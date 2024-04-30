package com.yara.android_practicum.data.api

import com.yara.android_practicum.data.model.CategoryAPI
import com.yara.android_practicum.data.model.EventAPI
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface RemoteAPI {

    @GET("/categories")
    fun getCategories(): Flow<List<CategoryAPI>>

    @GET("/events")
    fun getEvents(): Flow<List<EventAPI>>
}