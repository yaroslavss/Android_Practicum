package com.yara.android_practicum.data.repository

import com.yara.android_practicum.data.datasource.HardCodedDataSource
import com.yara.android_practicum.data.model.PhotoDialogMenuItemLocal
import com.yara.android_practicum.domain.repository.PhotoDialogMenuItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoDialogMenuItemsRepositoryImpl(private val dataSource: HardCodedDataSource) :
    PhotoDialogMenuItemsRepository {

    override suspend fun loadMenuItems(): List<PhotoDialogMenuItemLocal> =
        withContext(Dispatchers.IO) {
            dataSource.loadPhotoDialogMenuItems()
        }
}