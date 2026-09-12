import re

with open('app/src/main/java/com/example/ui/components/AdminCpmAnalyticsDialog.kt', 'r') as f:
    content = f.read()

# Update totalRevenue
content = content.replace(
    "val totalRevenue = remember(totalImpressions, baseCpmRate) { AppNoticeAnalyticsManager.getTotalRevenue(baseCpmRate) }",
    "val totalRevenue = remember(totalImpressions, baseCpmRate, notices) { AppNoticeAnalyticsManager.getTotalRevenue(baseCpmRate, notices) }"
)

# Update individual revenue loop
old_individual_rev = """                                val revenue = metrics.calculateRevenue(baseCpmRate)"""
new_individual_rev = """                                val mediaMultiplier = if (notice.videoUrl.isNotBlank()) {
                                    if (notice.videoUrl.contains("video") || notice.videoUrl.endsWith(".mp4") || notice.videoUrl.contains("youtube")) 2.5
                                    else 1.5
                                } else 1.0
                                val revenue = metrics.calculateRevenue(baseCpmRate, mediaMultiplier)"""
content = content.replace(old_individual_rev, new_individual_rev)

with open('app/src/main/java/com/example/ui/components/AdminCpmAnalyticsDialog.kt', 'w') as f:
    f.write(content)
