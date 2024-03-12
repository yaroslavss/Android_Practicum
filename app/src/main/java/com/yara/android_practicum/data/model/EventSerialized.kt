package com.yara.android_practicum.data.model

import com.google.gson.annotations.SerializedName

data class EventSerialized(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("date_string")
    val dateString: String,
    @SerializedName("categories")
    val categories: List<String>
)