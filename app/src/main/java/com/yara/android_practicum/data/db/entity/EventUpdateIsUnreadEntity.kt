package com.yara.android_practicum.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class EventUpdateIsUnreadEntity(
    val id: Int,
    @ColumnInfo(name = "is_unread")
    val isUnread: Boolean,
)