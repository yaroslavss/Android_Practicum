package com.yara.android_practicum.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.util.CategoryAssetReader
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream

class HelpViewModel : ViewModel() {

    private val _categoriesListLiveData = MutableLiveData<List<Category>>()
    val categoriesListLiveData: LiveData<List<Category>> = _categoriesListLiveData

    private val categoriesRepository =
        CategoriesRepositoryImpl(HardCodedDataSource(), CategoryAssetReader())

    private val context = App.instance
    lateinit var inputStream: InputStream

    init {
        try {
            inputStream = context.assets.open(Constants.CATEGORIES_ASSET_FILENAME)
            loadCategories()
        } catch (e: IOException) {
            println("json not found")
        }
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = categoriesRepository.readCategories(inputStream)
            _categoriesListLiveData.postValue(categories.toDomainModelList())
        }
    }
}