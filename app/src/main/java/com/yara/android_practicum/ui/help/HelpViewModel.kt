package com.yara.android_practicum.ui.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.api.RetrofitInstance
import com.yara.android_practicum.data.db.HelpDatabase
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

typealias Categories = List<Category>

class HelpViewModel : ViewModel() {

    private val context = App.instance

    private val categoriesRepository =
        CategoriesRepositoryImpl(
            AssetReaderImpl(CategoryDeserializer),
            RetrofitInstance.api,
            HelpDatabase.getInstance(context).HelpDao(),
        )

    init {
        viewModelScope.launch {
            initCategories()
        }
    }

    suspend fun loadCategories(): Categories {
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

    fun queryCategories(): Flow<Categories> = categoriesRepository.queryCategoriesFromDB()

    private suspend fun initCategories() {
        categoriesRepository.getCategories().collect { categories ->
            categoriesRepository.insertCategoryListIntoDB(categories)
        }
    }
}