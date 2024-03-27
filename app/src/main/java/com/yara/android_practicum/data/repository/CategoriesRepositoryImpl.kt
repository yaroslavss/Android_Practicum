package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.domain.repository.RepositoryCallback
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Resource
import java.io.InputStream
import java.util.concurrent.Executor

class CategoriesRepositoryImpl(
    private val assetDataSource: AssetReader<CategorySerialized>,
    private val executor: Executor
) : CategoriesRepository {

    override fun readCategories(
        inputStream: InputStream,
        callback: RepositoryCallback<List<Category>>
    ) {
        executor.execute {
            Thread.sleep(EXECUTOR_TIMEOUT)
            val categories = readCategoriesSynchronous(inputStream)
            callback.onComplete(Resource.Success(categories.toDomainModelList()))
        }
    }

    private fun readCategoriesSynchronous(inputStream: InputStream): List<CategorySerialized> =
        assetDataSource.readList(inputStream)

    companion object {
        const val EXECUTOR_TIMEOUT = 5000L
    }
}