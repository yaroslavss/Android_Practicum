package com.yara.core.domain.usecase

import com.yara.core.domain.model.Event
import com.yara.core.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventByIdUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(eventId: Int): Flow<Event> =
        eventsRepository.queryEventByIdFromDB(eventId)
}