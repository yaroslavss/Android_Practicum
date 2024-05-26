package com.yara.android_practicum.data.mapper

import com.yara.core.data.model.EventAPI
import com.yara.core.domain.model.Event
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

private fun createEventFromAPI(event: EventAPI): Event {
    // calculate date fields
    val tz = TimeZone.currentSystemDefault()
    val today: LocalDate = Clock.System.todayIn(tz)

    val instantStart = Instant.fromEpochSeconds(event.startDate)
    val eds = instantStart.toLocalDateTime(tz).date

    val instantEnd = Instant.fromEpochSeconds(event.endDate)
    val ede = instantEnd.toLocalDateTime(tz).date

    // event
    return Event(
        id = event.id,
        title = event.name,
        description = event.description,
        images = event.photos,
        dateStart = eds,
        dateEnd = ede,
        dateString = " ${today.daysUntil(eds)} дней (${eds.dayOfMonth}.${eds.monthNumber} - ${ede.dayOfMonth}.${ede.monthNumber})",
        categories = event.category.map { it },
        isUnread = true,
        phone = event.phone,
        address = event.address,
        organisation = event.organisation,
    )
}

fun EventAPI.toDomainModel() = createEventFromAPI(this)

fun List<EventAPI>.toDomainModelList() = this.map { createEventFromAPI(it) }