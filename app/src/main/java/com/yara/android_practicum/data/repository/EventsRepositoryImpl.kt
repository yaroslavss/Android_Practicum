package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.domain.repository.EventsRepository
import com.yara.android_practicum.utils.AssetReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class EventsRepositoryImpl(
    private val assetDataSource: AssetReader<EventSerialized>,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : EventsRepository {

    override suspend fun readEvents(inputStream: InputStream): List<EventSerialized> =
        withContext(defaultDispatcher) {
            assetDataSource.readList(inputStream)
        }
}