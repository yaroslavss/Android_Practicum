package com.yara.android_practicum.data.mapper

import com.yara.android_practicum.data.model.PhotoDialogMenuItemLocal
import com.yara.android_practicum.domain.model.PhotoDialogMenuItem

private fun createPhotoDialogMenuItemFromLocal(menuItem: PhotoDialogMenuItemLocal) =
    PhotoDialogMenuItem(
        title = menuItem.title,
        icon = menuItem.icon,
        action = menuItem.action
    )

fun PhotoDialogMenuItemLocal.toDomainModel() = createPhotoDialogMenuItemFromLocal(this)

fun List<PhotoDialogMenuItemLocal>.toDomainModelList() =
    this.map { createPhotoDialogMenuItemFromLocal(it) }