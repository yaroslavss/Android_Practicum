package com.yara.feature_profile.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yara.feature_profile.test_data.ProfileViewModelTestData.testMenuItems
import com.yara.feature_profile.test_utils.MainCoroutineRule
import com.yara.feature_profile.test_utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProfileViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var profileViewModel: ProfileViewModel

    @Before
    fun initBeforeTest() {
        profileViewModel = ProfileViewModel()
    }

    @Test
    fun getMenuItemsListLiveData_fetchAllItems() {
        val items = profileViewModel.menuItemsListLiveData.getOrAwaitValue()
        assertEquals(testMenuItems, items)
    }
}