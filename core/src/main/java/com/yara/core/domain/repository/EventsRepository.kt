package com.yara.core.domain.repository

import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef
import com.yara.core.data.db.entity.relation.EventWithCategories
import com.yara.core.data.model.EventAPI
import com.yara.core.domain.model.Event
import com.yara.core.domain.model.Events
import com.yara.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EventsRepository {

    suspend fun getEvents(): Resource<List<EventAPI>>

    suspend fun insertEventListIntoDB(events: List<EventEntity>)

    suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef)

    suspend fun updateEventIsUnread(event: EventUpdateIsUnreadEntity)

    fun queryEventsFromDB(): Flow<Events>

    fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>>

    fun queryEventsByCategoriesFromDB(categories: Array<Int>): Flow<Events>

    fun queryEventsByTitleFromDB(strToFind: String): Flow<Events>

    fun queryEventByIdFromDB(eventId: Int): Flow<Event>
}