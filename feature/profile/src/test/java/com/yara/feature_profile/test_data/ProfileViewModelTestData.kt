package com.yara.feature_profile.test_data

import com.yara.core.utils.Action
import com.yara.feature_profile.R
import com.yara.feature_profile.domain.model.PhotoDialogMenuItem

object ProfileViewModelTestData {

    val testMenuItems = listOf(
        PhotoDialogMenuItem(
            title = "Выбрать фото",
            icon = R.drawable.ic_upload,
            action = Action.TakePhoto,
        ),
        PhotoDialogMenuItem(
            title = "Сделать снимок",
            icon = R.drawable.ic_camera,
            action = Action.MakeCameraPhoto,
        ),
        PhotoDialogMenuItem(
            title = "Удалить",
            icon = R.drawable.ic_delete,
            action = Action.DeleteProfilePhoto,
        ),
    )
}