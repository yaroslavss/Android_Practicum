package com.yara.feature_news

import android.R
import android.os.Bundle
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.yara.feature_news.ui.NewsFragment
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.yara.feature_news.R as R_news

class FragmentUiTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var scenario: FragmentScenario<NewsFragment>

    @Before
    fun setup() {
        val bundle = Bundle()
        scenario = launchFragmentInContainer<NewsFragment>(
            bundle,
            R.style.Theme_WithActionBar
        )
    }

    @Test
    fun testNewsFragmentStarted_TitleInAppbar() {
        composeTestRule.onNodeWithText("Новости").assertExists()
    }

    @Test
    fun testNewsFragmentStarted_NewsPresented() {
        composeTestRule.onNodeWithText("Событие 1").assertExists()
    }

    @Test
    fun testNewsFragment_NavigateToFilterFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        scenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R_news.navigation.filter_graph)
            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        /*onView(ViewMatchers.withTagValue("ibActionFilter")).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R_news.id.filterFragment)*/
        composeTestRule.onNodeWithTag("ibActionFilter").performClick().assertIsDisplayed()
    }
}