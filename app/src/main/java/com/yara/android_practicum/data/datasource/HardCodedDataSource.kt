package com.yara.android_practicum.data.datasource

import com.yara.android_practicum.R
import com.yara.android_practicum.data.model.CategoryLocal
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

    /**
     * Simulates categories loading with small delay.
     */
    suspend fun loadCategories(): List<CategoryLocal> {
        delay(300L)
        return categories
    }
}