package com.example.data

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class NoticeMetrics(
    val noticeId: String,
    val impressions: Long = 0,
    val clicks: Long = 0,
    val totalRawClicks: Long = 0,
    val fullscreenViews: Long = 0,
    val lastViewedTimestamp: Long = System.currentTimeMillis()
) {
    val ctr: Double
        get() = if (impressions > 0) (clicks.toDouble() / impressions.toDouble()) * 100.0 else 0.0

    fun calculateRevenue(cpmRate: Double): Double {
        return (impressions.toDouble() / 1000.0) * cpmRate
    }
}

object AppNoticeAnalyticsManager {
    private const val TAG = "AppNoticeAnalytics"
    private const val PREFS_NAME = "wild_rift_notice_analytics_prefs"
    private const val KEY_METRICS_JSON = "metrics_json_map"
    private const val KEY_BASE_CPM = "base_cpm_rate_usd"
    private const val KEY_START_DATE = "tracking_start_date_ms"
    private const val KEY_DAILY_IMPRESSIONS_PREFIX = "daily_unique_imps_"
    private const val KEY_DAILY_CLICKS_PREFIX = "daily_unique_clicks_"
    private const val KEY_DAILY_FULLSCREEN_PREFIX = "daily_unique_full_"

    private const val FIRESTORE_COLLECTION = "system_config"
    private const val FIRESTORE_DOC_ANALYTICS = "app_notice_analytics"

    private var firestoreListener: ListenerRegistration? = null
    private var authStateListener: FirebaseAuth.AuthStateListener? = null
    private var isAuthenticatingAnonymously = false

    private val _metricsMap = MutableStateFlow<Map<String, NoticeMetrics>>(emptyMap())
    val metricsMap: StateFlow<Map<String, NoticeMetrics>> = _metricsMap.asStateFlow()

    private val _baseCpmRate = MutableStateFlow(2.50) // $2.50 USD por defecto por cada 1,000 impresiones
    val baseCpmRate: StateFlow<Double> = _baseCpmRate.asStateFlow()

    private val _trackingStartDate = MutableStateFlow(System.currentTimeMillis())
    val trackingStartDate: StateFlow<Long> = _trackingStartDate.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    private val _lastSyncTime = MutableStateFlow(0L)
    val lastSyncTime: StateFlow<Long> = _lastSyncTime.asStateFlow()

    /**
     * Modelo de cálculo inteligente de CPM en tiempo real.
     * Evalúa las métricas de rendimiento reales del app (CTR, ratio de fullscreen, volumen de impresiones)
     * junto con los benchmarks de la industria en apps móviles de eSports y gaming.
     */
    data class DynamicCpmRecommendation(
        val recommendedCpm: Double,
        val tierName: String,
        val marketBenchmarkMin: Double,
        val marketBenchmarkMax: Double,
        val ctrMultiplier: Double,
        val engagementBonus: Double,
        val reasoning: String,
        val suggestedPriceRange: Pair<Double, Double>,
        val price1Day: Double = 0.0,
        val price3Days: Double = 0.0,
        val price1Week: Double = 0.0,
        val price1Month: Double = 0.0,
        val price1Year: Double = 0.0
    )

