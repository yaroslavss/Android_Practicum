package com.yara.android_practicum.ui.help

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yara.android_practicum.App
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.domain.usecase.GetAllCategoriesUseCase
import com.yara.android_practicum.utils.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

typealias Categories = List<Category>

class HelpViewModel : ViewModel() {

    private val context = App.instance

    @Inject
    lateinit var getAllCategoriesUseCase: GetAllCategoriesUseCase

    @Inject
    lateinit var categoriesRepository: CategoriesRepositoryImpl

    private val scope = viewModelScope

    val categoriesFlow: Flow<Categories> by lazy { getAllCategoriesUseCase(scope) }

    init {
        App.instance.dagger.inject(this)
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
}