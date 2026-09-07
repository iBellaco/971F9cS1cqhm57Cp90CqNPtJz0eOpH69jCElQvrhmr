import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """    override fun onDestroy() {"""

replacement = """    private fun updateOverlayRect(params: WindowManager.LayoutParams, isExpanded: Boolean) {
        try {
            val density = resources.displayMetrics.density
            val isLandscape = resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels
            val cWidth = if (isExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
            val cHeight = if (isExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
            val margin = (16 * density).toInt()
            DraftVisionScanner.overlayRect = android.graphics.Rect(params.x - margin, params.y - margin, params.x + cWidth + margin, params.y + cHeight + margin)
        } catch (_: Exception) {}
    }

    override fun onDestroy() {"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
print("Added updateOverlayRect properly")
