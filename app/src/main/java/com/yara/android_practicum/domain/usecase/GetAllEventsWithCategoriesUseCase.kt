package com.yara.android_practicum.domain.usecase

import com.yara.android_practicum.data.mapper.toDomainModelList
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef
import com.yara.core.data.mapper.toEntityList
import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.EventsRepository
import com.yara.core.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetAllEventsWithCategoriesUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(scope: CoroutineScope): Flow<Events> {
        scope.launch {
            when (val result = eventsRepository.getEvents()) {
                is Resource.Success -> {
                    val events = result.data
                    eventsRepository.insertEventListIntoDB(events.toEntityList())
                    events.forEach { event ->
                        insertEventCategoryCrossRefIntoDB(event.id, event.category)
                    }
                }

                else -> {}
            }
        }

        return eventsRepository.queryEventsWithCategoriesFromDB()
            .map { it.toDomainModelList() }
    }

    private suspend fun insertEventCategoryCrossRefIntoDB(eventId: Int, categories: List<Int>) {
        categories.forEach {
            eventsRepository.insertEventCategoryCrossRefIntoDB(
                EventCategoryCrossRef(eventId = eventId, categoryId = it)
            )
        }
    }
}