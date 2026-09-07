import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

# 1. Remove the floatingParams block from the scan call inside LaunchedEffect (line 850+)
target1 = """                    if (bitmap != null) {
                        floatingParams?.let { params ->
                            val density = resources.displayMetrics.density
                            val isLandscape = resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels
                            val cWidth = if (isOverlayExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
                            val cHeight = if (isOverlayExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
                            val margin = (16 * density).toInt()
                            DraftVisionScanner.overlayRect = android.graphics.Rect(params.x - margin, params.y - margin, params.x + cWidth + margin, params.y + cHeight + margin)
                        } ?: run { DraftVisionScanner.overlayRect = null }
                        val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)"""

replacement1 = """                    if (bitmap != null) {
                        val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)"""

content = content.replace(target1, replacement1)

# 2. Remove it from the manual scan call (line 920+)
target2 = """            if (bitmap != null) {
                floatingParams?.let { params ->
                    val density = resources.displayMetrics.density
                    val isLandscape = resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels
                    val cWidth = if (isOverlayExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
                    val cHeight = if (isOverlayExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
                    val margin = (16 * density).toInt()
                    DraftVisionScanner.overlayRect = android.graphics.Rect(params.x - margin, params.y - margin, params.x + cWidth + margin, params.y + cHeight + margin)
                } ?: run { DraftVisionScanner.overlayRect = null }
                val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)"""

replacement2 = """            if (bitmap != null) {
                val result = DraftVisionScanner.scanDraftFromBitmap(bitmap)"""

content = content.replace(target2, replacement2)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
print("Removed bad blocks")
