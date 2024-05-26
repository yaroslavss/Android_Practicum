package com.yara.android_practicum.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.di.DaggerAppComponent
import com.yara.android_practicum.domain.usecase.FilterEventsByTitleUseCase
import com.yara.core.domain.usecase.GetAllCategoriesUseCase
import com.yara.android_practicum.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.android_practicum.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.android_practicum.domain.usecase.UpdateEventSetReadUseCase
import com.yara.core.App
import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.core.domain.model.Categories
import com.yara.core.domain.model.Event
import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.repository.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    lateinit var categoriesRepository: CategoriesRepository

    @Inject
    lateinit var eventsRepository: EventsRepository

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState

    val filters = mutableSetOf<Int>()

    private val scope = viewModelScope

    init {
        DaggerAppComponent.factory()
            .create(App.instance)
            .inject(this)

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
}