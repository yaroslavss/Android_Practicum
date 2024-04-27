package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.ui.help.Categories
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface CategoriesRepository {

    fun readCategories(inputStream: InputStream): Observable<Categories>

    fun getCategories(): Flow<Categories>
}