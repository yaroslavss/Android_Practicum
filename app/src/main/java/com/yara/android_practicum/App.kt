package com.yara.android_practicum

import android.app.Application
import java.util.concurrent.Executors

class App : Application() {

    var executorService = Executors.newFixedThreadPool(THREADS_COUNT)

    override fun onCreate() {
        super.onCreate()

        // init App
        instance = this
    }

    companion object {

        const val THREADS_COUNT = 4

        // static
        lateinit var instance: App
            // private setter
            private set
    }
}