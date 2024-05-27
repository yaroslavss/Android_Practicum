package com.yara.feature_profile.data.repository

import com.yara.feature_profile.data.datasource.HardCodedDataSource
import com.yara.feature_profile.data.model.PhotoDialogMenuItemLocal
import com.yara.feature_profile.domain.repository.PhotoDialogMenuItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoDialogMenuItemsRepositoryImpl(private val dataSource: HardCodedDataSource) :
    PhotoDialogMenuItemsRepository {

    override suspend fun loadMenuItems(): List<PhotoDialogMenuItemLocal> =
        withContext(Dispatchers.IO) {
            dataSource.loadPhotoDialogMenuItems()
        }
}