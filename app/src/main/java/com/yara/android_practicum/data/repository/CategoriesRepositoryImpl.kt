package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.api.RemoteAPI
import com.yara.android_practicum.data.db.HelpDao
import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.mapper.toEntityList
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
    private val helpDao: HelpDao,
) : CategoriesRepository {

    override suspend fun readCategories(inputStream: InputStream): Categories {
        delay(EXECUTOR_TIMEOUT)
        return assetDataSource.readList(inputStream).toDomainModelList()
    }

    override fun getCategories(): Flow<List<CategoryEntity>> =
        remoteAPI.getCategories()
            .map { it.toEntityList() }
            .flowOn(Dispatchers.IO)

    override suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>) {
        helpDao.insertCategoryList(categories)
    }

    override fun queryCategoriesFromDB(): Flow<Categories> =
        helpDao.getCategories().map { it.toDomainModelList() }
}