package com.yara.feature_help.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.yara.core.domain.repository.CategoriesRepository
import com.yara.core.domain.usecase.GetAllCategoriesUseCase
import com.yara.feature_help.test_data.HelpViewModelTestData.testCategories
import com.yara.feature_help.test_utils.MainCoroutineRule
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
class HelpViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var categoriesRepositoryMock: CategoriesRepository

    private lateinit var getAllCategoriesUseCaseMock: GetAllCategoriesUseCase

    private lateinit var helpViewModel: HelpViewModel

    @Before
    fun initBeforeTest() {
        categoriesRepositoryMock = mock() {
            onBlocking { queryCategoriesFromDB() } doReturn flowOf(testCategories)
        }
        getAllCategoriesUseCaseMock = GetAllCategoriesUseCase(categoriesRepositoryMock)
        helpViewModel = HelpViewModel(categoriesRepositoryMock, getAllCategoriesUseCaseMock)
    }

    @Test
    fun initCategories_fetchesAllCategories() = runTest {
        helpViewModel.uiState.test {
            assertEquals(CategoriesUiState.Success(testCategories), awaitItem())
        }
    }
}