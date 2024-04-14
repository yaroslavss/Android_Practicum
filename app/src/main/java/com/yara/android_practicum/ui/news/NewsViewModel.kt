package com.yara.android_practicum.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yara.android_practicum.App
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.data.util.EventDeserializer
import com.yara.android_practicum.domain.model.Event
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.Constants
import com.yara.android_practicum.utils.Resource
import com.yara.android_practicum.utils.containsAny
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.io.IOException
import java.io.InputStream

typealias Events = List<Event>

class NewsViewModel : ViewModel() {

    private val _eventsLiveData = MutableLiveData<Resource<Events>>()
    val eventsLiveData: LiveData<Resource<Events>> = _eventsLiveData

    var eventsObservable: Observable<Resource<Events>>
    var categoriesObservable: Observable<Resource<Categories>>

    private val _searchResultsLiveData = MutableLiveData<Resource<Events>>()
    val searchResultsLiveData: LiveData<Resource<Events>> = _searchResultsLiveData

    private val _categoriesLiveData = MutableLiveData<Resource<Categories>>()
    val categoriesLiveData: LiveData<Resource<Categories>> = _categoriesLiveData

    lateinit var allEvents: Events
    val filters = mutableSetOf<Int>()
    val newsQnt: BehaviorSubject<Int> = BehaviorSubject.create()

    private val eventsRepository =
        EventsRepositoryImpl(
            AssetReaderImpl(EventDeserializer),
            App.instance.executorService
        )
    private val categoriesRepository =
        CategoriesRepositoryImpl(
            AssetReaderImpl(CategoryDeserializer),
            App.instance.executorService
        )

    private val context = App.instance

    lateinit var inputStream1: InputStream
    lateinit var inputStream2: InputStream

    init {
        try {
            inputStream1 = context.assets.open(Constants.CATEGORIES_ASSET_FILENAME)
            categoriesObservable = loadCategories(inputStream1)
                .map { Resource.Success(it) }
        } catch (e: IOException) {
            categoriesObservable =
                Observable.just(Resource.Error("Exception while opening asset file"))
        }
        try {
            inputStream2 = context.assets.open(Constants.EVENTS_ASSET_FILENAME)
            eventsObservable = loadEvents(inputStream2)
                .map { Resource.Success(it) }
        } catch (e: IOException) {
            eventsObservable =
                Observable.just(Resource.Error("Exception while opening asset file"))
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
        _searchResultsLiveData.postValue(Resource.Success(
            if (str == "") {
                emptyList<Event>()
            } else {
                allEvents.filter {
                    it.title.startsWith(
                        str,
                        true
                    )
                }
            }))
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
        newsQnt.onNext(qnt)
    }

    private fun loadEvents(inputStream: InputStream): Observable<Events> =
        eventsRepository.readEvents(inputStream)

    private fun loadCategories(inputStream: InputStream): Observable<Categories> =
        categoriesRepository.readCategories(inputStream)
}