package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.ui.news.Events
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface EventsRepository {

    suspend fun readEvents(inputStream: InputStream): Events

    fun getEvents(): Flow<List<EventEntity>>

    suspend fun insertEventListIntoDB(events: List<EventEntity>)

    fun queryEventsFromDB(): Flow<Events>
}