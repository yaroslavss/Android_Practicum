package com.yara.android_practicum.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.yara.android_practicum.test_data.NewsViewModelTestData.testEvent
import com.yara.feature_news.ui.EventDetailsFragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class EventDetailsFragmentTest {

    @Test
    fun eventDetailsFragment_DisplayedInUi() {
        /* Given */
        val event = testEvent

        /* When */
        val bundle = Bundle()
        launchFragmentInContainer<EventDetailsFragment>(bundle)
    }
}