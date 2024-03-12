package com.yara.android_practicum.data.datasource

import com.yara.android_practicum.R
import com.yara.android_practicum.data.model.CategoryLocal
import com.yara.android_practicum.data.model.PhotoDialogMenuItemLocal
import com.yara.android_practicum.utils.Action
import kotlinx.coroutines.delay

class HardCodedDataSource {

    private val categories = listOf(
        CategoryLocal(
            id = 1,
            name = "Дети",
            icon = R.drawable.icon_kids,
        ),
        CategoryLocal(
            id = 2,
            name = "Взрослые",
            icon = R.drawable.icon_adult,
        ),
        CategoryLocal(
            id = 3,
            name = "Пожилые",
            icon = R.drawable.icon_elderly,
        ),
        CategoryLocal(
            id = 4,
            name = "Животные",
            icon = R.drawable.icon_animals,
        ),
        CategoryLocal(
            id = 5,
            name = "Мероприятия",
            icon = R.drawable.icon_event,
        ),
    )

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
     * Simulates categories loading with small delay.
     */
    suspend fun loadCategories(): List<CategoryLocal> {
        delay(300L)
        return categories
    }

    /**
     * Simulates menu items loading with small delay.
     */
    suspend fun loadPhotoDialogMenuItems(): List<PhotoDialogMenuItemLocal> {
        delay(300L)
        return photoDialogMenuItems
    }
}