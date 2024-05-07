package com.yara.android_practicum

import android.app.Application
import com.yara.android_practicum.di.AppComponent
import com.yara.android_practicum.di.DaggerAppComponent

class App : Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()

        // init App
        instance = this
        // create component
        dagger = DaggerAppComponent.factory().create(this)
    }

    companion object {

        // static
        lateinit var instance: App
            // private setter
            private set
    }
}