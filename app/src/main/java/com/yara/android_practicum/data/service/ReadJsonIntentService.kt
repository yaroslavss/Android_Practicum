package com.yara.android_practicum.data.service

import android.app.IntentService
import android.content.Intent
import com.yara.android_practicum.data.repository.EventsRepositoryImpl
import com.yara.android_practicum.data.util.AssetReaderImpl
import com.yara.android_practicum.data.util.EventDeserializer
import com.yara.android_practicum.utils.Constants

class ReadJsonIntentService : IntentService("ReadJsonIntentService") {

    private val eventsRepository = EventsRepositoryImpl(AssetReaderImpl(EventDeserializer))

    init {
        instance = this
    }

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        try {
            Thread.sleep(5000)
            val inputStream = this.assets.open(Constants.EVENTS_ASSET_FILENAME)
            val events = eventsRepository.readEvents(inputStream)
            println("!!! Service is running... Events: $events")
            sendBroadcast(Intent("SEND_EVENTS_ACTION"))
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