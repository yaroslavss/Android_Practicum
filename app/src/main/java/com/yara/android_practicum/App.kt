package com.yara.android_practicum

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.yara.android_practicum.di.AppComponent
import com.yara.android_practicum.di.DaggerAppComponent
import com.yara.core.utils.Constants.CHANNEL_DESC
import com.yara.core.utils.Constants.CHANNEL_ID
import com.yara.core.utils.Constants.CHANNEL_NAME
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

        createNotificationChannel()
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

    private fun createNotificationChannel() {
        val name = CHANNEL_NAME
        val descriptionText = CHANNEL_DESC
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}