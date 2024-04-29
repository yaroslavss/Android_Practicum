package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.api.RemoteAPI
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Constants.EXECUTOR_TIMEOUT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.InputStream

class CategoriesRepositoryImpl(
    private val assetDataSource: AssetReader<CategorySerialized>,
    private val remoteAPI: RemoteAPI,
) : CategoriesRepository {

    override suspend fun readCategories(inputStream: InputStream): Categories {
        delay(EXECUTOR_TIMEOUT)
        return assetDataSource.readList(inputStream).toDomainModelList()
    }

    override fun getCategories(): Flow<Categories> =
        remoteAPI.getCategories()
            .map { it.toDomainModelList() }
            .flowOn(Dispatchers.IO)
}