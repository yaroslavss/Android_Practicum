package com.yara.android_practicum.domain.usecase

import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.android_practicum.domain.repository.EventsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateEventSetReadUseCase @Inject constructor(
    private val eventsRepository: EventsRepository
) {

    operator fun invoke(scope: CoroutineScope, event: EventUpdateIsUnreadEntity) =
        scope.launch {
            eventsRepository.updateEventIsUnread(event)
        }
}