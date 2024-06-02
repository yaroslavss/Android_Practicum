package com.yara.core.data.api

import com.yara.core.data.model.CategoryAPI
import com.yara.core.data.model.EventAPI
import retrofit2.Response
import retrofit2.http.GET

interface RemoteAPI {

    @GET("/categories")
    suspend fun getCategories(): Response<List<CategoryAPI>>

    @GET("/events")
    suspend fun getEvents(): Response<List<EventAPI>>
}