package com.yara.android_practicum.domain.model

import com.yara.android_practicum.utils.Action

data class PhotoDialogMenuItem(
    val title: String,
    val icon: Int,
    val action: Action,
)