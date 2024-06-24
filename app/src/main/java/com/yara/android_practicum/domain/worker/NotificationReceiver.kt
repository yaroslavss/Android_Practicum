package com.yara.android_practicum.domain.worker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.yara.core.utils.Constants
import java.util.concurrent.TimeUnit

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val eventId = intent?.getIntExtra(Constants.EXTRA_EVENT_ID, 0)
        val eventTitle = intent?.getStringExtra(Constants.EXTRA_EVENT_TITLE)
        val amount = intent?.getIntExtra(Constants.EXTRA_AMOUNT, 0)

        if (eventId != null && eventTitle != null && amount != null) {
            val sendNotificationWorkRequest =
                OneTimeWorkRequestBuilder<SendNotificationWorker>()
                    .setInitialDelay(Constants.SECOND_NOTIFICATION_DELAY, TimeUnit.MINUTES)
                    .setInputData(
                        workDataOf(
                            "eventId" to eventId,
                            "eventTitle" to eventTitle,
                            "amount" to amount,
                            "addAction" to false,
                        )
                    )
                    .build()

            WorkManager.getInstance(context!!).enqueue(sendNotificationWorkRequest)

            // cancel notification after tapping action button
            NotificationManagerCompat.from(context).cancel(eventId + 10)
        }
    }
}