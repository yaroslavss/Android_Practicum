package com.yara.android_practicum.data.model

import com.google.gson.annotations.SerializedName

data class EventAPI(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("startDate")
    val startDate: Long,
    @SerializedName("endDate")
    val endDate: Long,
    @SerializedName("description")
    val description: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("photos")
    val photos: List<String>,
    @SerializedName("category")
    val category: List<Int>,
    @SerializedName("createAt")
    val createAt: Long,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("organisation")
    val organisation: String,
)