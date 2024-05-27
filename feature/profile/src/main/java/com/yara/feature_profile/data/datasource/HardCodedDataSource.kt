package com.yara.feature_profile.data.datasource

import com.yara.core.utils.Action
import com.yara.feature_profile.R
import com.yara.feature_profile.data.model.PhotoDialogMenuItemLocal
import kotlinx.coroutines.delay

class HardCodedDataSource {

    private val photoDialogMenuItems = listOf(
        PhotoDialogMenuItemLocal(
            title = "Выбрать фото",
            icon = R.drawable.ic_upload,
            action = Action.TakePhoto,
        ),
        PhotoDialogMenuItemLocal(
            title = "Сделать снимок",
            icon = R.drawable.ic_camera,
            action = Action.MakeCameraPhoto,
        ),
        PhotoDialogMenuItemLocal(
            title = "Удалить",
            icon = R.drawable.ic_delete,
            action = Action.DeleteProfilePhoto,
        ),
    )

    /**
     * Simulates menu items loading with small delay.
     */
    suspend fun loadPhotoDialogMenuItems(): List<PhotoDialogMenuItemLocal> {
        delay(300L)
        return photoDialogMenuItems
    }
}