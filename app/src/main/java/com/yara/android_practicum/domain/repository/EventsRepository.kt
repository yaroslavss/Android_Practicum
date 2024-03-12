package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.model.EventSerialized
import java.io.InputStream

interface EventsRepository {

    suspend fun readEvents(inputStream: InputStream): List<EventSerialized>
}