package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.db.entity.CategoryEntity
import com.yara.android_practicum.ui.help.Categories
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface CategoriesRepository {

    suspend fun readCategories(inputStream: InputStream): Categories

    fun getCategories(): Flow<List<CategoryEntity>>

    suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>)

    fun queryCategoriesFromDB(): Flow<Categories>
}