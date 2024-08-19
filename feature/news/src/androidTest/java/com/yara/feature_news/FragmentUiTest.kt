package com.yara.feature_news

import android.R
import android.os.Bundle
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.yara.feature_news.ui.NewsFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
    fun test() {
        composeTestRule.onNodeWithText("Новости").assertExists()
    }
}