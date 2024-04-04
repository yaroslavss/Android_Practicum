package com.yara.android_practicum.data.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class EventsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "SEND_EVENTS_ACTION") {
            println("!!! got events")
        }
    }
}