package com.example.model

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class SubscriptionRecord(
    val id: String = "",
    val timestamp: Long = 0L,
    val durationMillis: Long = 0L,
    val planName: String = "",
    val status: String = "",
    val amount: String = ""
) {
    companion object {
        fun fromDocument(doc: DocumentSnapshot): SubscriptionRecord? {
            return try {
                val data = doc.data ?: return null

                // Parse timestamp with flexible support for Long, Double, Timestamp, Date, String
                val rawTimestamp = data["timestamp"] ?: data["created_at"] ?: data["date"] ?: data["time"]
                val timestamp: Long = when (rawTimestamp) {
                    is Number -> rawTimestamp.toLong()
                    is com.google.firebase.Timestamp -> rawTimestamp.toDate().time
                    is java.util.Date -> rawTimestamp.time
                    is String -> rawTimestamp.toLongOrNull() ?: 0L
                    else -> 0L
                }

                // Parse durationMillis with support for Long, Int, Double, String
                val rawDuration = data["durationMillis"] ?: data["duration_millis"] ?: data["duration"] ?: data["durationMs"]
                val durationMillis: Long = when (rawDuration) {
                    is Number -> rawDuration.toLong()
                    is String -> rawDuration.toLongOrNull() ?: 0L
                    else -> 0L
                }

                // Parse planName with multiple key variants
                val rawPlan = data["planName"] ?: data["plan_name"] ?: data["plan"] ?: data["name"] ?: data["title"]
                val planName = rawPlan?.toString()?.trim() ?: ""

                // Parse status
                val rawStatus = data["status"] ?: data["state"]
                val status = rawStatus?.toString()?.trim() ?: "Completado"

                // Parse amount / price
                val rawAmount = data["amount"] ?: data["price"] ?: data["cost"]
                val amount = when (rawAmount) {
                    is Number -> "$${rawAmount}"
                    is String -> if (rawAmount.isNotBlank()) rawAmount else "$0.00"
                    else -> "$0.00"
                }

                SubscriptionRecord(
                    id = doc.id,
                    timestamp = if (timestamp > 0L) timestamp else System.currentTimeMillis(),
                    durationMillis = durationMillis,
                    planName = planName.ifEmpty { "Suscripción Premium" },
                    status = status,
                    amount = amount
                )
            } catch (e: Exception) {
                Log.w("SubscriptionRecord", "Failed to parse subscription doc ${doc.id}", e)
                null
            }
        }
    }
}

