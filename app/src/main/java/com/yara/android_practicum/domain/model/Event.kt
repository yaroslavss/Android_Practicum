package com.yara.android_practicum.domain.model

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val images: List<String>,
    val dateString: String,
    val categories: List<Int>,
)