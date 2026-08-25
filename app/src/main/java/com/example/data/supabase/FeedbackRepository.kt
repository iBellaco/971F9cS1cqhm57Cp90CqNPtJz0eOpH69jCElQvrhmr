package com.example.data.supabase

import android.content.Context
import android.os.Build
import android.util.Log
import com.example.BuildConfig
import com.example.data.WildRiftRepository
import com.example.data.remote.model.FeedbackReport
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
private data class InsertFeedbackReport(
    val type: String,
    val title: String,
    val description: String,
    @SerialName("app_version") val appVersion: String,
    @SerialName("device_info") val deviceInfo: String,
    val status: String
)

object FeedbackRepository {

    private const val TAG = "FeedbackRepository"
    private const val TABLE_NAME = "feedbacks"
    private const val PREFS_NAME = "feedback_admin_prefs"
    private const val KEY_COMPLETED_IDS = "completed_feedback_ids"

    /**
     * Envía un reporte o sugerencia a Supabase y purga automáticamente
     * los reportes con más de [retentionDays] días de antigüedad.
     */
    suspend fun submitFeedback(
        type: String,
        title: String,
        description: String,
        imageBase64: String? = null,
        retentionDays: Int = 7
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val client = SupabaseClientManager.client
            val postgrest = client.postgrest

            // 1. Purgar reportes antiguos automáticamente (> 7 días)
            try {
                purgeOldReports(retentionDays)
            } catch (e: Exception) {
                Log.w(TAG, "No se pudo realizar la purga automática de reportes antiguos: ${e.message}")
            }

            // 2. Preparar el nuevo reporte
            val baseDeviceInfo = "${Build.MANUFACTURER} ${Build.MODEL} (Android ${Build.VERSION.RELEASE}, API ${Build.VERSION.SDK_INT})"
            val deviceInfo = if (imageBase64 != null) "$baseDeviceInfo\n\n[IMAGE_BASE64]\n$imageBase64" else baseDeviceInfo
            val appVersion = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) [${WildRiftRepository.CURRENT_PATCH_VERSION}]"

            val report = InsertFeedbackReport(
                type = type,
                title = title.trim(),
                description = description.trim(),
                appVersion = appVersion,
                deviceInfo = deviceInfo,
                status = "PENDING"
            )

