package com.yara.android_practicum.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val images: List<String>,
    val dateString: String,
    val categories: List<Int>,
) : Parcelable