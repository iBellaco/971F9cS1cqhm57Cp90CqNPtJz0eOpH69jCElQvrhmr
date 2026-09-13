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

data class SupportMessageEntry(
    val id: String = java.util.UUID.randomUUID().toString(),
    val senderName: String = "",
    val senderRole: String = "SUPPORT", // "SUPPORT" o "USER"
    val text: String = "",
    val timestampMillis: Long = System.currentTimeMillis(),
    val isGreeting: Boolean = false
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
    private const val KEY_CONVERSATION_PREFIX = "conversation_history_"

    const val DAYS_RETENTION_READ = 30
    const val DAYS_RETENTION_UNREAD = 60

    /**
     * Identifica si un texto coincide con el saludo predeterminado del soporte de Coach.
     */
    fun isDefaultGreeting(text: String): Boolean {
        val clean = text.trim()
        val isGreetingPrefix = clean.startsWith("👋 Hola", ignoreCase = true) || 
                               clean.startsWith("Hola", ignoreCase = true) ||
                               clean.startsWith("👋 Saludo", ignoreCase = true)
        val hasSupportMention = clean.contains("equipo de soporte", ignoreCase = true) ||
                                clean.contains("soporte de Coach", ignoreCase = true)
        val hasReceivedMention = clean.contains("recibido tu mensaje", ignoreCase = true) ||
                                 clean.contains("estamos para ayudarte", ignoreCase = true)
        return isGreetingPrefix && hasSupportMention && hasReceivedMention
    }

    /**
     * Determina si el usuario tiene permiso para responder.
     * Si no hay respuesta de soporte aún, o si la ÚNICA respuesta recibida de soporte
     * es el saludo predeterminado, el usuario no puede responder.
     * Tan pronto soporte envíe una respuesta real o seguimiento, el usuario puede responder.
     */
    fun canUserReply(messages: List<SupportMessageEntry>): Boolean {
        val supportMessages = messages.filter { 
            it.senderRole.equals("SUPPORT", ignoreCase = true) || it.senderRole.equals("ADMIN", ignoreCase = true)
        }
        if (supportMessages.isEmpty()) return false
        // Si todos los mensajes de soporte son saludos predeterminados, no puede responder
        val allAreGreetings = supportMessages.all { it.isGreeting || isDefaultGreeting(it.text) }
        return !allAreGreetings
    }

    /**
     * Comprueba si sólo hay saludos de bienvenida de parte de soporte.
     */
    fun isOnlyGreeting(messages: List<SupportMessageEntry>): Boolean {
        val supportMessages = messages.filter { 
            it.senderRole.equals("SUPPORT", ignoreCase = true) || it.senderRole.equals("ADMIN", ignoreCase = true)
        }
        if (supportMessages.isEmpty()) return false
        return supportMessages.all { it.isGreeting || isDefaultGreeting(it.text) }
    }

    fun getConversation(context: Context, reportId: String): List<SupportMessageEntry> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_CONVERSATION_PREFIX + reportId, null)
        if (!json.isNullOrBlank()) {
            try {
                val array = org.json.JSONArray(json)
                val list = mutableListOf<SupportMessageEntry>()
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)
                    list.add(
                        SupportMessageEntry(
                            id = obj.optString("id", java.util.UUID.randomUUID().toString()),
                            senderName = obj.optString("senderName", "Soporte"),
                            senderRole = obj.optString("senderRole", "SUPPORT"),
                            text = obj.optString("text", ""),
                            timestampMillis = obj.optLong("timestampMillis", System.currentTimeMillis()),
                            isGreeting = obj.optBoolean("isGreeting", false)
                        )
                    )
                }
                return list
            } catch (e: Exception) {
                Log.w(TAG, "Error parseando historial de conversación local: ${e.message}")
            }
        }
        // Fallback a respuesta única legacy si existía
        val legacyReply = getLocalReply(context, reportId)
        if (legacyReply != null && legacyReply.text.isNotBlank()) {
            val entry = SupportMessageEntry(
                senderName = legacyReply.author,
                senderRole = "SUPPORT",
                text = legacyReply.text,
                timestampMillis = legacyReply.timestampMillis,
                isGreeting = isDefaultGreeting(legacyReply.text)
            )
            return listOf(entry)
        }
        return emptyList()
    }

    fun saveConversation(context: Context, reportId: String, messages: List<SupportMessageEntry>) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val array = org.json.JSONArray()
        messages.forEach { m ->
            val obj = org.json.JSONObject()
            obj.put("id", m.id)
            obj.put("senderName", m.senderName)
            obj.put("senderRole", m.senderRole)
            obj.put("text", m.text)
            obj.put("timestampMillis", m.timestampMillis)
            obj.put("isGreeting", m.isGreeting || isDefaultGreeting(m.text))
            array.put(obj)
        }
        prefs.edit().putString(KEY_CONVERSATION_PREFIX + reportId, array.toString()).apply()
    }

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
            // 1. Guardar copia local permanente y actualizar historial de conversación
            val isGreeting = isDefaultGreeting(replyText)
            val newEntry = SupportMessageEntry(
                senderName = author,
                senderRole = "SUPPORT",
                text = replyText,
                timestampMillis = System.currentTimeMillis(),
                isGreeting = isGreeting
            )
            val currentConversation = getConversation(context, reportId) + newEntry
            saveConversation(context, reportId, currentConversation)
            saveLocalReply(context, reportId, replyText, author)

            val conversationListMap = currentConversation.map { m ->
                mapOf(
                    "id" to m.id,
                    "senderName" to m.senderName,
                    "senderRole" to m.senderRole,
                    "text" to m.text,
                    "timestampMillis" to m.timestampMillis,
                    "isGreeting" to m.isGreeting
                )
            }

            // 2. Si es documento en Firestore, actualizar el documento con el historial
            var resolvedUserId = ""
            var resolvedTitle = reportTitle ?: "Reporte de Soporte"
            var resolvedOriginalDesc = reportTitle ?: ""
            var resolvedSenderName = "Usuario"

            try {
                val db = FirebaseFirestore.getInstance()
                val docSnap = db.collection("support_reports").document(reportId).get().await()
                if (docSnap.exists()) {
                    resolvedUserId = docSnap.getString("userId") ?: ""
                    resolvedTitle = docSnap.getString("title") ?: resolvedTitle
                    resolvedOriginalDesc = docSnap.getString("description") ?: docSnap.getString("content") ?: resolvedOriginalDesc
                    resolvedSenderName = docSnap.getString("userName") ?: "Usuario"
                }

                val updateData = mutableMapOf<String, Any>(
                    "adminReply" to replyText,
                    "repliedAt" to Timestamp.now(),
                    "repliedBy" to author,
                    "conversation" to conversationListMap,
                    "lastMessageAt" to Timestamp.now()
                )
                if (markAsRead) {
                    updateData["status"] = "LEIDO"
                }
                db.collection("support_reports").document(reportId).set(updateData, com.google.firebase.firestore.SetOptions.merge()).await()
                Log.d(TAG, "Respuesta e historial sincronizados en Firestore para $reportId")
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
                if (resolvedUserId.isBlank() && !userEmail.isNullOrBlank()) {
                    // Buscar userId por email si no lo tenemos aún
                    val userQuery = db.collection("users").whereEqualTo("email", userEmail.trim()).limit(1).get().await()
                    if (!userQuery.isEmpty) {
                        resolvedUserId = userQuery.documents[0].id
                    }
                }

                if (resolvedUserId.isNotBlank() && resolvedUserId != "anonimo") {
                    val messageMap = hashMapOf<String, Any>(
                        "title" to "Soporte: $resolvedTitle",
                        "content" to resolvedOriginalDesc,
                        "description" to resolvedOriginalDesc,
                        "adminReply" to replyText,
                        "repliedBy" to author,
                        "timestamp" to System.currentTimeMillis(),
                        "isRead" to false,
                        "tag" to "SUPPORT",
                        "sender" to resolvedSenderName,
                        "reportId" to reportId,
                        "conversation" to conversationListMap
                    )
                    db.collection("users").document(resolvedUserId).collection("messages").document(reportId)
                        .set(messageMap, com.google.firebase.firestore.SetOptions.merge()).await()

                    val userRef = db.collection("users").document(resolvedUserId)
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

    suspend fun sendUserReply(
        context: Context,
        reportId: String,
        userReplyText: String,
        userName: String,
        userId: String? = null,
        userEmail: String? = null
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val newEntry = SupportMessageEntry(
                senderName = userName.takeIf { it.isNotBlank() && !it.contains("@") } ?: "Invocador",
                senderRole = "USER",
                text = userReplyText.trim(),
                timestampMillis = System.currentTimeMillis(),
                isGreeting = false
            )
            val currentConversation = getConversation(context, reportId) + newEntry
            saveConversation(context, reportId, currentConversation)

            val conversationListMap = currentConversation.map { m ->
                mapOf(
                    "id" to m.id,
                    "senderName" to m.senderName,
                    "senderRole" to m.senderRole,
                    "text" to m.text,
                    "timestampMillis" to m.timestampMillis,
                    "isGreeting" to m.isGreeting
                )
            }

            val db = FirebaseFirestore.getInstance()
            
            // 1. Actualizar el ticket de soporte marcándolo como PENDIENTE para que el admin lo atienda
            try {
                val updateData = hashMapOf<String, Any>(
                    "conversation" to conversationListMap,
                    "status" to "PENDIENTE",
                    "lastUserMessage" to userReplyText.trim(),
                    "lastUserMessageAt" to Timestamp.now(),
                    "updatedAt" to Timestamp.now()
                )
                db.collection("support_reports").document(reportId)
                    .set(updateData, com.google.firebase.firestore.SetOptions.merge()).await()
            } catch (e: Exception) {
                Log.w(TAG, "Error actualizando ticket de soporte con respuesta de usuario: ${e.message}")
            }

            // 2. Actualizar la bandeja de entrada del usuario
            val effectiveUserId = userId?.takeIf { it.isNotBlank() && it != "anonimo" }
                ?: com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
            if (!effectiveUserId.isNullOrBlank()) {
                try {
                    val userMsgUpdate = hashMapOf<String, Any>(
                        "conversation" to conversationListMap,
                        "timestamp" to System.currentTimeMillis(),
                        "isRead" to true
                    )
                    db.collection("users").document(effectiveUserId).collection("messages").document(reportId)
                        .set(userMsgUpdate, com.google.firebase.firestore.SetOptions.merge()).await()
                } catch (e: Exception) {
                    Log.w(TAG, "Error actualizando mensaje de usuario en su bandeja: ${e.message}")
                }
            }

            true
        } catch (e: Exception) {
            Log.e(TAG, "Error enviando respuesta de usuario: ${e.message}")
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
