package com.yara.android_practicum.domain.model

import android.os.Parcelable
import kotlinx.datetime.LocalDate
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

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
) : Parcelable