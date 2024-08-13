package com.yara.feature_news.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.yara.feature_news.test_data.NewsViewModelTestData.testEvent
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class EventDetailsFragmentTest {

    @Test
    fun eventDetailsFragment_DispayedInUi() {
        /* Given */
        val event = testEvent

        /* When */
        val bundle = Bundle()
        launchFragmentInContainer<EventDetailsFragment>(bundle, android.R.style.Theme_Material_NoActionBar)
    }
}