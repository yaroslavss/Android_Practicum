package com.yara.android_practicum.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.mapper.toDomainModelList
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream

typealias Events = List<Event>

class NewsViewModel : ViewModel() {

    private val _eventsLiveData = MutableLiveData<Resource<Events>>()
    val eventsLiveData: LiveData<Resource<Events>> = _eventsLiveData
    private val _categoriesLiveData = MutableLiveData<Resource<Categories>>()
    val categoriesLiveData: LiveData<Resource<Categories>> = _categoriesLiveData
    lateinit var allEvents: Events
    val filters = mutableSetOf<Int>()

    private val eventsRepository = EventsRepositoryImpl(AssetReaderImpl(EventDeserializer))
    private val categoriesRepository =
        CategoriesRepositoryImpl(HardCodedDataSource(), AssetReaderImpl(CategoryDeserializer))

    private val context = App.instance
    lateinit var inputStream1: InputStream
    lateinit var inputStream2: InputStream

    init {
        try {
            inputStream1 = context.assets.open(Constants.CATEGORIES_ASSET_FILENAME)
            loadCategories(inputStream1)
        } catch (e: IOException) {
            _categoriesLiveData.value =
                Resource.Error("Exception while opening categories asset file")
        }
        try {
            inputStream2 = context.assets.open(Constants.EVENTS_ASSET_FILENAME)
            loadEvents(inputStream2)
        } catch (e: IOException) {
            _eventsLiveData.value = Resource.Error("Exception while opening events asset file")
        }
    }

    fun addFilter(id: Int) {
        filters.add(id)
        filterEvents()
    }

    fun removeFilter(id: Int) {
        filters.remove(id)
        filterEvents()
    }

    private fun filterEvents() {
        _eventsLiveData.value =
            Resource.Success(allEvents.filter { filters.containsAny(it.categories) })
    }

    private fun loadEvents(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            allEvents = eventsRepository.readEvents(inputStream).toDomainModelList()
            _eventsLiveData.postValue(Resource.Success(allEvents))
        }
    }

    private fun loadCategories(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = categoriesRepository.readCategories(inputStream)
            filters.addAll(categories.map { it.id })
            _categoriesLiveData.postValue(Resource.Success(categories.toDomainModelList()))
        }
    }
}