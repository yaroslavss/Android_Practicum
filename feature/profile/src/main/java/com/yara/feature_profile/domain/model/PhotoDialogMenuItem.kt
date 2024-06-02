package com.yara.feature_profile.domain.model

import com.yara.core.utils.Action

data class PhotoDialogMenuItem(
    val title: String,
    val icon: Int,
    val action: Action,
)