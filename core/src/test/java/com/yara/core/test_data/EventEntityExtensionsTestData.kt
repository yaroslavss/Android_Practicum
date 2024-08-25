package com.yara.core.test_data

import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.model.EventAPI
import com.yara.core.domain.model.Event
import kotlinx.datetime.LocalDate

object EventEntityExtensionsTestData {

    val testEventAPI = EventAPI(
        id = 1,
        name = "Событие 1",
        description = "Описание события 1",
        photos = listOf("image1.png", "image2.png"),
        startDate = 1724616000,
        endDate = 1724875200,
        status = "status",
        category = listOf(1, 2),
        phone = "+7 495 111 11 11",
        address = "Адрес",
        organisation = "Организация",
        createAt = 1723534522,
    )

    val testEventEntity = EventEntity(
        id = 1,
        title = "Событие 1",
        description = "Описание события 1",
        startDate = LocalDate(2024, 8, 26),
        endDate = LocalDate(2024, 8, 29),
        status = "status",
        photos = "image1.png, image2.png",
        category = "1, 2",
        isUnread = true,
        createAt = LocalDate(2024, 8, 13),
        phone = "+7 495 111 11 11",
        address = "Адрес",
        organisation = "Организация",
    )

    val testEventDomainModel = Event(
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
    )
}