package com.yara.feature_news.test_data

import com.yara.core.data.db.entity.CategoryEntity
import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.relation.EventWithCategories
import com.yara.core.domain.model.Category
import com.yara.core.domain.model.Event
import kotlinx.datetime.LocalDate

object NewsViewModelTestData {

    val testCategories = listOf(
        Category(1, "Категория 1", "pic1.png"),
        Category(2, "Категория 2", "pic2.png"),
    )

    val testEvents = listOf(
        Event(
            id = 1,
            title = "Событие 1",
            description = "Описание события 1",
            images = listOf("image1.png", "image2.png"),
            dateStart = LocalDate(2024, 8, 26),
            dateEnd = LocalDate(2024, 8, 29),
            dateString = "Осталось 03 дней (26.08 - 29.08)",
            categories = listOf(1, 2),
            isUnread = true,
            phone = "+7 495 111 11 11",
            address = "Адрес",
            organisation = "Организация",
        ),
        Event(
            id = 2,
            title = "Событие 2",
            description = "Описание события 2",
            images = listOf("image1.png", "image2.png"),
            dateStart = LocalDate(2024, 8, 26),
            dateEnd = LocalDate(2024, 8, 29),
            dateString = "Осталось 03 дней (26.08 - 29.08)",
            categories = listOf(1, 2),
            isUnread = true,
            phone = "+7 495 111 11 11",
            address = "Адрес",
            organisation = "Организация",
        ),
    )

    val testEventsWithCategories = listOf(
        EventWithCategories(
            event = EventEntity(
                id = 1,
                title = "Событие 1",
                description = "Описание события 1",
                startDate = LocalDate(2024, 8, 26),
                endDate = LocalDate(2024, 8, 29),
                status = "status",
                photos = "image1.png, image2.png",
                category = "1, 2",
                isUnread = true,
                createAt = LocalDate(2024, 7, 29),
                phone = "+7 495 111 11 11",
                address = "Адрес",
                organisation = "Организация",
            ),
            categories = listOf(
                CategoryEntity(
                    id = 1,
                    name = "Дети",
                    nameEn = "Kids",
                    image = "https://loremflickr.com/640/480/abstract)"
                ),
                CategoryEntity(
                    id = 2,
                    name = "Взрослые",
                    nameEn = "Adult",
                    image = "https://loremflickr.com/640/480/abstract"
                ),
            )
        ),
        EventWithCategories(
            event = EventEntity(
                id = 2,
                title = "Событие 2",
                description = "Описание события 2",
                startDate = LocalDate(2024, 8, 26),
                endDate = LocalDate(2024, 8, 29),
                status = "status",
                photos = "image1.png, image2.png",
                category = "1, 2",
                isUnread = true,
                createAt = LocalDate(2024, 7, 29),
                phone = "+7 495 111 11 11",
                address = "Адрес",
                organisation = "Организация",
            ),
            categories = listOf(
                CategoryEntity(
                    id = 1,
                    name = "Дети",
                    nameEn = "Kids",
                    image = "https://loremflickr.com/640/480/abstract)"
                ),
                CategoryEntity(
                    id = 2,
                    name = "Взрослые",
                    nameEn = "Adult",
                    image = "https://loremflickr.com/640/480/abstract"
                ),
            )
        ),
    )
}