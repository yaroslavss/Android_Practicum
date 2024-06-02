package com.yara.core.data.db

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class Converters {

    private val tz = TimeZone.currentSystemDefault()

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