package com.yara.feature_filter.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.repository.EventsRepository
import com.yara.core.domain.usecase.FilterEventsByTitleUseCase
import com.yara.core.domain.usecase.GetAllEventsWithCategoriesUseCase
import com.yara.core.domain.usecase.GetEventByIdUseCase
import com.yara.core.domain.usecase.GetEventsByCategoriesUseCase
import com.yara.core.domain.usecase.UpdateEventSetReadUseCase
import com.yara.feature_filter.test_data.NewsViewModelTestData.testCategories
import com.yara.feature_filter.test_data.NewsViewModelTestData.testEvents
import com.yara.feature_filter.test_data.NewsViewModelTestData.testEventsFilteredByCategories
import com.yara.feature_filter.test_data.NewsViewModelTestData.testEventsWithCategories
import com.yara.feature_filter.test_utils.MainCoroutineRule
import com.yara.feature_news.ui.NewsUiState
import com.yara.feature_news.ui.NewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
            onBlocking { it.queryEventsWithCategoriesFromDB() } doReturn flowOf(
                testEventsWithCategories
            )
            onBlocking { it.queryEventsByCategoriesFromDB(arrayOf(1)) } doReturn flowOf(
                testEventsFilteredByCategories
            )
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

    @Test
    fun initCategories_fetchesAllCategories() = runTest {
        newsViewModel.uiState.test {
            assertEquals(
                NewsUiState(
                    categories = testCategories,
                    events = testEvents,
                    unreadNewsQnt = 2,
                    filters = mutableSetOf(1, 2),
                    isLoading = false,
                ), awaitItem()
            )
        }
    }

    @Test
    fun removeFilter_fetchesEventsByCategories() = runTest {
        newsViewModel.removeFilter(2)

        newsViewModel.uiState.test {
            assertEquals(
                NewsUiState(
                    categories = testCategories,
                    events = testEvents,
                    unreadNewsQnt = 2,
                    filters = mutableSetOf(1),
                    isLoading = false,
                ), awaitItem()
            )
        }
    }
}