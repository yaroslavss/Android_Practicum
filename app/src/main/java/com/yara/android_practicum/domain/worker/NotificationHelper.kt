package com.yara.android_practicum.domain.worker

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.yara.android_practicum.ui.MainActivity
import com.yara.core.R
import com.yara.core.utils.Constants

object NotificationHelper {

    private var eventId = 0
    private var eventTitle = ""
    private var amount = 0

    fun createNotification(context: Context, data: Map<String, String>) {
        eventId = data.get("eventId")?.toInt() ?: 0
        eventTitle = data.get("eventTitle") ?: ""
        amount = data.get("amount")?.toInt() ?: 0

        println("!!! create notification: $eventId, $eventTitle, $amount")

        // intent to open activity
        val intent = Intent(context, MainActivity::class.java)

        val flags = PendingIntent.FLAG_UPDATE_CURRENT or
                PendingIntent.FLAG_IMMUTABLE

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, flags)

        // intent to send another notification
        val intent1 = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("MESSAGE", "Clicked $eventId $eventTitle!")
        }

        val flags1 = PendingIntent.FLAG_IMMUTABLE

        val pendingIntent1 = PendingIntent.getBroadcast(context, 0, intent1, flags1)

        val builder = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(eventTitle)
            .setContentText(context.getString(R.string.notification_text_format, amount))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(
                        context.getString(
                            R.string.notification_text_format,
                            amount
                        )
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(0, "ACTION", pendingIntent1)

        val notificationManager = NotificationManagerCompat.from(context)

        if (ActivityCompat.checkSelfPermission(
                context,
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