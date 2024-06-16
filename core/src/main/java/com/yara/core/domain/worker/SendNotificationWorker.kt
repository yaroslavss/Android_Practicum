package com.yara.core.domain.worker

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.yara.core.R
import com.yara.core.utils.Constants.CHANNEL_ID

class SendNotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        val eventId = inputData.getInt("eventId", 0)
        val eventTitle = inputData.getString("eventTitle") ?: ""
        val amount = inputData.getInt("amount", 0)

        sendNotification(eventId, eventTitle, amount)

        return Result.success()
    }

    private fun sendNotification(eventId: Int, eventTitle: String, amount: Int) {
        println("!!! send notification: $eventId, $eventTitle, $amount")

        var builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(eventTitle)
            .setContentText(applicationContext.getString(R.string.notification_text_format, amount))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(applicationContext)

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            println("!!! not permitted to send notifications")
            return
        }

        val notificationId = eventId + 1
        notificationManager.notify(notificationId, builder.build())
    }
}