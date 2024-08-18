package com.yara.android_practicum.ui

import android.R
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.yara.feature_profile.ui.ProfileFragment
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class ProfileFragmentTest {

    @Test
    fun profileFragment_DisplayedInUi() {
        /* When */
        val bundle = Bundle()
        launchFragmentInContainer<ProfileFragment>(bundle)
    }
}