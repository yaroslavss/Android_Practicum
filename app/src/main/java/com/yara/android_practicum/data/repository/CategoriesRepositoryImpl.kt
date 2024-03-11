package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.model.CategoryLocal
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.utils.AssetReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class CategoriesRepositoryImpl(
    private val localDataSource: HardCodedDataSource,
    private val assetDataSource: AssetReader<CategorySerialized>,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : CategoriesRepository {

    override suspend fun loadCategories(): List<CategoryLocal> = withContext(defaultDispatcher) {
        localDataSource.loadCategories()
    }

    override suspend fun readCategories(inputStream: InputStream): List<CategorySerialized> =
        withContext(defaultDispatcher) {
            assetDataSource.readList(inputStream)
        }
}