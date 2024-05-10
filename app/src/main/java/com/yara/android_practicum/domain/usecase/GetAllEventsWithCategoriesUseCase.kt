package com.yara.android_practicum.domain.usecase

import com.yara.android_practicum.data.db.entity.relation.EventCategoryCrossRef
import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.ui.news.Events
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetAllEventsWithCategoriesUseCase @Inject constructor(
    private val eventsRepository: EventsRepositoryImpl
) {

    operator fun invoke(scope: CoroutineScope): Flow<Events> {
        scope.launch {
            eventsRepository.getEvents().collect { events ->
                eventsRepository.insertEventListIntoDB(events)
                events.forEach { event ->
                    insertEventCategoryCrossRefIntoDB(event.id, event.category)
                }
            }
        }

        return eventsRepository.queryEventsWithCategoriesFromDB()
            .map { it.toDomainModelList() }
    }

    private suspend fun insertEventCategoryCrossRefIntoDB(eventId: Int, categoriesStr: String) {
        val categories = categoriesStr.split(", ").map { it.toInt() }
        categories.forEach {
            eventsRepository.insertEventCategoryCrossRefIntoDB(
                EventCategoryCrossRef(eventId = eventId, categoryId = it)
            )
        }
    }
}