package com.yara.core.domain.usecase

import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class FilterEventsByTitleUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(strToFind: String): Flow<Events> =
        if (strToFind.isEmpty()) {
            emptyFlow()
        } else {
            eventsRepository.queryEventsByTitleFromDB(strToFind)
        }
}