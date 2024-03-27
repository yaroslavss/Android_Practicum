package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.domain.model.Category
import com.yara.android_practicum.utils.Resource
import java.io.InputStream

interface CategoriesRepository {

    fun readCategories(
        inputStream: InputStream,
        callback: RepositoryCallback<List<Category>>
    )
}

fun interface RepositoryCallback<T> {
    fun onComplete(result: Resource<T>?)
}