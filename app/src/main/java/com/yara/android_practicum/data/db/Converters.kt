package com.yara.android_practicum.data.db

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

private val tz = TimeZone.currentSystemDefault()

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let {
            val instant = Instant.fromEpochSeconds(value)
            instant.toLocalDateTime(tz).date
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.let {
            val ldt = LocalDateTime(date.year, date.monthNumber, date.dayOfMonth, 0, 0)
            ldt.toInstant(tz).epochSeconds
        }
    }
}