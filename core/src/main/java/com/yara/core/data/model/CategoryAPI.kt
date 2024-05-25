package com.yara.core.data.model

import com.google.gson.annotations.SerializedName

data class CategoryAPI(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
)