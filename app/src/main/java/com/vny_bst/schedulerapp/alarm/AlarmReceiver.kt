package com.vny_bst.schedulerapp.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.vny_bst.schedulerapp.R


class AlarmReceiver : BroadcastReceiver() {
    private val vibrationPattern = longArrayOf(1000, 1000)
    private var defaultSoundUri: Uri? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "Set_Reminder") {
            val task = intent.getStringExtra("task")
            if (task != null) {
                showNotification(context, task)
            }
        }
    }

    private fun showNotification(context: Context, task: String) {
        val channelId = "101"
        defaultSoundUri = Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName).path(R.raw.ringtone.toString()).build()
        Log.e("Default Sound", "$defaultSoundUri")
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_CALL)
            .setSound(defaultSoundUri)
            .setVibrate(vibrationPattern)
            .setDefaults(NotificationCompat.DEFAULT_LIGHTS)
            .setCustomContentView(getIncomingCallNotificationView(context, task))
            .build()
        notificationBuilder.flags = notificationBuilder.flags or NotificationCompat.FLAG_INSISTENT
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Reminder Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.vibrationPattern = vibrationPattern
            channel.enableVibration(true)
            channel.setSound(
                defaultSoundUri,
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .build()
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(101, notificationBuilder)
    }

    private fun getIncomingCallNotificationView(context: Context, task: String): RemoteViews {
        val view = RemoteViews(context.packageName, R.layout.alarm_remote_view)
        view.setTextViewText(R.id.incoming_call_notification_content_text, task)
        return view
    }

}
