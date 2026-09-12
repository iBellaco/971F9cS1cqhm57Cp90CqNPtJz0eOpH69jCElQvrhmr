package com.example.data

import android.content.Context
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
    private const val PREFS_NAME = "wild_rift_notice_analytics_prefs"
    private const val KEY_METRICS_JSON = "metrics_json_map"
    private const val KEY_BASE_CPM = "base_cpm_rate_usd"
    private const val KEY_START_DATE = "tracking_start_date_ms"
    private const val KEY_DAILY_IMPRESSIONS_PREFIX = "daily_unique_imps_"
    private const val KEY_DAILY_CLICKS_PREFIX = "daily_unique_clicks_"
    private const val KEY_DAILY_FULLSCREEN_PREFIX = "daily_unique_full_"

    private val _metricsMap = MutableStateFlow<Map<String, NoticeMetrics>>(emptyMap())
    val metricsMap: StateFlow<Map<String, NoticeMetrics>> = _metricsMap.asStateFlow()

    private val _baseCpmRate = MutableStateFlow(2.50) // $2.50 USD por defecto por cada 1,000 impresiones
    val baseCpmRate: StateFlow<Double> = _baseCpmRate.asStateFlow()

    private val _trackingStartDate = MutableStateFlow(System.currentTimeMillis())
    val trackingStartDate: StateFlow<Long> = _trackingStartDate.asStateFlow()

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
        val suggestedPriceRange: Pair<Double, Double>
    )

    fun calculateRecommendedCpm(): DynamicCpmRecommendation {
        val totalImps = getTotalImpressions()
        val totalClicks = getTotalClicks()
        val totalFullscreen = getTotalFullscreenViews()
        val ctr = getOverallCtr()

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

        return DynamicCpmRecommendation(
            recommendedCpm = finalCpm,
            tierName = tier,
            marketBenchmarkMin = 1.50,
            marketBenchmarkMax = 4.80,
            ctrMultiplier = ctrFactor,
            engagementBonus = engagementBonus,
            reasoning = reasoning,
            suggestedPriceRange = Pair(minRange, maxRange)
        )
    }

    fun init(context: Context) {
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
     * Si alreadyRecordedToday=true, omite el conteo.
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
    }

    @Synchronized
    fun recordClick(context: Context, noticeId: String) {
        if (noticeId.isBlank()) return

        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val todayDate = SimpleDateFormat("yyyyMMdd", Locale.US).format(Date())
            val dailyKey = "$KEY_DAILY_CLICKS_PREFIX${todayDate}_$noticeId"
            
            // Si ya hizo clic hoy en este dispositivo para este anuncio, evitamos conteo inflado artificial (1 clic único por dispositivo al día)
            val alreadyClickedToday = prefs.getBoolean(dailyKey, false)
            if (alreadyClickedToday) {
                return
            }
            prefs.edit().putBoolean(dailyKey, true).apply()
        } catch (_: Exception) {}

        val current = _metricsMap.value.toMutableMap()
        val existing = current[noticeId] ?: NoticeMetrics(noticeId = noticeId)
        val updated = existing.copy(
            clicks = existing.clicks + 1,
            lastViewedTimestamp = System.currentTimeMillis()
        )
        current[noticeId] = updated
        _metricsMap.value = current
        saveToPrefs(context, current)
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
    }

    fun setBaseCpm(context: Context, rate: Double) {
        val safeRate = rate.coerceAtLeast(0.01)
        _baseCpmRate.value = safeRate
        try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putFloat(KEY_BASE_CPM, safeRate.toFloat()).apply()
        } catch (_: Exception) {}
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
        sb.append("📊 REPORTE DE MONETIZACIÓN Y CPM - WILD RIFT COACH\n")
        sb.append("====================================================\n")
        sb.append("📅 Período: $startFormatted hasta $nowFormatted\n")
        sb.append("💵 Tarifa Configurada: $${String.format(Locale.US, "%.2f", cpm)} USD / 1,000 Imp.\n")
        sb.append("🤖 Tarifa Recomendada IA: $${String.format(Locale.US, "%.2f", dynamicRec.recommendedCpm)} USD (${dynamicRec.tierName})\n")
        sb.append("📈 Rango de Venta Sugerido: $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.first)} - $${String.format(Locale.US, "%.2f", dynamicRec.suggestedPriceRange.second)} USD\n")
        sb.append("👁️ Impresiones Únicas Totales: $totalImps (1x disp/día)\n")
        sb.append("🖱️ Clics Únicos Totales: $totalClicks (CTR: ${String.format(Locale.US, "%.2f", overallCtr)}%)\n")
        sb.append("📱 Pantalla Completa: $totalFullscreen vistas\n")
        sb.append("💰 Ingresos Estimados Totales: $${String.format(Locale.US, "%.2f", totalRev)} USD\n")
        sb.append("====================================================\n")
        sb.append("DESGLOSE POR ANUNCIO / CAMPAÑA:\n")

        for (n in notices) {
            val m = _metricsMap.value[n.id] ?: NoticeMetrics(n.id)
            val rev = m.calculateRevenue(cpm)
            sb.append("\n• [${n.tag.uppercase()}] ${n.title}\n")
            sb.append("  - Imp. Únicas: ${m.impressions}\n")
            sb.append("  - Clics Únicos: ${m.clicks} (CTR: ${String.format(Locale.US, "%.2f", m.ctr)}%)\n")
            sb.append("  - Fullscreen: ${m.fullscreenViews}\n")
            sb.append("  - Generado: $${String.format(Locale.US, "%.2f", rev)} USD\n")
        }

        return sb.toString()
    }
}
