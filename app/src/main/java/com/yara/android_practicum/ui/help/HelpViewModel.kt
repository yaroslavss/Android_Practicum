package com.yara.android_practicum.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yara.android_practicum.App
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Constants
import com.yara.android_practicum.utils.Resource
import java.io.IOException
import java.io.InputStream

typealias Categories = List<Category>

class HelpViewModel : ViewModel() {

    private val _categoriesLiveData = MutableLiveData<Resource<Categories>>()
    val categoriesLiveData: LiveData<Resource<Categories>> = _categoriesLiveData

    private val categoriesRepository =
        CategoriesRepositoryImpl(
            AssetReaderImpl(CategoryDeserializer),
            App.instance.executorService
        )

    private val context = App.instance
    lateinit var inputStream: InputStream

    init {
        try {
            inputStream = context.assets.open(Constants.CATEGORIES_ASSET_FILENAME)
            loadCategories()
        } catch (e: IOException) {
            _categoriesLiveData.value = Resource.Error("Exception while opening asset file")
        }
    }

    private fun loadCategories() =
        categoriesRepository.readCategories(inputStream) { resource ->
            _categoriesLiveData.postValue(resource)
        }
}