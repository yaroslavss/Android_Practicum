package com.yara.android_practicum.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.domain.model.Event
import com.yara.android_practicum.domain.usecase.FilterEventsByTitleUseCase
import com.yara.android_practicum.domain.usecase.GetAllCategoriesUseCase
import com.yara.android_practicum.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.android_practicum.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.android_practicum.domain.usecase.UpdateEventSetReadUseCase
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

typealias Events = List<Event>

data class NewsUiState(
    val categories: Categories = emptyList(),
    val events: Events = emptyList(),
    val searchResults: Events = emptyList(),
    val unreadNewsQnt: Int = 0,
    val filters: MutableSet<Int> = mutableSetOf(),
    val isLoading: Boolean = true,
    val userMessage: String = "",
)

class NewsViewModel : ViewModel() {

    private val context = App.instance

    @Inject
    lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    @Inject
    lateinit var getAllEventsWithCategoriesUseCase: GetAllEventsWithCategoriesUseCase

    @Inject
    lateinit var getEventsByCategoriesUseCase: GetEventsByCategoriesUseCase

    @Inject
    lateinit var filterEventsByTitleUseCase: FilterEventsByTitleUseCase

    @Inject
    lateinit var updateEventSetReadUseCase: UpdateEventSetReadUseCase

    @Inject
    lateinit var categoriesRepository: CategoriesRepositoryImpl

    @Inject
    lateinit var eventsRepository: EventsRepositoryImpl

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState

    val filters = mutableSetOf<Int>()

    private val scope = viewModelScope

    init {
        App.instance.dagger.inject(this)

        scope.launch {
            queryCategories()
                .collect { categories ->
                    _uiState.update {
                        filters.addAll(categories.map { it.id })
                        _uiState.value.copy(
                            categories = categories,
                            filters = filters,
                            isLoading = false,
                        )
                    }
                }
        }

        scope.launch {
            getAllEventsWithCategoriesUseCase(scope)
                .collect { events ->
                    _uiState.update {
                        _uiState.value.copy(
                            events = events,
                            unreadNewsQnt = events.filter { it.isUnread }.size,
                        )
                    }
                }
        }
    }

    fun addFilter(id: Int) {
        filters.add(id)
        filterEventsByCategory()
    }

    fun removeFilter(id: Int) {
        filters.remove(id)
        filterEventsByCategory()
    }

    fun filterEventsByTitle(str: String) {
        scope.launch {
            filterEventsByTitleUseCase(str)
                .onEmpty {
                    _uiState.update {
                        _uiState.value.copy(searchResults = emptyList(),)
                    }
                }
                .collect { events ->
                    _uiState.update {
                        _uiState.value.copy(searchResults = events,)
                    }
                }
        }
    }

    private fun filterEventsByCategory() {
        scope.launch {
            getEventsByCategoriesUseCase(filters.toTypedArray())
                .collect { events ->
                    _uiState.update {
                        _uiState.value.copy(
                            events = events,
                            filters = filters,
                            unreadNewsQnt = events.filter { it.isUnread }.size,
                        )
                    }
                }
        }
    }

    fun setEventRead(event: Event) {
        updateEventSetReadUseCase(scope, EventUpdateIsUnreadEntity(event.id, false))
        filterEventsByCategory()
    }

    private fun queryCategories() = categoriesRepository.queryCategoriesFromDB()

    private suspend fun loadEvents(): Events {
        var events = listOf<Event>()
        val inputStream = context.assets.open(Constants.EVENTS_ASSET_FILENAME)

        try {
            val deferred = viewModelScope.async {
                eventsRepository.readEvents(inputStream)
            }
            events = deferred.await()
        } catch (e: IOException) {
            println("!!! Error while reading events asset file")
        }

        return events
    }

    private suspend fun loadCategories(): Categories {
        var categories = listOf<Category>()
        val inputStream = context.assets.open(Constants.CATEGORIES_ASSET_FILENAME)

        try {
            val deferred = viewModelScope.async {
                categoriesRepository.readCategories(inputStream)
            }
            categories = deferred.await()
        } catch (e: IOException) {
            println("!!! Error while reading categories asset file")
        }

        return categories
    }
}