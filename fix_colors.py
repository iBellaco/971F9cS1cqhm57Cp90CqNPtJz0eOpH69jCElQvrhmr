import re

with open('app/src/main/java/com/example/ui/theme/Color.kt', 'r') as f:
    content = f.read()

if "TierBColor" not in content:
    content = content.replace(
        "val TierAColor = Color(0xFFF59E0B)",
        "val TierAColor = Color(0xFFF59E0B)\nval TierBColor = Color(0xFF3B82F6)\nval TierCColor = Color(0xFF9CA3AF)\nval TierDColor = Color(0xFFEF4444)"
    )

with open('app/src/main/java/com/example/ui/theme/Color.kt', 'w') as f:
    f.write(content)
