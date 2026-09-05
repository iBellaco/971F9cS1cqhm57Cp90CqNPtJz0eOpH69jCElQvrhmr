import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# 1. Update createFloatingOverlay
create_old = """        val density = displayMetrics.density
        val marginPx = (8 * density).toInt()
        val cardWidthPx = (330 * density).toInt()
        val cardHeightPx = (520 * density).toInt()"""

create_new = """        val density = displayMetrics.density
        val marginPx = (8 * density).toInt()
        
        val isLandscape = displayMetrics.widthPixels > displayMetrics.heightPixels
        val cardWidthPx = ((if (isLandscape) 560 else 330) * density).toInt()
        val cardHeightPx = ((if (isLandscape) 360 else 520) * density).toInt()"""

text = text.replace(create_old, create_new)

# 2. Update onConfigurationChanged
config_old = """                val marginPx = (8 * density).toInt()
                val currentBubblePx = ((if (isCompactBubbleMode) 36f else 46f) * density).toInt()
                val cardWidthPx = (330 * density).toInt()
                val cardHeightPx = (520 * density).toInt()"""

config_new = """                val marginPx = (8 * density).toInt()
                val currentBubblePx = ((if (isCompactBubbleMode) 36f else 46f) * density).toInt()
                val isLandscape = metrics.widthPixels > metrics.heightPixels
                val cardWidthPx = ((if (isLandscape) 560 else 330) * density).toInt()
                val cardHeightPx = ((if (isLandscape) 360 else 520) * density).toInt()"""

text = text.replace(config_old, config_new)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
