package com.yara.feature_news.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.repository.EventsRepository
import com.yara.core.domain.usecase.FilterEventsByTitleUseCase
import com.yara.core.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.core.domain.usecase.GetEventByIdUseCase
import com.yara.core.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.core.domain.usecase.UpdateEventSetReadUseCase
import com.yara.feature_news.test_data.NewsViewModelTestData.testCategories
import com.yara.feature_news.test_data.NewsViewModelTestData.testEvents
import com.yara.feature_news.test_utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var categoriesRepositoryMock: CategoriesRepository

    private lateinit var eventsRepositoryMock: EventsRepository

    private lateinit var getAllEventsWithCategoriesUseCaseMock: GetAllEventsWithCategoriesUseCase

    private lateinit var getEventsByCategoriesUseCaseMock: GetEventsByCategoriesUseCase

    private lateinit var filterEventsByTitleUseCaseMock: FilterEventsByTitleUseCase

    private lateinit var updateEventSetReadUseCaseMock: UpdateEventSetReadUseCase

    private lateinit var getEventByIdUseCaseMock: GetEventByIdUseCase

    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun initBeforeTest() {
        categoriesRepositoryMock = mock() {
            onBlocking { queryCategoriesFromDB() } doReturn flowOf(testCategories)
        }
        eventsRepositoryMock = mock() {
            onBlocking { queryEventsFromDB() } doReturn flowOf(testEvents)
        }

        getAllEventsWithCategoriesUseCaseMock =
            GetAllEventsWithCategoriesUseCase(eventsRepositoryMock)
        getEventsByCategoriesUseCaseMock = GetEventsByCategoriesUseCase(eventsRepositoryMock)
        filterEventsByTitleUseCaseMock = FilterEventsByTitleUseCase(eventsRepositoryMock)
        updateEventSetReadUseCaseMock = UpdateEventSetReadUseCase(eventsRepositoryMock)
        getEventByIdUseCaseMock = GetEventByIdUseCase(eventsRepositoryMock)

        newsViewModel = NewsViewModel(
            categoriesRepositoryMock,
            getAllEventsWithCategoriesUseCaseMock,
            getEventsByCategoriesUseCaseMock,
            filterEventsByTitleUseCaseMock,
            updateEventSetReadUseCaseMock,
            getEventByIdUseCaseMock
        )
    }
}