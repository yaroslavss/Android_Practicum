package com.yara.feature_news

import com.yara.core.data.db.entity.CategoryEntity
import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef
import com.yara.core.data.db.entity.relation.EventWithCategories
import com.yara.core.data.model.CategoryAPI
import com.yara.core.data.model.EventAPI
import com.yara.core.domain.model.Categories
import com.yara.core.domain.model.Category
import com.yara.core.domain.model.Event
import com.yara.core.domain.model.Events
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.repository.EventsRepository
import com.yara.core.utils.Resource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.LocalDate
import javax.inject.Singleton

@Module
class TestRepoModule {
    @Provides
    @Singleton
    fun provideCategoriesRepository(): CategoriesRepository = CategoriesRepoTest()

    @Provides
    @Singleton
    fun provideEventsRepository(): EventsRepository = EventsRepoTest()
}

class CategoriesRepoTest : CategoriesRepository {
    private val testCategoriesAPI = listOf(
        CategoryAPI(1, "Категория 1", "Категория 1", "pic1.png"),
        CategoryAPI(2, "Категория 2", "Категория 2", "pic2.png"),
    )

    private val testCategories = listOf(
        Category(1, "Категория 1", "pic1.png"),
        Category(2, "Категория 2", "pic2.png"),
    )

    override suspend fun getCategories(): Resource<List<CategoryAPI>> {
        return Resource.Success(testCategoriesAPI)
    }

    override suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>) {
    }

    override fun queryCategoriesFromDB(): Flow<Categories> {
        return flowOf(testCategories)
    }
}

class EventsRepoTest : EventsRepository {
    private val testEventsAPI = listOf(
        EventAPI(
            id = 1,
            name = "Событие 1",
            description = "Описание события 1",
            photos = listOf("image1.png", "image2.png"),
            startDate = 1724007817,
            endDate = 1724009817,
            category = listOf(1),
            phone = "+7 495 111 11 11",
            address = "Адрес",
            organisation = "Организация",
            createAt = 1723907017,
            status = ""
        ),
        EventAPI(
            id = 2,
            name = "Событие 2",
            description = "Описание события 2",
            photos = listOf("image1.png", "image2.png"),
            startDate = 1724007817,
            endDate = 1724009817,
            category = listOf(2),
            phone = "+7 495 111 11 11",
            address = "Адрес",
            organisation = "Организация",
            createAt = 1723907017,
            status = ""
        ),
    )

    private val testEvents = listOf(
        Event(
            id = 1,
            title = "Событие 1",
            description = "Описание события 1",
            images = listOf("image1.png", "image2.png"),
            dateStart = LocalDate(2024, 8, 15),
            dateEnd = LocalDate(2024, 8, 29),
            dateString = "Осталось 08 дней (15.08 - 29.08)",
            categories = listOf(1),
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
            dateStart = LocalDate(2024, 8, 15),
            dateEnd = LocalDate(2024, 8, 29),
            dateString = "Осталось 08 дней (15.08 - 29.08)",
            categories = listOf(2),
            isUnread = true,
            phone = "+7 495 111 11 11",
            address = "Адрес",
            organisation = "Организация",
        ),
    )

    private val testEventsWithCategories = listOf(
        EventWithCategories(
            event = EventEntity(
                id = 1,
                title = "Событие 1",
                description = "Описание события 1",
                startDate = LocalDate(2024, 8, 15),
                endDate = LocalDate(2024, 8, 29),
                status = "status",
                photos = "image1.png, image2.png",
                category = "1",
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
            )
        ),
        EventWithCategories(
            event = EventEntity(
                id = 2,
                title = "Событие 2",
                description = "Описание события 2",
                startDate = LocalDate(2024, 8, 15),
                endDate = LocalDate(2024, 8, 29),
                status = "status",
                photos = "image1.png, image2.png",
                category = "2",
                isUnread = true,
                createAt = LocalDate(2024, 7, 29),
                phone = "+7 495 111 11 11",
                address = "Адрес",
                organisation = "Организация",
            ),
            categories = listOf(
                CategoryEntity(
                    id = 2,
                    name = "Взрослые",
                    nameEn = "Adult",
                    image = "https://loremflickr.com/640/480/abstract"
                ),
            )
        ),
    )

    override suspend fun getEvents(): Resource<List<EventAPI>> {
        return Resource.Success(testEventsAPI)
    }

    override suspend fun insertEventListIntoDB(events: List<EventEntity>) {
    }

    override suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef) {
    }

    override suspend fun updateEventIsUnread(event: EventUpdateIsUnreadEntity) {
    }

    override fun queryEventsFromDB(): Flow<Events> {
        return flowOf(testEvents)
    }

    override fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>> {
        return flowOf(testEventsWithCategories)
    }

    override fun queryEventsByCategoriesFromDB(categories: Array<Int>): Flow<Events> {
        return flowOf(testEvents)
    }

    override fun queryEventsByTitleFromDB(strToFind: String): Flow<Events> {
        return emptyFlow()
    }

    override fun queryEventByIdFromDB(eventId: Int): Flow<Event> {
        return emptyFlow()
    }
}