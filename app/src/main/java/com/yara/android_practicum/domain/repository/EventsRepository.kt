package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.ui.news.Events
import io.reactivex.rxjava3.core.Observable
import java.io.InputStream

interface EventsRepository {

    fun readEvents(inputStream: InputStream): Observable<Events>

    fun getEvents(): Observable<Events>
}