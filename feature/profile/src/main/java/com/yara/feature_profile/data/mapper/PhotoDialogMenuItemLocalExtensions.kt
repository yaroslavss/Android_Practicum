package com.yara.feature_profile.data.mapper

import com.yara.feature_profile.data.model.PhotoDialogMenuItemLocal
import com.yara.feature_profile.domain.model.PhotoDialogMenuItem

private fun createPhotoDialogMenuItemFromLocal(menuItem: PhotoDialogMenuItemLocal) =
    PhotoDialogMenuItem(
        title = menuItem.title,
        icon = menuItem.icon,
        action = menuItem.action
    )

fun PhotoDialogMenuItemLocal.toDomainModel() = createPhotoDialogMenuItemFromLocal(this)

fun List<PhotoDialogMenuItemLocal>.toDomainModelList() =
    this.map { createPhotoDialogMenuItemFromLocal(it) }