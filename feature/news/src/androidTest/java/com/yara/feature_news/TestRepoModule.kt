package com.yara.feature_news

import com.yara.core.data.api.RemoteAPI
import com.yara.core.data.db.HelpDao
import com.yara.core.data.db.entity.CategoryEntity
import com.yara.core.data.db.entity.EventEntity
import com.yara.core.data.db.entity.EventUpdateIsUnreadEntity
import com.yara.core.data.db.entity.relation.EventCategoryCrossRef
import com.yara.core.data.db.entity.relation.EventWithCategories
import com.yara.core.data.model.CategoryAPI
import com.yara.core.data.model.EventAPI
import com.yara.core.data.repository.CategoriesRepositoryImpl
import com.yara.core.data.repository.EventsRepositoryImpl
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

class CategoriesRepoTest: CategoriesRepository {
    val testCategories = listOf(
        CategoryAPI(1, "Категория 1", "Категория 1", "pic1.png"),
        CategoryAPI(2, "Категория 2", "Категория 2","pic2.png"),
    )

    override suspend fun getCategories(): Resource<List<CategoryAPI>> {
        return Resource.Success(testCategories)
    }

    override suspend fun insertCategoryListIntoDB(categories: List<CategoryEntity>) {
    }

    override fun queryCategoriesFromDB(): Flow<Categories> {
        return emptyFlow()
    }
}

class EventsRepoTest: EventsRepository {
    val testEvents = listOf(
        EventAPI(
            id = 1,
            name = "Событие 1",
            description = "Описание события 1",
            photos = listOf("image1.png", "image2.png"),
            startDate = 1724007817,
            endDate = 1724009817,
            category = listOf(1, 2),
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
            category = listOf(1, 2),
            phone = "+7 495 111 11 11",
            address = "Адрес",
            organisation = "Организация",
            createAt = 1723907017,
            status = ""
        ),
    )

    override suspend fun getEvents(): Resource<List<EventAPI>> {
        return Resource.Success(testEvents)
    }

    override suspend fun insertEventListIntoDB(events: List<EventEntity>) {
    }

    override suspend fun insertEventCategoryCrossRefIntoDB(eventCategoryCrossRef: EventCategoryCrossRef) {
    }

    override suspend fun updateEventIsUnread(event: EventUpdateIsUnreadEntity) {
    }

    override fun queryEventsFromDB(): Flow<Events> {
        return emptyFlow()
    }

    override fun queryEventsWithCategoriesFromDB(): Flow<List<EventWithCategories>> {
        return emptyFlow()
    }

    override fun queryEventsByCategoriesFromDB(categories: Array<Int>): Flow<Events> {
        return emptyFlow()
    }

    override fun queryEventsByTitleFromDB(strToFind: String): Flow<Events> {
        return emptyFlow()
    }

    override fun queryEventByIdFromDB(eventId: Int): Flow<Event> {
        return emptyFlow()
    }

}