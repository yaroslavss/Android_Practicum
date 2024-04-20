package com.yara.android_practicum.ui.help

import androidx.lifecycle.ViewModel
import com.yara.android_practicum.App
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Constants
import io.reactivex.rxjava3.core.Observable
import java.io.IOException

typealias Categories = List<Category>

class HelpViewModel : ViewModel() {

    private val categoriesRepository =
        CategoriesRepositoryImpl(
            AssetReaderImpl(CategoryDeserializer),
            App.instance.executorService
        )

    private val context = App.instance

    fun loadCategories(): Observable<Categories> {
        return try {
            val inputStream = context.assets.open(Constants.CATEGORIES_ASSET_FILENAME)
            categoriesRepository.readCategories(inputStream)
        } catch (e: IOException) {
            Observable.error(e)
        }
    }
}