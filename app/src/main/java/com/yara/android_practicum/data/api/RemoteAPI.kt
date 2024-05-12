package com.yara.android_practicum.data.api

import com.yara.android_practicum.data.model.CategoryAPI
import com.yara.android_practicum.data.model.EventAPI
import retrofit2.Response
import retrofit2.http.GET

interface RemoteAPI {

    @GET("/categories")
    suspend fun getCategories(): Response<List<CategoryAPI>>

    @GET("/events")
    suspend fun getEvents(): Response<List<EventAPI>>
}