package com.yara.core.data.repository

import com.yara.core.data.mapper.toDomainModelList
import com.yara.core.data.api.RemoteAPI
import com.yara.core.data.db.HelpDao
import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef
import com.yara.core.data.db.entity.relation.EventWithCategories
import com.yara.core.data.model.EventAPI
import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.EventsRepository
import com.yara.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    private val remoteAPI: RemoteAPI,
    private val helpDao: HelpDao,
) : EventsRepository {

    override suspend fun getEvents(): Resource<List<EventAPI>> =
        try {
            val response = remoteAPI.getEvents()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: HttpException) {
            // request exception
            Resource.Error(e.toString())
        } catch (e: IOException) {
            // no internet exception
            Resource.Error(e.toString())
        }

    override suspend fun insertEventListIntoDB(events: List<EventEntity>) {
        helpDao.insertEventList(events)
    }

    override suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef) {
        helpDao.insertEventCategoryCrossRef(eventCategoryCrossRef)
    }

    override suspend fun updateEventIsUnread(event: EventUpdateIsUnreadEntity) {
        helpDao.updateEventIsUnread(event)
    }

    override fun queryEventsFromDB(): Flow<Events> =
        helpDao.getEvents().map { it.toDomainModelList() }

    override fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>> =
        helpDao.getEventsWithCategories().distinctUntilChanged()

    override fun queryEventsByCategoriesFromDB(categories: Array<Int>): Flow<Events> =
        helpDao.getEventsByCategories(categories)
            .distinctUntilChanged()
            .map { it.toDomainModelList() }

    override fun queryEventsByTitleFromDB(strToFind: String): Flow<Events> =
        helpDao.filterEventsByTitle(strToFind)
            .distinctUntilChanged()
            .map { it.toDomainModelList() }
}