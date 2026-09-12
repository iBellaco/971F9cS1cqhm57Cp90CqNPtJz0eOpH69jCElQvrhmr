package com.example.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.example.data.supabase.FeedbackRepository
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class SupportReply(
    val reportId: String,
    val text: String,
    val author: String = "Equipo Coach",
    val timestampMillis: Long = System.currentTimeMillis()
)

data class AutoDeleteCountdown(
    val maxDays: Int,
    val remainingMillis: Long,
    val remainingDays: Long,
    val remainingHours: Long,
    val isExpired: Boolean,
    val displayText: String
)

object SupportReplyManager {
    private const val TAG = "SupportReplyManager"
    private const val PREFS_NAME = "support_replies_prefs"
    private const val KEY_REPLY_PREFIX = "reply_text_"
    private const val KEY_DATE_PREFIX = "reply_date_"
    private const val KEY_AUTHOR_PREFIX = "reply_author_"

    const val DAYS_RETENTION_READ = 30
    const val DAYS_RETENTION_UNREAD = 60

    /**
     * Calcula el tiempo restante de vida antes de la eliminación automática:
     * - 30 días si el mensaje está leído (o resuelto).
     * - 60 días si el mensaje está sin leer (pendiente).
     */
    fun calculateCountdown(createdAtMillis: Long, isRead: Boolean): AutoDeleteCountdown {
        val maxDays = if (isRead) DAYS_RETENTION_READ else DAYS_RETENTION_UNREAD
        val maxLifespanMillis = maxDays * 24L * 60 * 60 * 1000L
        val now = System.currentTimeMillis()
        val elapsed = now - createdAtMillis
        val remainingMillis = maxLifespanMillis - elapsed

        if (remainingMillis <= 0) {
            return AutoDeleteCountdown(
                maxDays = maxDays,
                remainingMillis = 0L,
                remainingDays = 0L,
                remainingHours = 0L,
                isExpired = true,
                displayText = "Expirado ($maxDays d)"
            )
        }

        val totalHours = remainingMillis / (1000 * 60 * 60)
        val days = totalHours / 24
        val hours = totalHours % 24

        val formattedTime = if (days > 0) {
            "${days}d ${hours}h"
        } else {
            "${hours}h"
        }

        return AutoDeleteCountdown(
            maxDays = maxDays,
            remainingMillis = remainingMillis,
            remainingDays = days,
            remainingHours = hours,
            isExpired = false,
            displayText = "$formattedTime ($maxDays d)"
        )
    }

