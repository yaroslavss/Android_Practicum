package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.core.data.model.CategoryAPI
import com.yara.android_practicum.ui.help.Categories
import com.yara.android_practicum.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface CategoriesRepository {

    suspend fun readCategories(inputStream: InputStream): Categories

    suspend fun getCategories(): Resource<List<CategoryAPI>>

    suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>)

    fun queryCategoriesFromDB(): Flow<Categories>
}