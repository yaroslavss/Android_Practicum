package com.yara.android_practicum.domain.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SendNotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val eventId = inputData.getInt("eventId", 0)
        val eventTitle = inputData.getString("eventTitle") ?: ""
        val amount = inputData.getInt("amount", 0)

        NotificationHelper.createNotification(
            applicationContext,
            mapOf(
                "eventId" to eventId.toString(),
                "eventTitle" to eventTitle,
                "amount" to amount.toString()
            )
        )

        return Result.success()
    }
}