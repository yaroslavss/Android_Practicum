package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.api.RemoteAPI
import com.yara.android_practicum.data.db.HelpDao
import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.data.db.entity.relation.EventCategoryCrossRef
import com.yara.android_practicum.data.db.entity.relation.EventWithCategories
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.mapper.toEntityList
import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.domain.repository.EventsRepository
import com.yara.android_practicum.ui.news.Events
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Constants.EXECUTOR_TIMEOUT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.InputStream
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val assetDataSource: AssetReader<EventSerialized>,
    private val remoteAPI: RemoteAPI,
    private val helpDao: HelpDao,
) : EventsRepository {

    override suspend fun readEvents(inputStream: InputStream): Events {
        delay(EXECUTOR_TIMEOUT)
        return assetDataSource.readList(inputStream).toDomainModelList()
    }

    override fun getEvents(): Flow<List<EventEntity>> =
        remoteAPI.getEvents()
            .map { it.toEntityList() }
            .flowOn(Dispatchers.IO)

    override suspend fun insertEventListIntoDB(events: List<EventEntity>) {
        helpDao.insertEventList(events)
    }

    override suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef) {
        helpDao.insertEventCategoryCrossRef(eventCategoryCrossRef)
    }

    override fun queryEventsFromDB(): Flow<Events> =
        helpDao.getEvents().map { it.toDomainModelList() }

    override fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>> =
        helpDao.getEventsWithCategories().distinctUntilChanged()

    override fun queryEventsByCategoriesFromDB(categories: Array<Int>): Flow<Events> =
        helpDao.getEventsByCategories(categories)
            .distinctUntilChanged()
            .map { it.toDomainModelList() }
}