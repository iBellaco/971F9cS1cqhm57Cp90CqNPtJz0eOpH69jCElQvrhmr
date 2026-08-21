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

object FeedbackRepository {

    private const val TAG = "FeedbackRepository"
    private const val TABLE_NAME = "feedbacks"

    /**
     * Envía un reporte o sugerencia a Supabase y purga automáticamente
     * los reportes con más de [retentionDays] días de antigüedad.
     */
    suspend fun submitFeedback(
        type: String,
        title: String,
        description: String,
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
            val deviceInfo = "${Build.MANUFACTURER} ${Build.MODEL} (Android ${Build.VERSION.RELEASE}, API ${Build.VERSION.SDK_INT})"
            val appVersion = "v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) [${WildRiftRepository.CURRENT_PATCH_VERSION}]"

            val report = FeedbackReport(
                type = type,
                title = title.trim(),
                description = description.trim(),
                appVersion = appVersion,
                deviceInfo = deviceInfo
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
     * Obtiene los IDs de los reportes marcados como completados localmente.
     */
    fun getCompletedFeedbackIds(context: Context): Set<String> {
        val prefs = context.getSharedPreferences("feedback_admin_prefs", Context.MODE_PRIVATE)
        return prefs.getStringSet("completed_feedback_ids", emptySet()) ?: emptySet()
    }

    /**
     * Marca o desmarca un reporte como completado.
     */
    fun setFeedbackCompleted(context: Context, id: String, completed: Boolean) {
        val prefs = context.getSharedPreferences("feedback_admin_prefs", Context.MODE_PRIVATE)
        val currentSet = prefs.getStringSet("completed_feedback_ids", emptySet())?.toMutableSet() ?: mutableSetOf()
        if (completed) {
            currentSet.add(id)
        } else {
            currentSet.remove(id)
        }
        prefs.edit().putStringSet("completed_feedback_ids", currentSet).apply()
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

