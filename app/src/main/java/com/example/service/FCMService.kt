package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class FCMService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCMService", "Refreshed token: $token")
        // In a real app, we might want to send this to our backend.
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCMService", "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FCMService", "Message data payload: ${remoteMessage.data}")
            handleNotification(
                title = remoteMessage.data["title"] ?: "Actualización Premium",
                body = remoteMessage.data["body"] ?: "El meta ha cambiado."
            )
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d("FCMService", "Message Notification Body: ${it.body}")
            handleNotification(
                title = it.title ?: "Actualización Premium",
                body = it.body ?: "El meta ha cambiado."
            )
        }
    }

    private fun handleNotification(title: String, body: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "premium_updates_channel"
        Log.d("FCMService", "Showing FCM notification: $title - $body")
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Actualizaciones Premium",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notificaciones sobre el Meta y Tier List para usuarios Premium"
            }
            notificationManager.createNotificationChannel(channel)
        }

        try {
            notificationManager.notify(Random.nextInt(), notificationBuilder.build())
        } catch (e: SecurityException) {
            Log.e("FCMService", "Missing POST_NOTIFICATIONS permission", e)
        }
    }
}
