package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.model.CategoryLocal

interface CategoriesRepository {

    suspend fun loadCategories(): List<CategoryLocal>
}