package com.yara.android_practicum.domain.usecase

import com.yara.android_practicum.domain.repository.EventsRepository
import com.yara.android_practicum.ui.news.Events
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEventsByCategoriesUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(categories: Array<Int>): Flow<Events> =
        eventsRepository.queryEventsByCategoriesFromDB(categories)
}