    fun calculateRecommendedCpm(): DynamicCpmRecommendation {
        val totalImps = getTotalImpressions()
        val totalClicks = getTotalClicks()
        val totalFullscreen = getTotalFullscreenViews()
        val ctr = getOverallCtr()
        val millisTracked = System.currentTimeMillis() - _trackingStartDate.value
        val daysTracked = maxOf(1L, millisTracked / (1000 * 60 * 60 * 24)).toDouble()
        
        // Estimar impresiones diarias (min 50 para cálculo)
        val avgDailyImps = maxOf(totalImps.toDouble() / daysTracked, 50.0)

        // Base de mercado eSports Gaming en LATAM/Global: $1.20 - $4.50 USD CPM
        val baseMarketCpm = 2.20

        // Factor por CTR (rendimiento directo de clics únicos)
        // CTR promedio en gaming display: ~1.0% a 1.8%. Si supera el 2.5%, el espacio vale considerablemente más.
        val ctrFactor = when {
            ctr >= 6.0 -> 2.20  // Excepcional (High Conversion)
            ctr >= 4.0 -> 1.75  // Muy alto
            ctr >= 2.5 -> 1.40  // Sólido / Superior a la media
            ctr >= 1.2 -> 1.10  // Promedio saludable
            ctr > 0.0  -> 0.90  // Inicial / Bajo CTR
            else       -> 1.00  // Sin datos aún
        }

        // Factor por engagement de pantalla completa (retención visual y apertura de videos/imágenes)
        val fullscreenRatio = if (totalImps > 0) (totalFullscreen.toDouble() / totalImps.toDouble()) else 0.0
        val engagementBonus = when {
            fullscreenRatio >= 0.15 -> 0.60 // 15%+ de usuarios ven en pantalla completa (+ $0.60 USD)
            fullscreenRatio >= 0.08 -> 0.35 // 8%+ (+ $0.35 USD)
            fullscreenRatio >= 0.03 -> 0.15
            else -> 0.0
        }

        // Factor por masa crítica de impresiones (audiencia acumulada)
        val volumeFactor = when {
            totalImps >= 10000 -> 1.25 // Audiencia verificada alta
            totalImps >= 2500  -> 1.15
            totalImps >= 500   -> 1.05
            else               -> 1.00
        }

        // Cálculo dinámico final redondeado a 2 decimales
        val rawCpm = (baseMarketCpm * ctrFactor * volumeFactor) + engagementBonus
        val finalCpm = (Math.round(rawCpm * 100.0) / 100.0).coerceIn(0.80, 15.00)

        val tier = when {
            finalCpm >= 5.50 -> "Premium High-Impact"
            finalCpm >= 3.50 -> "Tier 1 - Alto Rendimiento"
            finalCpm >= 2.00 -> "Estándar Competitivo"
            else -> "Fase Inicial / Crecimiento"
        }

        val reasoning = when {
            ctr >= 3.0 && fullscreenRatio >= 0.08 -> "Tus usuarios interactúan activamente (CTR ${String.format(Locale.US, "%.1f", ctr)}% y alto fullscreen). El inventario califica como espacio patrocinado de alto valor."
            ctr >= 1.5 -> "CTR saludable (${String.format(Locale.US, "%.1f", ctr)}%) alineado con los estándares de apps gaming competitivas."
            totalImps < 50 -> "Datos iniciales. Recomendamos un CPM base de entrada ($2.00 - $2.50 USD) para atraer anunciantes y recopilar estadísticas."
            else -> "Audiencia en desarrollo. Optimiza la calidad gráfica y llamadas a la acción (CTA) para elevar el CTR y el valor del espacio."
        }

        val minRange = (Math.round((finalCpm * 0.85) * 100.0) / 100.0).coerceAtLeast(0.50)
        val maxRange = (Math.round((finalCpm * 1.25) * 100.0) / 100.0)
        
        // Cálculo de precios proyectados basados en impresiones diarias promedio
        val calcPrice = { days: Double -> (avgDailyImps * days / 1000.0) * finalCpm }
        // Descuentos progresivos por volumen de tiempo
        val price1Day = Math.round(calcPrice(1.0) * 100.0) / 100.0
        val price3Days = Math.round(calcPrice(3.0) * 0.95 * 100.0) / 100.0
        val price1Week = Math.round(calcPrice(7.0) * 0.90 * 100.0) / 100.0
        val price1Month = Math.round(calcPrice(30.0) * 0.80 * 100.0) / 100.0
        val price1Year = Math.round(calcPrice(365.0) * 0.65 * 100.0) / 100.0

        return DynamicCpmRecommendation(
            recommendedCpm = finalCpm,
            tierName = tier,
            marketBenchmarkMin = 1.50,
            marketBenchmarkMax = 4.80,
            ctrMultiplier = ctrFactor,
            engagementBonus = engagementBonus,
            reasoning = reasoning,
            suggestedPriceRange = Pair(minRange, maxRange),
            price1Day = price1Day,
            price3Days = price3Days,
            price1Week = price1Week,
            price1Month = price1Month,
            price1Year = price1Year
        )
    }

