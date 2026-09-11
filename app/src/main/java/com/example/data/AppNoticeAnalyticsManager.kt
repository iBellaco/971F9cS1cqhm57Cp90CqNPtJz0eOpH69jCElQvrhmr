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

    private val _metricsMap = MutableStateFlow<Map<String, NoticeMetrics>>(emptyMap())
    val metricsMap: StateFlow<Map<String, NoticeMetrics>> = _metricsMap.asStateFlow()

    private val _baseCpmRate = MutableStateFlow(2.50) // $2.50 USD por defecto por cada 1,000 impresiones
    val baseCpmRate: StateFlow<Double> = _baseCpmRate.asStateFlow()

    private val _trackingStartDate = MutableStateFlow(System.currentTimeMillis())
    val trackingStartDate: StateFlow<Long> = _trackingStartDate.asStateFlow()

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

    @Synchronized
    fun recordImpression(context: Context, noticeId: String, noticeTag: String = "") {
        if (noticeId.isBlank()) return
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

        val sb = StringBuilder()
        sb.append("📊 REPORTE DE MONETIZACIÓN Y CPM - WILD RIFT COACH\n")
        sb.append("====================================================\n")
        sb.append("📅 Período: $startFormatted hasta $nowFormatted\n")
        sb.append("💵 Tarifa Base CPM: $${String.format(Locale.US, "%.2f", cpm)} USD / 1,000 Impresiones\n")
        sb.append("👁️ Impresiones Totales: $totalImps\n")
        sb.append("🖱️ Clics Totales: $totalClicks (CTR: ${String.format(Locale.US, "%.2f", overallCtr)}%)\n")
        sb.append("📱 Pantalla Completa: $totalFullscreen vistas\n")
        sb.append("💰 Ingresos Estimados Totales: $${String.format(Locale.US, "%.2f", totalRev)} USD\n")
        sb.append("====================================================\n")
        sb.append("DESGLOSE POR ANUNCIO / CAMPAÑA:\n")

        for (n in notices) {
            val m = _metricsMap.value[n.id] ?: NoticeMetrics(n.id)
            val rev = m.calculateRevenue(cpm)
            sb.append("\n• [${n.tag.uppercase()}] ${n.title}\n")
            sb.append("  - Impresiones: ${m.impressions}\n")
            sb.append("  - Clics: ${m.clicks} (CTR: ${String.format(Locale.US, "%.2f", m.ctr)}%)\n")
            sb.append("  - Fullscreen: ${m.fullscreenViews}\n")
            sb.append("  - Generado: $${String.format(Locale.US, "%.2f", rev)} USD\n")
        }

        return sb.toString()
    }
}
