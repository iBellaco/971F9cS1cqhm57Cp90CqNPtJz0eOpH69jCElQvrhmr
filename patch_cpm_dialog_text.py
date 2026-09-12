import re

with open('app/src/main/java/com/example/ui/components/AdminCpmAnalyticsDialog.kt', 'r') as f:
    content = f.read()

old_text = """                        Text(if (metrics.customCpmRate != null) "Generado (CPM ★)" else "Generado", color = TextMuted, fontSize = 9.sp)"""
new_text = """                        val label = if (metrics.customCpmRate != null) "Generado (CPM ★)" else "Generado"
                        val multLabel = if (mediaMultiplier > 1.0) " [x${mediaMultiplier}]" else ""
                        Text(label + multLabel, color = TextMuted, fontSize = 9.sp)"""
content = content.replace(old_text, new_text)

with open('app/src/main/java/com/example/ui/components/AdminCpmAnalyticsDialog.kt', 'w') as f:
    f.write(content)