            // 3. Insertar en la tabla feedbacks de Supabase
            postgrest.from(TABLE_NAME).insert(report)
            Log.d(TAG, "Feedback enviado exitosamente a Supabase")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error enviando feedback a Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Obtiene los IDs y claves de los reportes marcados como completados localmente.
     */
    fun getCompletedFeedbackIds(context: Context): Set<String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_COMPLETED_IDS, emptySet())?.toSet() ?: emptySet()
    }

    /**
     * Marca o desmarca un reporte como completado de forma persistente en SharedPreferences y
     * sincroniza el estado en la base de datos de Supabase si está disponible.
     */
    fun setFeedbackCompleted(
        context: Context,
        report: FeedbackReport,
        completed: Boolean
    ) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val currentSet = prefs.getStringSet(KEY_COMPLETED_IDS, emptySet())?.toMutableSet() ?: mutableSetOf()
        
        // Claves identificadoras del reporte (ID UUID, combinada título_fecha y título)
        val id = report.id
        val compositeKey = if (report.createdAt != null) "${report.title}_${report.createdAt}" else null
        val titleKey = report.title

        if (completed) {
            if (!id.isNullOrBlank()) currentSet.add(id)
            if (!compositeKey.isNullOrBlank()) currentSet.add(compositeKey)
            if (titleKey.isNotBlank()) currentSet.add("title:$titleKey")
        } else {
            if (!id.isNullOrBlank()) currentSet.remove(id)
            if (!compositeKey.isNullOrBlank()) currentSet.remove(compositeKey)
            if (titleKey.isNotBlank()) currentSet.remove("title:$titleKey")
        }

        prefs.edit().putStringSet(KEY_COMPLETED_IDS, currentSet).apply()
    }

    /**
     * Comprueba si un reporte está completado (ya sea por dato remoto de Supabase o guardado local permanente).
     */
    fun isReportCompleted(report: FeedbackReport, completedIds: Set<String>): Boolean {
        if (report.status.equals("COMPLETED", ignoreCase = true)) return true
        if (report.isCompleted == true) return true
        
        val id = report.id
        if (!id.isNullOrBlank() && completedIds.contains(id)) return true

        val compositeKey = if (report.createdAt != null) "${report.title}_${report.createdAt}" else null
        if (!compositeKey.isNullOrBlank() && completedIds.contains(compositeKey)) return true

        if (completedIds.contains("title:${report.title}")) return true

        return false
    }

    /**
     * Sincroniza el cambio de estado en la nube de Supabase.
     */
    suspend fun updateFeedbackStatusInCloud(id: String, completed: Boolean): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val client = SupabaseClientManager.client
            val postgrest = client.postgrest
            val statusString = if (completed) "COMPLETED" else "PENDING"

            // Intentar actualizar status e is_completed en Supabase
            try {
                postgrest.from(TABLE_NAME).update(
                    mapOf(
                        "status" to statusString,
                        "is_completed" to completed
                    )
                ) {
                    filter {
                        eq("id", id)
                    }
                }
            } catch (ignored: Exception) {
                // Fallback por si la columna is_completed no existe
                postgrest.from(TABLE_NAME).update(
                    mapOf("status" to statusString)
                ) {
                    filter {
                        eq("id", id)
                    }
                }
            }

            Log.d(TAG, "Estado de reporte $id actualizado en nube a $statusString")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.w(TAG, "No se pudo actualizar en nube el estado del reporte $id: ${e.message}")
            Result.failure(e)
        }
    }

    /**
     * Obtiene todos los reportes ordenados de más reciente a más antiguo para el Panel de Administrador.
     */
    suspend fun getAllFeedbacks(): Result<List<FeedbackReport>> = withContext(Dispatchers.IO) {
        try {
            val client = SupabaseClientManager.client
            val postgrest = client.postgrest

            val list = postgrest.from(TABLE_NAME)
                .select {
                    order("created_at", Order.DESCENDING)
                }
                .decodeList<FeedbackReport>()

            Log.d(TAG, "Se obtuvieron ${list.size} reportes de Supabase")
            Result.success(list)
        } catch (e: Exception) {
            Log.e(TAG, "Error al obtener feedbacks de Supabase: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Elimina un reporte específico por su ID UUID.
     */
    suspend fun deleteFeedback(id: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val client = SupabaseClientManager.client
            val postgrest = client.postgrest

            postgrest.from(TABLE_NAME).delete {
                filter {
                    eq("id", id)
                }
            }
            Log.d(TAG, "Reporte $id eliminado exitosamente")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error al eliminar reporte $id: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Elimina todos los reportes de la tabla.
     */
    suspend fun clearAllFeedbacks(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val client = SupabaseClientManager.client
            val postgrest = client.postgrest

            postgrest.from(TABLE_NAME).delete {
                filter {
                    neq("type", "___DUMMY_NEVER_MATCH___")
                }
            }
            Log.d(TAG, "Todos los reportes han sido eliminados")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error al limpiar todos los reportes: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Elimina manualmente o por mantenimiento los reportes con más de [days] días de antigüedad.
     */
    suspend fun purgeOldReports(days: Int = 7): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val client = SupabaseClientManager.client
            val postgrest = client.postgrest

            // Calcular fecha límite ISO-8601 (hace N días)
            val cutoffMillis = System.currentTimeMillis() - (days.toLong() * 24 * 60 * 60 * 1000L)
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val cutoffIso = sdf.format(Date(cutoffMillis))

            postgrest.from(TABLE_NAME).delete {
                filter {
                    lt("created_at", cutoffIso)
                }
            }
            Log.d(TAG, "Purga completada: eliminados reportes anteriores a $cutoffIso")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.w(TAG, "Fallo al purgar reportes antiguos: ${e.message}")
            Result.failure(e)
        }
    }
}
