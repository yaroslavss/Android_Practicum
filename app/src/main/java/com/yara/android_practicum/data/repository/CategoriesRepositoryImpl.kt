package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Constants.EXECUTOR_TIMEOUT
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InputStream
import java.util.concurrent.Executor

class CategoriesRepositoryImpl(
    private val assetDataSource: AssetReader<CategorySerialized>,
    private val executor: Executor
) : CategoriesRepository {

    override fun readCategories(inputStream: InputStream): Observable<Categories> =
        Observable
            .create { emitter ->
                Thread.sleep(EXECUTOR_TIMEOUT)
                val categories = readCategoriesSynchronous(inputStream)
                emitter.onNext(categories)
            }
            .subscribeOn(Schedulers.from(executor))

    private fun readCategoriesSynchronous(inputStream: InputStream): Categories =
        assetDataSource.readList(inputStream).toDomainModelList()
}