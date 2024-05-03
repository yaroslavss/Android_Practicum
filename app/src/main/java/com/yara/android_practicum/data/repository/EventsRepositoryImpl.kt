package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.api.RemoteAPI
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.domain.repository.EventsRepository
import com.yara.android_practicum.ui.news.Events
import com.yara.android_practicum.utils.AssetReader
import com.yara.android_practicum.utils.Constants.EXECUTOR_TIMEOUT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.io.InputStream

class EventsRepositoryImpl(
    private val assetDataSource: AssetReader<EventSerialized>,
    private val remoteAPI: RemoteAPI,
) : EventsRepository {

    override suspend fun readEvents(inputStream: InputStream): Events {
        delay(EXECUTOR_TIMEOUT)
        return assetDataSource.readList(inputStream).toDomainModelList()
    }

    override fun getEvents(): Flow<Events> =
        remoteAPI.getEvents()
            .map { it.toDomainModelList() }
            .flowOn(Dispatchers.IO)
}