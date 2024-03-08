package com.yara.android_practicum.ui.help

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HelpViewModel : ViewModel() {

    private val _categoriesListLiveData = MutableLiveData<List<Category>>()
    val categoriesListLiveData: LiveData<List<Category>> = _categoriesListLiveData

    private val categoriesRepository = CategoriesRepositoryImpl(HardCodedDataSource())

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = categoriesRepository.loadCategories()
            _categoriesListLiveData.postValue(categories.toDomainModelList())
        }
    }
}