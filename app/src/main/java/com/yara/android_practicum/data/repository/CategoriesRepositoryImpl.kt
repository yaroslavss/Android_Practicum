package com.yara.android_practicum.data.repository

import com.yara.core.data.api.RemoteAPI
import com.yara.core.data.db.HelpDao
import com.yara.core.data.db.entity.CategoryEntity
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.core.data.model.CategoryAPI
import com.yara.android_practicum.data.model.CategorySerialized
import com.yara.android_practicum.domain.repository.CategoriesRepository
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Constants.EXECUTOR_TIMEOUT
import com.yara.android_practicum.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val assetDataSource: AssetReader<CategorySerialized>,
    private val remoteAPI: RemoteAPI,
    private val helpDao: HelpDao,
) : CategoriesRepository {

    override suspend fun readCategories(inputStream: InputStream): Categories {
        delay(EXECUTOR_TIMEOUT)
        return assetDataSource.readList(inputStream).toDomainModelList()
    }

    override suspend fun getCategories(): Resource<List<CategoryAPI>> =
        try {
            val response = remoteAPI.getCategories()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: HttpException) {
            // request exception
            Resource.Error(e.toString())
        } catch (e: IOException) {
            // no internet exception
            Resource.Error(e.toString())
        }

    override suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>) {
        helpDao.insertCategoryList(categories)
    }

    override fun queryCategoriesFromDB(): Flow<Categories> =
        helpDao.getCategories()
            .distinctUntilChanged()
            .map { it.toDomainModelList() }
}