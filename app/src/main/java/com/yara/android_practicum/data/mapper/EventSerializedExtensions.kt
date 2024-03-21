package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.data.model.EventSerialized
import com.yara.android_practicum.domain.model.Event
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

private fun createEventFromSerialized(event: EventSerialized): Event {
    // calculate date string
    val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val eds = LocalDate.parse(event.dateStart)
    val ede = LocalDate.parse(event.dateEnd)

    return Event(
        id = event.id,
        title = event.title,
        description = event.description,
        images = event.images,
        dateStart = eds,
        dateEnd = ede,
        dateString = "Осталось ${today.daysUntil(eds)} дней (${eds.dayOfMonth}.${eds.monthNumber} - ${ede.dayOfMonth}.${ede.monthNumber})",
        categories = event.categories.map { it.toInt() }
    )
}

fun EventSerialized.toDomainModel() = createEventFromSerialized(this)

fun List<EventSerialized>.toDomainModelList() = this.map { createEventFromSerialized(it) }