    fun parseDateToMillis(dateStr: String?): Long {
        if (dateStr.isNullOrBlank()) return System.currentTimeMillis()
        dateStr.toLongOrNull()?.let { return it }
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val clean = dateStr.substringBefore(".").substringBefore("+").substringBefore("Z")
            sdf.parse(clean)?.time ?: System.currentTimeMillis()
        } catch (_: Exception) {
            System.currentTimeMillis()
        }
    }

    fun getLocalReply(context: Context, reportId: String): SupportReply? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val text = prefs.getString(KEY_REPLY_PREFIX + reportId, null) ?: return null
        val date = prefs.getLong(KEY_DATE_PREFIX + reportId, System.currentTimeMillis())
        val author = prefs.getString(KEY_AUTHOR_PREFIX + reportId, "Equipo Coach") ?: "Equipo Coach"
        return SupportReply(reportId, text, author, date)
    }

    fun saveLocalReply(context: Context, reportId: String, text: String, author: String = "Equipo Coach"): SupportReply {
        val now = System.currentTimeMillis()
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_REPLY_PREFIX + reportId, text)
            .putLong(KEY_DATE_PREFIX + reportId, now)
            .putString(KEY_AUTHOR_PREFIX + reportId, author)
            .apply()
        return SupportReply(reportId, text, author, now)
    }

    suspend fun sendSupportReply(
        context: Context,
        reportId: String,
        replyText: String,
        author: String = "Equipo Coach",
        userEmail: String? = null,
        reportTitle: String? = null,
        isFirestoreDoc: Boolean = false,
        markAsRead: Boolean = true
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            // 1. Guardar copia local permanente
            saveLocalReply(context, reportId, replyText, author)

            // 2. Si es documento en Firestore, actualizar el documento
            try {
                val db = FirebaseFirestore.getInstance()
                val updateData = mutableMapOf<String, Any>(
                    "adminReply" to replyText,
                    "repliedAt" to Timestamp.now(),
                    "repliedBy" to author
                )
                if (markAsRead) {
                    updateData["status"] = "LEIDO"
                }
                db.collection("support_reports").document(reportId).update(updateData).await()
                Log.d(TAG, "Respuesta sincronizada en soporte remoto para $reportId")
            } catch (e: Exception) {
                Log.w(TAG, "No se pudo actualizar respuesta en Firestore: ${e.message}")
            }

            // 3. Sincronizar estado en feedback repository si es necesario
            try {
                if (markAsRead) {
                    FeedbackRepository.updateFeedbackStatusInCloud(reportId, FeedbackRepository.STATUS_READ)
                }
            } catch (_: Exception) {}

            // 4. Notificar a la bandeja de entrada del usuario en Firestore si existe userId
            try {
                val db = FirebaseFirestore.getInstance()
                val docSnap = db.collection("support_reports").document(reportId).get().await()
                val userId = docSnap.getString("userId") ?: ""
                val title = docSnap.getString("title") ?: reportTitle ?: "Reporte de Soporte"
                if (userId.isNotBlank() && userId != "anonimo") {
                    val messageMap = hashMapOf<String, Any>(
                        "title" to "Respuesta de Soporte: $title",
                        "content" to replyText,
                        "timestamp" to Timestamp.now(),
                        "isRead" to false,
                        "tag" to "SUPPORT",
                        "sender" to author,
                        "reportId" to reportId
                    )
                    db.collection("users").document(userId).collection("messages").add(messageMap).await()

                    val userRef = db.collection("users").document(userId)
                    val userSnap = userRef.get().await()
                    val unreadCount = userSnap.getLong("unreadMessagesCount") ?: 0L
                    userRef.update(
                        "hasUnreadMessages", true,
                        "unreadMessagesCount", unreadCount + 1
                    ).await()
                }
            } catch (e: Exception) {
                Log.w(TAG, "No se pudo enviar mensaje a la bandeja del usuario: ${e.message}")
            }

            true
        } catch (e: Exception) {
            Log.e(TAG, "Error enviando respuesta de soporte: ${e.message}")
            false
        }
    }

    fun createEmailReplyIntent(email: String, title: String, replyText: String): Intent {
        val subject = "Respuesta de Soporte Coach: $title"
        val body = "Hola,\n\nHemos revisado tu mensaje de soporte: \"$title\"\n\n$replyText\n\nAtentamente,\nEquipo Coach"
        val uri = Uri.parse("mailto:${email.trim()}?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}")
        return Intent(Intent.ACTION_SENDTO, uri)
    }

    suspend fun autoPurgeAllExpired(context: Context): Int = withContext(Dispatchers.IO) {
        var totalPurged = 0
        val now = System.currentTimeMillis()

        // 1. Purga en Firestore de support_reports
        try {
            val db = FirebaseFirestore.getInstance()
            val snapshot = db.collection("support_reports").get().await()
            for (doc in snapshot.documents) {
                val status = doc.getString("status") ?: "PENDIENTE"
                val isRead = status.equals("LEIDO", ignoreCase = true) ||
                             status.equals("LEÍDO", ignoreCase = true) ||
                             status.equals("SOLUCIONADO", ignoreCase = true) ||
                             status.equals("SOLVED", ignoreCase = true) ||
                             status.equals("READ", ignoreCase = true)
                val maxDays = if (isRead) DAYS_RETENTION_READ else DAYS_RETENTION_UNREAD
                val maxLifespan = maxDays * 24L * 60 * 60 * 1000L
                val ts = doc.getTimestamp("createdAt")?.toDate()?.time ?: continue
                if (now - ts >= maxLifespan) {
                    try {
                        db.collection("support_reports").document(doc.id).delete().await()
                        totalPurged++
                        Log.d(TAG, "Reporte expirado eliminado de Firestore: ${doc.id}")
                    } catch (_: Exception) {}
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error en purga de reportes en Firestore: ${e.message}")
        }

        // 2. Purga en Supabase / Local Feedback
        try {
            val fbPurged = FeedbackRepository.autoPurgeExpiredReports(context)
            totalPurged += fbPurged
        } catch (_: Exception) {}

        totalPurged
    }
}
