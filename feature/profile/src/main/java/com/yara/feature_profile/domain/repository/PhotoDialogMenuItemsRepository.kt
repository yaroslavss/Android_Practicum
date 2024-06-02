package com.yara.feature_profile.domain.repository

import com.yara.feature_profile.data.model.PhotoDialogMenuItemLocal

interface PhotoDialogMenuItemsRepository {

    suspend fun loadMenuItems(): List<PhotoDialogMenuItemLocal>
}