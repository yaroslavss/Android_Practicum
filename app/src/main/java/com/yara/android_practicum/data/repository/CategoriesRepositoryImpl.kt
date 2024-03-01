package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.model.CategoryLocal
import com.yara.android_practicum.domain.repository.CategoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesRepositoryImpl(private val dataSource: HardCodedDataSource) : CategoriesRepository {

    override suspend fun loadCategories(): List<CategoryLocal> = withContext(Dispatchers.IO) {
        dataSource.loadCategories()
    }
}