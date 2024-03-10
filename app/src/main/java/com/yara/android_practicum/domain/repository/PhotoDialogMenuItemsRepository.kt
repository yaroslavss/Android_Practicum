package com.yara.android_practicum.domain.repository

import com.yara.android_practicum.data.model.PhotoDialogMenuItemLocal

interface PhotoDialogMenuItemsRepository {

    suspend fun loadMenuItems(): List<PhotoDialogMenuItemLocal>
}