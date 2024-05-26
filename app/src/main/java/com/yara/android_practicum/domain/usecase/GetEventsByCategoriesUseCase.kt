package com.yara.android_practicum.domain.usecase

import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventsByCategoriesUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(categories: Array<Int>): Flow<Events> =
        eventsRepository.queryEventsByCategoriesFromDB(categories)
}