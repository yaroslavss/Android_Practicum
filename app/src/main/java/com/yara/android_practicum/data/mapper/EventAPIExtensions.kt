package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.data.model.EventAPI
import com.yara.android_practicum.domain.model.Event
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.todayIn

private fun createEventFromAPI(event: EventAPI): Event {
    // calculate date string
    val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    //val eds = LocalDate.parse(event.startDate)
    //val ede = LocalDate.parse(event.endDate)

    return Event(
        id = event.id,
        title = event.name,
        description = event.description,
        images = event.photos,
        dateStart = LocalDate.fromEpochDays((event.startDate / 86400).toInt()),
        dateEnd = LocalDate.fromEpochDays((event.endDate / 86400).toInt()),
        //dateString = " ${today.daysUntil(eds)} дней (${eds.dayOfMonth}.${eds.monthNumber} - ${ede.dayOfMonth}.${ede.monthNumber})",
        dateString = "Осталось X дней",
        categories = event.category.map { it },
        isUnread = true,
        phone = event.phone,
        address = event.address,
        organisation = event.organisation,
    )
}

fun EventAPI.toDomainModel() = createEventFromAPI(this)

fun List<EventAPI>.toDomainModelList() = this.map { createEventFromAPI(it) }