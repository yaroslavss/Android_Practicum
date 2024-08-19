package com.yara.feature_news

import android.app.Application
import com.yara.feature_news.di.NewsComponent
import com.yara.feature_news.di.NewsComponentProvider

class NewsTestApp: Application(), NewsComponentProvider {
    lateinit var component: NewsTestAppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerNewsTestAppComponent.factory().create()
    }

    override fun getNewsComponent(): NewsComponent {
        return component
    }
}