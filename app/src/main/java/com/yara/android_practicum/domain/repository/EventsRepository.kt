package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.data.db.entity.relation.EventCategoryCrossRef
import com.yara.android_practicum.data.db.entity.relation.EventWithCategories
import com.yara.android_practicum.data.model.EventAPI
import com.yara.android_practicum.ui.news.Events
import com.yara.android_practicum.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface EventsRepository {

    suspend fun readEvents(inputStream: InputStream): Events

    suspend fun getEvents(): Resource<List<EventAPI>>

    suspend fun insertEventListIntoDB(events: List<EventEntity>)

    suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef)

    fun queryEventsFromDB(): Flow<Events>

    fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>>
}