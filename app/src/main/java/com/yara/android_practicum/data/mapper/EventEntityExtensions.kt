package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.App
import com.yara.android_practicum.R
import com.yara.android_practicum.data.db.entity.EventEntity
import com.yara.android_practicum.data.model.EventAPI
import com.yara.android_practicum.domain.model.Event
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

private val tz = TimeZone.currentSystemDefault()

private fun createEventFromEntity(eventEntity: EventEntity): Event {
    // calculate date string
    val context = App.instance
    val today: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val eds = eventEntity.startDate
    val ede = eventEntity.endDate

    return Event(
        id = eventEntity.id,
        title = eventEntity.title,
        description = eventEntity.description,
        images = eventEntity.photos.split(", "),
        dateStart = eventEntity.startDate,
        dateEnd = eventEntity.endDate,
        dateString = context.getString(
            R.string.event_date_string_format,
            today.daysUntil(eds),
            eds.dayOfMonth,
            eds.monthNumber,
            ede.dayOfMonth,
            ede.monthNumber
        ),
        categories = eventEntity.category.split(", ").map { it.toInt() },
        isUnread = eventEntity.isUnread,
        phone = eventEntity.phone,
        address = eventEntity.address,
        organisation = eventEntity.organisation,
    )
}

fun EventEntity.toDomainModel() = createEventFromEntity(this)

fun List<EventEntity>.toDomainModelList() = this.map { createEventFromEntity(it) }

private fun createEventEntityFromAPI(event: EventAPI) =
    EventEntity(
        id = event.id,
        title = event.name,
        description = event.description,
        startDate = Instant.fromEpochSeconds(event.startDate).toLocalDateTime(tz).date,
        endDate = Instant.fromEpochSeconds(event.endDate).toLocalDateTime(tz).date,
        status = event.status,
        photos = event.photos.joinToString(),
        category = event.category.joinToString(),
        isUnread = true,
        createAt = Instant.fromEpochSeconds(event.createAt).toLocalDateTime(tz).date,
        phone = event.phone,
        address = event.address,
        organisation = event.organisation,
    )

fun EventAPI.toEntity() = createEventEntityFromAPI(this)

fun List<EventAPI>.toEntityList() = this.map { createEventEntityFromAPI(it) }