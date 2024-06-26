package com.yara.feature_news.di

import com.yara.feature_news.ui.EventDetailsFragment
import com.yara.feature_news.ui.NewsFragment

interface NewsComponent {

    fun injectNewsFragment(newsFragment: NewsFragment)

    fun injectEventDetailsFragment(eventDetailsFragment: EventDetailsFragment)
}