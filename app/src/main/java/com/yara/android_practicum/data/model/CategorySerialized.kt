package com.yara.android_practicum.data.model

import com.google.gson.annotations.SerializedName

data class CategorySerialized(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String
)