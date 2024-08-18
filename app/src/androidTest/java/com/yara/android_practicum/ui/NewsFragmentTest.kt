package com.yara.android_practicum.ui

import android.R
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.yara.android_practicum.test_data.NewsViewModelTestData
import com.yara.feature_news.ui.NewsFragment
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    @Test
    fun newsFragment_DisplayedInUi() {
        /* Given */
        val events = NewsViewModelTestData.testEvents

        /* When */
        val bundle = Bundle()
        launchFragmentInContainer<NewsFragment>(
            bundle,
            R.style.Theme_WithActionBar
        )
    }
}