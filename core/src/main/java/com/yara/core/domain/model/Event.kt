package com.yara.core.domain.model

import android.os.Parcelable
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

typealias Events = List<Event>

@Parcelize
data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val images: List<String>,
    val dateStart: @RawValue LocalDate,
    val dateEnd: @RawValue LocalDate,
    val dateString: String,
    val categories: List<Int>,
    var isUnread: Boolean,
    val phone: String,
    val address: String,
    val organisation: String,
) : Parcelable