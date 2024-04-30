package com.yara.android_practicum.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.api.RetrofitInstance
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.data.util.EventDeserializer
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.domain.model.Event
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.Constants
import com.yara.android_practicum.utils.Resource
import com.yara.android_practicum.utils.containsAny
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.io.IOException

typealias Events = List<Event>

class NewsViewModel : ViewModel() {

    private val _eventsLiveData = MutableLiveData<Resource<Events>>()
    val eventsLiveData: LiveData<Resource<Events>> = _eventsLiveData

    private val _searchResults: MutableStateFlow<Resource<Events>> =
        MutableStateFlow(Resource.Success(emptyList()))
    val searchResults = _searchResults.asStateFlow()

    private val _categoriesLiveData = MutableLiveData<Resource<Categories>>()
    val categoriesLiveData: LiveData<Resource<Categories>> = _categoriesLiveData

    private val allEvents: MutableList<Event> = mutableListOf()
    val filters = mutableSetOf<Int>()

    private val _newsQnt = MutableStateFlow(0)
    val newsQnt = _newsQnt.asStateFlow()

    private val eventsRepository =
        EventsRepositoryImpl(
            AssetReaderImpl(EventDeserializer),
            RetrofitInstance.api
        )
    private val categoriesRepository =
        CategoriesRepositoryImpl(
            AssetReaderImpl(CategoryDeserializer),
            RetrofitInstance.api
        )

    private val context = App.instance

    init {
        viewModelScope.launch {
            getCategories()
                .collect { categories ->
                    filters.addAll(categories.map { it.id })
                    _categoriesLiveData.postValue(Resource.Success(categories))
                }
        }

        viewModelScope.launch {
            getEvents()
                .collect { events ->
                    events.toCollection(allEvents)
                    _eventsLiveData.postValue(Resource.Success(events))
                    // set badge for bottom navigation view
                    publishUnreadEventsQnt(allEvents.filter { it.isUnread }.size)
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

    suspend fun filterEventsByTitle(str: String) {
        val result = Resource.Success(if (str == "") {
            emptyList()
        } else {
            allEvents.filter {
                it.title.startsWith(str, true)
            }
        })

        _searchResults.emit(result)
    }

    private fun filterEventsByCategory() {
        val tmpEvents = allEvents.filter { filters.containsAny(it.categories) }
        _eventsLiveData.value = Resource.Success(tmpEvents)
        publishUnreadEventsQnt(tmpEvents.filter { it.isUnread }.size)
    }

    fun setEventRead(event: Event) {
        event.isUnread = false
        publishUnreadEventsQnt(
            allEvents
                .filter { filters.containsAny(it.categories) }
                .filter { it.isUnread }
                .size
        )
    }

    private fun publishUnreadEventsQnt(qnt: Int) {
        _newsQnt.value = qnt
    }

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

    private fun getEvents(): Flow<Events> =
        eventsRepository.getEvents().catch {
            emit(loadEvents())
        }

    private fun getCategories(): Flow<Categories> =
        categoriesRepository.getCategories().catch {
            emit(loadCategories())
        }
}