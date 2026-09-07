import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target_method = """    private fun stopOverlay() {
        try {
            if (floatingComposeView?.isAttachedToWindow == true) {"""

replacement_method = """    private fun updateOverlayRect(params: WindowManager.LayoutParams, isExpanded: Boolean) {
        try {
            val density = resources.displayMetrics.density
            val isLandscape = resources.displayMetrics.widthPixels > resources.displayMetrics.heightPixels
            val cWidth = if (isExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
            val cHeight = if (isExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
            val margin = (16 * density).toInt()
            DraftVisionScanner.overlayRect = android.graphics.Rect(params.x - margin, params.y - margin, params.x + cWidth + margin, params.y + cHeight + margin)
        } catch (_: Exception) {}
    }

    private fun stopOverlay() {
        try {
            if (floatingComposeView?.isAttachedToWindow == true) {"""

content = content.replace(target_method, replacement_method)

target_update1 = """                                try {
                                    if (this@apply.isAttachedToWindow) {
                                        windowManager?.updateViewLayout(this@apply, params)
                                    }
                                } catch (_: Exception) {}"""

replacement_update1 = """                                try {
                                    if (this@apply.isAttachedToWindow) {
                                        windowManager?.updateViewLayout(this@apply, params)
                                        updateOverlayRect(params, isOverlayExpanded)
                                    }
                                } catch (_: Exception) {}"""

content = content.replace(target_update1, replacement_update1)

target_update2 = """                    floatingParams!!.y = currentY.coerceIn(marginPx, maxY)
                    windowManager?.updateViewLayout(floatingComposeView, floatingParams)
                }
            } else {"""

replacement_update2 = """                    floatingParams!!.y = currentY.coerceIn(marginPx, maxY)
                    windowManager?.updateViewLayout(floatingComposeView, floatingParams)
                    updateOverlayRect(floatingParams!!, isOverlayExpanded)
                }
            } else {"""

content = content.replace(target_update2, replacement_update2)

target_init = """            windowManager?.addView(floatingComposeView, params)
            isOverlayExpanded = false
            floatingParams = params"""

replacement_init = """            windowManager?.addView(floatingComposeView, params)
            isOverlayExpanded = false
            floatingParams = params
            updateOverlayRect(params, false)"""

content = content.replace(target_init, replacement_init)


with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
print("Added updateOverlayRect")
