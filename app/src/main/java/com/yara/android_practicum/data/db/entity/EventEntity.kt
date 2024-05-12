package com.yara.android_practicum.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yara.android_practicum.utils.Constants.EVENTS_TABLE
import kotlinx.datetime.LocalDate

@Entity(tableName = EVENTS_TABLE)
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    @ColumnInfo(name = "start_date")
    val startDate: LocalDate,
    @ColumnInfo(name = "end_date")
    val endDate: LocalDate,
    val status: String,
    val photos: String,
    val category: String,   // list of categories
    @ColumnInfo(name = "is_unread")
    var isUnread: Boolean,
    @ColumnInfo(name = "create_at")
    val createAt: LocalDate,
    val phone: String,
    val address: String,
    val organisation: String,
)
