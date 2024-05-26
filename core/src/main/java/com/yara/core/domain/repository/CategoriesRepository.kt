package com.yara.core.domain.repository

import com.yara.core.data.db.entity.CategoryEntity
import com.yara.core.data.model.CategoryAPI
import com.yara.core.domain.model.Categories
import com.yara.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {

    suspend fun getCategories(): Resource<List<CategoryAPI>>

    suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>)

    fun queryCategoriesFromDB(): Flow<Categories>
}