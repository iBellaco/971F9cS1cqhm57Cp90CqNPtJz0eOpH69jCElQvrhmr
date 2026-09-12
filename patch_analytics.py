import re

with open('app/src/main/java/com/example/data/AppNoticeAnalyticsManager.kt', 'r') as f:
    content = f.read()

# Modify NoticeMetrics calculateRevenue to take a media multiplier
old_calc = """    fun calculateRevenue(globalCpmRate: Double): Double {
        val effectiveCpm = customCpmRate ?: globalCpmRate
        return (impressions.toDouble() / 1000.0) * effectiveCpm
    }"""
new_calc = """    fun calculateRevenue(globalCpmRate: Double, mediaMultiplier: Double = 1.0): Double {
        val effectiveCpm = (customCpmRate ?: globalCpmRate) * mediaMultiplier
        return (impressions.toDouble() / 1000.0) * effectiveCpm
    }"""
content = content.replace(old_calc, new_calc)

# Modify getTotalRevenue to take notices
old_total_rev = """    fun getTotalRevenue(globalCpmRate: Double = _baseCpmRate.value): Double {
        return _metricsMap.value.values.sumOf { it.calculateRevenue(globalCpmRate) }
    }"""
new_total_rev = """    fun getTotalRevenue(globalCpmRate: Double = _baseCpmRate.value, notices: List<AppNotice>? = null): Double {
        return _metricsMap.value.values.sumOf { metrics -> 
            val notice = notices?.find { it.id == metrics.noticeId }
            val mediaMultiplier = if (notice != null && notice.videoUrl.isNotBlank()) {
                if (notice.videoUrl.contains("video") || notice.videoUrl.endsWith(".mp4") || notice.videoUrl.contains("youtube")) 2.5 // Video 10s = 2.5x base CPM
                else 1.5 // Image = 1.5x base CPM
            } else 1.0 // Plain text = 1x base CPM
            metrics.calculateRevenue(globalCpmRate, mediaMultiplier)
        }
    }"""
content = content.replace(old_total_rev, new_total_rev)

# In generateSummaryReport, pass notices to getTotalRevenue and calculate multiplier
old_summary_1 = """        val totalRev = getTotalRevenue(cpm)"""
new_summary_1 = """        val totalRev = getTotalRevenue(cpm, notices)"""
content = content.replace(old_summary_1, new_summary_1)

old_summary_2 = """            val m = _metricsMap.value[n.id] ?: NoticeMetrics(n.id)
            val rev = m.calculateRevenue(cpm)"""
new_summary_2 = """            val m = _metricsMap.value[n.id] ?: NoticeMetrics(n.id)
            val mediaMultiplier = if (n.videoUrl.isNotBlank()) {
                if (n.videoUrl.contains("video") || n.videoUrl.endsWith(".mp4") || n.videoUrl.contains("youtube")) 2.5
                else 1.5
            } else 1.0
            val rev = m.calculateRevenue(cpm, mediaMultiplier)"""
content = content.replace(old_summary_2, new_summary_2)

with open('app/src/main/java/com/example/data/AppNoticeAnalyticsManager.kt', 'w') as f:
    f.write(content)
