package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.domain.model.Event

private fun createEventFromSerialized(event: EventSerialized): Event {
    return Event(
        id = event.id,
        title = event.title,
        description = event.description,
        images = event.images,
        dateString = event.dateString,
        categories = event.categories.map { it.toInt() }
    )
}

fun EventSerialized.toDomainModel() = createEventFromSerialized(this)

fun List<EventSerialized>.toDomainModelList() = this.map { createEventFromSerialized(it) }