    fun init(context: Context) {
        val appContext = context.applicationContext
        // 1. Cargar caché local de inmediato (garantiza disponibilidad offline instantánea)
        loadFromLocalStorage(appContext)

        // 2. Monitorear cambios de sesión/autenticación para mantener activo el listener
        setupAuthStateListener(appContext)

        // 3. Conectar a la sincronización en la nube multi-dispositivo
        ensureAuthAndSync(appContext)
    }

    private fun setupAuthStateListener(context: Context) {
        if (authStateListener != null) return
        try {
            val auth = FirebaseAuth.getInstance()
            authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
                val user = firebaseAuth.currentUser
                if (user == null) {
                    ensureAuthAndSync(context)
                } else {
                    attachFirestoreListener(context, force = true)
                    syncFromCloud(context)
                }
            }
            auth.addAuthStateListener(authStateListener!!)
        } catch (e: Exception) {
            Log.w(TAG, "Error en setupAuthStateListener: ${e.message}")
        }
    }

    private fun ensureAuthAndSync(context: Context) {
        val appContext = context.applicationContext
        com.example.util.GuestAuthHelper.ensureAuth {
            attachFirestoreListener(appContext, force = true)
            executeCloudFetch(appContext, null)
        }
    }

    fun attachFirestoreListener(context: Context, force: Boolean = false) {
        if (force) {
            try {
                firestoreListener?.remove()
            } catch (_: Exception) {}
            firestoreListener = null
        }
        if (firestoreListener != null) return
        try {
            val db = FirebaseFirestore.getInstance()
            firestoreListener = db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w(TAG, "Error escuchando analíticas en la nube: ${error.message}")
                        try {
                            firestoreListener?.remove()
                        } catch (_: Exception) {}
                        firestoreListener = null
                        com.example.util.GuestAuthHelper.ensureAuth {
                            attachFirestoreListener(context, force = false)
                        }
                        return@addSnapshotListener
                    }
                    if (snapshot != null && snapshot.exists()) {
                        processFirestoreSnapshot(context, snapshot)
                    }
                }
        } catch (e: Exception) {
            Log.e(TAG, "No se pudo iniciar listener de analíticas: ${e.message}")
            firestoreListener = null
        }
    }

    fun syncFromCloud(context: Context, onComplete: ((Boolean) -> Unit)? = null) {
        val appContext = context.applicationContext
        val auth = try { FirebaseAuth.getInstance() } catch (_: Exception) { null }
        if (auth?.currentUser == null) {
            com.example.util.GuestAuthHelper.ensureAuth {
                executeCloudFetch(appContext, onComplete)
            }
        } else {
            executeCloudFetch(appContext, onComplete)
        }
    }

    private fun executeCloudFetch(appContext: Context, onComplete: ((Boolean) -> Unit)?) {
        _isSyncing.value = true
        try {
            val db = FirebaseFirestore.getInstance()
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .get()
                .addOnSuccessListener { snapshot ->
                    _isSyncing.value = false
                    if (snapshot != null && snapshot.exists()) {
                        processFirestoreSnapshot(appContext, snapshot)
                        _lastSyncTime.value = System.currentTimeMillis()
                        onComplete?.invoke(true)
                    } else {
                        pushLocalToCloud(appContext)
                        _lastSyncTime.value = System.currentTimeMillis()
                        onComplete?.invoke(true)
                    }
                }
                .addOnFailureListener { e ->
                    _isSyncing.value = false
                    Log.w(TAG, "Error forzando sincronización de analíticas: ${e.message}")
                    onComplete?.invoke(false)
                }
        } catch (e: Exception) {
            _isSyncing.value = false
            Log.e(TAG, "Excepción en syncFromCloud: ${e.message}")
            onComplete?.invoke(false)
        }
    }

    private fun processFirestoreSnapshot(context: Context, snapshot: com.google.firebase.firestore.DocumentSnapshot) {
        try {
            val cloudBaseCpm = snapshot.getDouble("baseCpmRate")
            val cloudStartDate = snapshot.getLong("trackingStartDate")

            if (cloudBaseCpm != null && cloudBaseCpm > 0.0) {
                _baseCpmRate.value = cloudBaseCpm
                saveBaseCpmToPrefs(context, cloudBaseCpm)
            }
            if (cloudStartDate != null && cloudStartDate > 0L) {
                _trackingStartDate.value = cloudStartDate
                saveStartDateToPrefs(context, cloudStartDate)
            }

            val metricsRaw = snapshot.get("metrics") as? Map<*, *>
            if (metricsRaw != null) {
                val current = _metricsMap.value.toMutableMap()
                for ((k, v) in metricsRaw) {
                    val noticeId = k?.toString() ?: continue
                    val map = v as? Map<*, *> ?: continue
                    val imps = (map["impressions"] as? Number)?.toLong() ?: 0L
                    val clicks = (map["clicks"] as? Number)?.toLong() ?: 0L
                    val rawClicks = (map["totalRawClicks"] as? Number)?.toLong() ?: 0L
                    val full = (map["fullscreenViews"] as? Number)?.toLong() ?: 0L
                    val lastViewed = (map["lastViewedTimestamp"] as? Number)?.toLong() ?: System.currentTimeMillis()

                    val localExisting = current[noticeId]
                    val mergedImps = maxOf(imps, localExisting?.impressions ?: 0L)
                    val mergedClicks = maxOf(clicks, localExisting?.clicks ?: 0L)
                    val mergedRawClicks = maxOf(rawClicks, localExisting?.totalRawClicks ?: 0L)
                    val mergedFull = maxOf(full, localExisting?.fullscreenViews ?: 0L)
                    val mergedLastViewed = maxOf(lastViewed, localExisting?.lastViewedTimestamp ?: 0L)

                    current[noticeId] = NoticeMetrics(
                        noticeId = noticeId,
                        impressions = mergedImps,
                        clicks = mergedClicks,
                        totalRawClicks = mergedRawClicks,
                        fullscreenViews = mergedFull,
                        lastViewedTimestamp = mergedLastViewed
                    )
                }
                _metricsMap.value = current
                saveToPrefs(context, current)
                _lastSyncTime.value = System.currentTimeMillis()
                Log.d(TAG, "Métricas publicitarias sincronizadas exitosamente en tiempo real (${current.size} anuncios).")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error procesando snapshot de analíticas: ${e.message}")
        }
    }

    private fun pushLocalToCloud(context: Context) {
        try {
            val db = FirebaseFirestore.getInstance()
            val metricsData = hashMapOf<String, Any>()
            for ((k, v) in _metricsMap.value) {
                metricsData[k] = hashMapOf(
                    "noticeId" to v.noticeId,
                    "impressions" to v.impressions,
                    "clicks" to v.clicks,
                    "totalRawClicks" to v.totalRawClicks,
                    "fullscreenViews" to v.fullscreenViews,
                    "lastViewedTimestamp" to v.lastViewedTimestamp
                )
            }
            val data = hashMapOf<String, Any>(
                "baseCpmRate" to _baseCpmRate.value,
                "trackingStartDate" to _trackingStartDate.value,
                "metrics" to metricsData,
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .set(data, SetOptions.merge())
        } catch (e: Exception) {
            Log.w(TAG, "Error subiendo analíticas locales a la nube: ${e.message}")
        }
    }

    private fun loadFromLocalStorage(context: Context) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val baseCpm = prefs.getFloat(KEY_BASE_CPM, 2.50f).toDouble()
            val startDate = prefs.getLong(KEY_START_DATE, System.currentTimeMillis())
            val jsonStr = prefs.getString(KEY_METRICS_JSON, null)

            _baseCpmRate.value = baseCpm
            _trackingStartDate.value = startDate

            if (!jsonStr.isNullOrBlank()) {
                val root = JSONObject(jsonStr)
                val map = mutableMapOf<String, NoticeMetrics>()
                val keys = root.keys()
                while (keys.hasNext()) {
                    val key = keys.next()
                    val obj = root.getJSONObject(key)
                    map[key] = NoticeMetrics(
                        noticeId = key,
                        impressions = obj.optLong("impressions", 0L),
                        clicks = obj.optLong("clicks", 0L),
                        totalRawClicks = obj.optLong("totalRawClicks", 0L),
                        fullscreenViews = obj.optLong("fullscreenViews", 0L),
                        lastViewedTimestamp = obj.optLong("lastViewedTimestamp", System.currentTimeMillis())
                    )
                }
                _metricsMap.value = map
            }
        } catch (_: Exception) {}
    }

    /**
     * Registra una impresión única (una sola vez por dispositivo al día).
     * Si alreadyRecordedToday=true, omite el conteo local y en la nube.
     */
    @Synchronized
    fun recordImpression(context: Context, noticeId: String, noticeTag: String = "") {
        if (noticeId.isBlank()) return
        
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val todayDate = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())
            val dailyKey = "$KEY_DAILY_IMPRESSIONS_PREFIX${todayDate}_$noticeId"
            
            // Comprobamos si ya fue contabilizado hoy en este dispositivo
            val alreadyCountedToday = prefs.getBoolean(dailyKey, false)
            if (alreadyCountedToday) {
                return
            }
            
            // Marcar como contabilizado para hoy
            prefs.edit().putBoolean(dailyKey, true).apply()
        } catch (_: Exception) {}

        val current = _metricsMap.value.toMutableMap()
        val existing = current[noticeId] ?: NoticeMetrics(noticeId = noticeId)
        val updated = existing.copy(
            impressions = existing.impressions + 1,
            lastViewedTimestamp = System.currentTimeMillis()
        )
        current[noticeId] = updated
        _metricsMap.value = current
        saveToPrefs(context, current)

        // Sincronizar incremento atómico en la nube para todos los dispositivos
        try {
            val db = FirebaseFirestore.getInstance()
            val updates = hashMapOf<String, Any>(
                "metrics.$noticeId.impressions" to FieldValue.increment(1L),
                "metrics.$noticeId.noticeId" to noticeId,
                "metrics.$noticeId.tag" to noticeTag,
                "metrics.$noticeId.lastViewedTimestamp" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .set(updates, SetOptions.merge())
        } catch (e: Exception) {
            Log.w(TAG, "Error incrementando impresión en la nube: ${e.message}")
        }
    }

    @Synchronized
    fun recordClick(context: Context, noticeId: String) {
        if (noticeId.isBlank()) return

        var incrementUniqueClick = false
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val todayDate = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())
            val dailyKey = "$KEY_DAILY_CLICKS_PREFIX${todayDate}_$noticeId"
            
            val alreadyClickedToday = prefs.getBoolean(dailyKey, false)
            if (!alreadyClickedToday) {
                prefs.edit().putBoolean(dailyKey, true).apply()
                incrementUniqueClick = true
            }
        } catch (_: Exception) {}

        val current = _metricsMap.value.toMutableMap()
        val existing = current[noticeId] ?: NoticeMetrics(noticeId = noticeId)
        val updated = existing.copy(
            clicks = existing.clicks + (if (incrementUniqueClick) 1 else 0),
            totalRawClicks = existing.totalRawClicks + 1,
            lastViewedTimestamp = System.currentTimeMillis()
        )
        current[noticeId] = updated
        _metricsMap.value = current
        saveToPrefs(context, current)

        try {
            val db = FirebaseFirestore.getInstance()
            val updates = hashMapOf<String, Any>(
                "metrics.$noticeId.totalRawClicks" to FieldValue.increment(1L),
                "metrics.$noticeId.noticeId" to noticeId,
                "metrics.$noticeId.lastViewedTimestamp" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
            )
            if (incrementUniqueClick) {
                updates["metrics.$noticeId.clicks"] = FieldValue.increment(1L)
            }
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .set(updates, SetOptions.merge())
        } catch (e: Exception) {
            Log.w(TAG, "Error incrementando clic en la nube: ${e.message}")
        }
    }

    @Synchronized
    fun recordFullscreen(context: Context, noticeId: String) {
        if (noticeId.isBlank()) return

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val todayDate = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())
            val dailyKey = "$KEY_DAILY_FULLSCREEN_PREFIX${todayDate}_$noticeId"
            
            val alreadyFullToday = prefs.getBoolean(dailyKey, false)
            if (alreadyFullToday) {
                return
            }
            prefs.edit().putBoolean(dailyKey, true).apply()
        } catch (_: Exception) {}

        val current = _metricsMap.value.toMutableMap()
        val existing = current[noticeId] ?: NoticeMetrics(noticeId = noticeId)
        val updated = existing.copy(
            fullscreenViews = existing.fullscreenViews + 1,
            lastViewedTimestamp = System.currentTimeMillis()
        )
        current[noticeId] = updated
        _metricsMap.value = current
        saveToPrefs(context, current)

        // Sincronizar incremento atómico de vistas de pantalla completa en la nube
        try {
            val db = FirebaseFirestore.getInstance()
            val updates = hashMapOf<String, Any>(
                "metrics.$noticeId.fullscreenViews" to FieldValue.increment(1L),
                "metrics.$noticeId.noticeId" to noticeId,
                "metrics.$noticeId.lastViewedTimestamp" to System.currentTimeMillis(),
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .set(updates, SetOptions.merge())
        } catch (e: Exception) {
            Log.w(TAG, "Error incrementando fullscreen en la nube: ${e.message}")
        }
    }

    fun setBaseCpm(context: Context, rate: Double) {
        val safeRate = rate.coerceAtLeast(0.01)
        _baseCpmRate.value = safeRate
        saveBaseCpmToPrefs(context, safeRate)

        // Sincronizar tarifa CPM en la nube para todos los dispositivos
        try {
            val db = FirebaseFirestore.getInstance()
            val updates = hashMapOf<String, Any>(
                "baseCpmRate" to safeRate,
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .set(updates, SetOptions.merge())
        } catch (e: Exception) {
            Log.w(TAG, "Error sincronizando CPM en la nube: ${e.message}")
        }
    }

    fun resetMetrics(context: Context) {
        _metricsMap.value = emptyMap()
        _trackingStartDate.value = System.currentTimeMillis()
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit()
                .putString(KEY_METRICS_JSON, "{}")
                .putLong(KEY_START_DATE, _trackingStartDate.value)
                .apply()
        } catch (_: Exception) {}

        // Resetear también en la nube para sincronización multi-dispositivo limpia
        try {
            val db = FirebaseFirestore.getInstance()
            val updates = hashMapOf<String, Any>(
                "metrics" to hashMapOf<String, Any>(),
                "trackingStartDate" to _trackingStartDate.value,
                "updatedAt" to System.currentTimeMillis()
            )
            db.collection(FIRESTORE_COLLECTION).document(FIRESTORE_DOC_ANALYTICS)
                .set(updates, SetOptions.merge())
        } catch (e: Exception) {
            Log.w(TAG, "Error reseteando métricas en la nube: ${e.message}")
        }
    }

    private fun saveBaseCpmToPrefs(context: Context, rate: Double) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putFloat(KEY_BASE_CPM, rate.toFloat()).apply()
        } catch (_: Exception) {}
    }

    private fun saveStartDateToPrefs(context: Context, date: Long) {
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putLong(KEY_START_DATE, date).apply()
        } catch (_: Exception) {}
    }

    private fun saveToPrefs(context: Context, map: Map<String, NoticeMetrics>) {
        try {
            val root = JSONObject()
            for ((key, metrics) in map) {
                val obj = JSONObject().apply {
                    put("impressions", metrics.impressions)
                    put("clicks", metrics.clicks)
                    put("fullscreenViews", metrics.fullscreenViews)
                    put("lastViewedTimestamp", metrics.lastViewedTimestamp)
                }
                root.put(key, obj)
            }
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_METRICS_JSON, root.toString()).apply()
        } catch (_: Exception) {}
    }

    fun getTotalImpressions(): Long = _metricsMap.value.values.sumOf { it.impressions }
    fun getTotalClicks(): Long = _metricsMap.value.values.sumOf { it.clicks }
    fun getTotalFullscreenViews(): Long = _metricsMap.value.values.sumOf { it.fullscreenViews }

    fun getTotalRevenue(cpmRate: Double = _baseCpmRate.value): Double {
        val totalImps = getTotalImpressions()
        return (totalImps.toDouble() / 1000.0) * cpmRate
    }

    fun getOverallCtr(): Double {
        val totalImps = getTotalImpressions()
        val totalClicks = getTotalClicks()
        return if (totalImps > 0) (totalClicks.toDouble() / totalImps.toDouble()) * 100.0 else 0.0
    }

    fun generateSummaryReport(notices: List<AppNotice>): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val startFormatted = sdf.format(Date(_trackingStartDate.value))
        val nowFormatted = sdf.format(Date())
        val totalImps = getTotalImpressions()
        val totalClicks = getTotalClicks()
        val totalFullscreen = getTotalFullscreenViews()
        val cpm = _baseCpmRate.value
        val totalRev = getTotalRevenue(cpm)
        val overallCtr = getOverallCtr()
        val dynamicRec = calculateRecommendedCpm()

        val sb = StringBuilder()
        sb.append("REPORTE DE MONETIZACION Y CPM - WILD RIFT COACH\n")
        sb.append("====================================================\n")
        sb.append("Periodo: $startFormatted hasta $nowFormatted\n")
        sb.append("Tarifa Configurada: $${String.format(Locale.US, "%.2f", cpm)} USD / 1,000 Imp.\n")
        sb.append("Tarifa Recomendada Inteligente: $${String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)} USD (${dynamicRec.tierName})\n")
        sb.append("Rango de Venta Sugerido: $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.first)} - $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.second)} USD\n")
        sb.append("Impresiones Unicas Totales: $totalImps (1x disp/dia)\n")
        sb.append("Clics Unicos Totales: $totalClicks (CTR: ${String.format(Locale.US, "%.2f", overallCtr)}%)\n")
        sb.append("Pantalla Completa: $totalFullscreen vistas\n")
        sb.append("Ingresos Estimados Totales: $${String.format(Locale.US, "%.2f", totalRev)} USD\n")
        sb.append("====================================================\n")
        sb.append("DESGLOSE POR ANUNCIO / CAMPANA:\n")

        for (n in notices) {
            val m = _metricsMap.value[n.id] ?: NoticeMetrics(n.id)
            val rev = m.calculateRevenue(cpm)
            sb.append("\n[${n.tag.uppercase()}] ${n.title}\n")
            sb.append("  - Imp. Unicas: ${m.impressions}\n")
            sb.append("  - Clics Unicos: ${m.clicks} (CTR: ${String.format(Locale.US, "%.2f", m.ctr)}%)\n")
            sb.append("  - Fullscreen: ${m.fullscreenViews}\n")
            sb.append("  - Generado: $${String.format(Locale.US, "%.2f", rev)} USD\n")
        }

        return sb.toString()
    }
}

