package com.yara.android_practicum.data.service

import android.app.IntentService
import android.content.Intent

class ReadJsonIntentService : IntentService("ReadJsonIntentService") {

    init {
        instance = this
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        try {
            isRunning = true
            while (isRunning) {
                println("!!! Service is running...")
                Thread.sleep(1000)
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
    }

    companion object {

        private lateinit var instance: ReadJsonIntentService
        var isRunning = false

        fun stopService() {
            println("!!! Service is stopping...")
            isRunning = false
            instance.stopSelf()
        }
    }
}