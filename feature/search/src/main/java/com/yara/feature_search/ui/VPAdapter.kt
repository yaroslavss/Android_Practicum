package com.yara.feature_search.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yara.feature_search.ui.inner.SearchEventsFragment
import com.yara.feature_search.ui.inner.SearchNpoFragment

class VPAdapter(fragmentActivity: SearchFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchEventsFragment()
            else -> SearchNpoFragment()
        }
    }
}