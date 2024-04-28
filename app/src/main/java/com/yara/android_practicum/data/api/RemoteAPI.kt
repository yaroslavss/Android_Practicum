package com.yara.android_practicum.data.api

import com.yara.android_practicum.data.model.CategoryAPI
import com.yara.android_practicum.data.model.EventAPI
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface RemoteAPI {

    @GET("/categories")
    fun getCategories(): Observable<List<CategoryAPI>>

    @GET("/events")
    fun getEvents(): Observable<List<EventAPI>>
}