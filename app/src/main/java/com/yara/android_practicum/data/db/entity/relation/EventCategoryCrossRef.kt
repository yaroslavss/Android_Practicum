package com.yara.android_practicum.data.db.entity.relation

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yara.android_practicum.utils.Constants.EVENTS_CATEGORIES_TABLE

@Entity(
    tableName = EVENTS_CATEGORIES_TABLE,
    primaryKeys = ["event_id", "category_id"]
)
data class EventCategoryCrossRef(
    @ColumnInfo(name = "event_id")
    val eventId: Int,
    @ColumnInfo(
        name = "category_id",
        index = true,
    )
    val categoryId: Int,
)