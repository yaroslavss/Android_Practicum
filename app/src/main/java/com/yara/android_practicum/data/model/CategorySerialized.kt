package com.yara.android_practicum.data.model

import com.google.gson.annotations.SerializedName

data class CategorySerialized(
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String
)