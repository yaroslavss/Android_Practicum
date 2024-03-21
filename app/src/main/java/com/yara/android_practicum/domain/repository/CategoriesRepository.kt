package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.model.CategoryLocal
import com.yara.android_practicum.data.model.CategorySerialized
import java.io.InputStream

interface CategoriesRepository {

    suspend fun loadCategories(): List<CategoryLocal>

    suspend fun readCategories(inputStream: InputStream): List<CategorySerialized>
}