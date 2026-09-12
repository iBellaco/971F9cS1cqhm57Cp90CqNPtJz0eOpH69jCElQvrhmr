import re

with open('app/src/main/java/com/example/ui/components/AdminCpmAnalyticsDialog.kt', 'r') as f:
    content = f.read()

# Fix tagRevenue
old_tag = """                                val tagRevenue = noticesInTag.sumOf { (metricsMap[it.id]?.calculateRevenue(baseCpmRate) ?: 0.0) }"""
new_tag = """                                val tagRevenue = noticesInTag.sumOf { 
                                    val mult = if (it.videoUrl.isNotBlank()) {
                                        if (it.videoUrl.contains("video") || it.videoUrl.endsWith(".mp4") || it.videoUrl.contains("youtube")) 2.5 else 1.5
                                    } else 1.0
                                    (metricsMap[it.id]?.calculateRevenue(baseCpmRate, mult) ?: 0.0) 
                                }"""
content = content.replace(old_tag, new_tag)

# Fix revenue inside individual row
old_revenue = """    val revenue = metrics.calculateRevenue(baseCpm)"""
new_revenue = """    val mediaMultiplier = if (notice.videoUrl.isNotBlank()) {
        if (notice.videoUrl.contains("video") || notice.videoUrl.endsWith(".mp4") || notice.videoUrl.contains("youtube")) 2.5 else 1.5
    } else 1.0
    val revenue = metrics.calculateRevenue(baseCpm, mediaMultiplier)"""
content = content.replace(old_revenue, new_revenue)

with open('app/src/main/java/com/example/ui/components/AdminCpmAnalyticsDialog.kt', 'w') as f:
    f.write(content)
