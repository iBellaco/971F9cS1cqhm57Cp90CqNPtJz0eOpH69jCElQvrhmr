package com.example.util

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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.DocumentChange
import kotlin.random.Random

object PushNotificationListener {
    
    private var isListening = false
    private var startTime = System.currentTimeMillis()

    fun startListening(context: Context) {
        if (isListening) return
        isListening = true
        startTime = System.currentTimeMillis()

        FirebaseFirestore.getInstance().collection("global_notifications")
            .whereGreaterThan("createdAt", startTime)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.w("PushListener", "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (dc in snapshots!!.documentChanges) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        val title = dc.document.getString("title") ?: "Alerta"
                        val body = dc.document.getString("body") ?: "Nueva actualización"
                        val target = dc.document.getString("target") ?: "all"

                        // Check role if target is premium
                        if (target == "premium") {
                            val role = SubscriptionManager.userRole.value
                            if (role == "premium" || role == "admin") {
                                showLocalNotification(context, title, body)
                            }
                        } else {
                            showLocalNotification(context, title, body)
                        }
                    }
                }
            }
    }

    private fun showLocalNotification(context: Context, title: String, body: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = "premium_updates_channel"
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Actualizaciones Premium",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(Random.nextInt(), notificationBuilder.build())
    }
}
