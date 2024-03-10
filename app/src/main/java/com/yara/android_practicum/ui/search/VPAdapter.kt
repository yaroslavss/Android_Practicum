package com.yara.android_practicum.ui.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yara.android_practicum.ui.search.inner.SearchEventsFragment
import com.yara.android_practicum.ui.search.inner.SearchNpoFragment

class VPAdapter(fragmentActivity: SearchFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchEventsFragment()
            else -> SearchNpoFragment()
        }
    }
}