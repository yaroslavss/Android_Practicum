package com.yara.android_practicum

import android.app.Application
import com.yara.android_practicum.di.AppComponent
import com.yara.android_practicum.di.DaggerAppComponent
import com.yara.feature_help.di.HelpComponent
import com.yara.feature_help.di.HelpComponentProvider
import com.yara.feature_news.di.NewsComponent
import com.yara.feature_news.di.NewsComponentProvider

class App : Application(), NewsComponentProvider, HelpComponentProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // init App
        instance = this
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {

        // static
        lateinit var instance: App
            // private setter
            private set
    }

    override fun getNewsComponent(): NewsComponent {
        return appComponent
    }

    override fun getHelpComponent(): HelpComponent {
        return appComponent
    }
}