package com.yara.feature_news.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.core.domain.model.Categories
import com.yara.core.domain.model.Event
import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.usecase.FilterEventsByTitleUseCase
import com.yara.core.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.core.domain.usecase.GetEventByIdUseCase
import com.yara.core.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.core.domain.usecase.UpdateEventSetReadUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class NewsUiState(
    val categories: Categories = emptyList(),
    val events: Events = emptyList(),
    val searchResults: Events = emptyList(),
    val unreadNewsQnt: Int = 0,
    val filters: MutableSet<Int> = mutableSetOf(),
    val isLoading: Boolean = true,
    val userMessage: String = "",
)

data class EventUiState(val event: Event? = null)

class NewsViewModelFactory(
    private val categoriesRepository: CategoriesRepository,
    private val getAllEventsWithCategoriesUseCase: GetAllEventsWithCategoriesUseCase,
    private val getEventsByCategoriesUseCase: GetEventsByCategoriesUseCase,
    private val filterEventsByTitleUseCase: FilterEventsByTitleUseCase,
    private val updateEventSetReadUseCase: UpdateEventSetReadUseCase,
    private val getEventByIdUseCase: GetEventByIdUseCase,
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        NewsViewModel(
            categoriesRepository,
            getAllEventsWithCategoriesUseCase,
            getEventsByCategoriesUseCase,
            filterEventsByTitleUseCase,
            updateEventSetReadUseCase,
            getEventByIdUseCase,
        ) as T
}

class NewsViewModel(
    private val categoriesRepository: CategoriesRepository,
    private val getAllEventsWithCategoriesUseCase: GetAllEventsWithCategoriesUseCase,
    private val getEventsByCategoriesUseCase: GetEventsByCategoriesUseCase,
    private val filterEventsByTitleUseCase: FilterEventsByTitleUseCase,
    private val updateEventSetReadUseCase: UpdateEventSetReadUseCase,
    private val getEventByIdUseCase: GetEventByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState

    private val _uiStateEvent = MutableStateFlow(EventUiState())
    val uiStateEvent: StateFlow<EventUiState> = _uiStateEvent

    val filters = mutableSetOf<Int>()

    private val scope = viewModelScope

    init {
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
                        _uiState.value.copy(searchResults = emptyList())
                    }
                }
                .collect { events ->
                    _uiState.update {
                        _uiState.value.copy(searchResults = events)
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

    fun queryEventById(eventId: Int) {
        scope.launch {
            getEventByIdUseCase(eventId)
                .collect { event ->
                    _uiStateEvent.update {
                        _uiStateEvent.value.copy(event)
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