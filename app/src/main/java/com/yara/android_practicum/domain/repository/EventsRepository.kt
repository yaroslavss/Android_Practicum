package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.data.db.entity.relation.EventCategoryCrossRef
import com.yara.android_practicum.data.db.entity.relation.EventWithCategories
import com.yara.android_practicum.ui.news.Events
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface EventsRepository {

    suspend fun readEvents(inputStream: InputStream): Events

    fun getEvents(): Flow<List<EventEntity>>

    suspend fun insertEventListIntoDB(events: List<EventEntity>)

    suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef)

    fun queryEventsFromDB(): Flow<Events>

    fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>>

    fun queryEventsByCategoriesFromDB(categories: Array<Int>): Flow<Events>
}