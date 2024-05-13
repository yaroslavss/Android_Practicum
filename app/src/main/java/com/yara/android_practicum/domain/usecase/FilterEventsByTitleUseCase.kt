package com.yara.android_practicum.domain.usecase

import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.ui.news.Events
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class FilterEventsByTitleUseCase @Inject constructor(
    private val eventsRepository: EventsRepositoryImpl
) {

    operator fun invoke(strToFind: String): Flow<Events> =
        if (strToFind.isEmpty()) {
            emptyFlow()
        } else {
            eventsRepository.queryEventsByTitleFromDB(strToFind)
        }
}