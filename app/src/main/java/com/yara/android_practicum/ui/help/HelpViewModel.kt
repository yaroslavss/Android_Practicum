package com.yara.android_practicum.ui.help

import androidx.lifecycle.ViewModel
import com.yara.android_practicum.App
import com.yara.android_practicum.data.repository.CategoriesRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.CategoryDeserializer
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Constants
import com.yara.android_practicum.utils.Resource
import io.reactivex.rxjava3.core.Observable
import java.io.IOException
import java.io.InputStream

typealias Categories = List<Category>

class HelpViewModel : ViewModel() {

    var categoriesObservable: Observable<Resource<Categories>>

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
            categoriesObservable = loadCategories(inputStream)
                .map { Resource.Success(it) }
        } catch (e: IOException) {
            categoriesObservable =
                Observable.just(Resource.Error("Exception while opening asset file"))
        }
    }

    private fun loadCategories(inputStream: InputStream): Observable<Categories> =
        categoriesRepository.readCategories(inputStream)
}