package com.yara.core.domain.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class SendNotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        sendNotification()
        return Result.success()
    }

    private fun sendNotification() {
        println("!!! send notification")
    }
}