package com.yara.android_practicum.data.api

import com.yara.android_practicum.data.model.CategoryAPI
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface RemoteAPI {

    @GET("/categories")
    fun getCategories(): Observable<List<CategoryAPI>>
}