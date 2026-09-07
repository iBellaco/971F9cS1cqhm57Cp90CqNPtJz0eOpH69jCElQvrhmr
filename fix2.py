import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """        try {
            windowManager?.addView(closeTargetComposeView, closeTargetParams)
            windowManager?.addView(debugBoxesComposeView, debugParams)
        } catch (_: Exception) {}"""

replacement = """        val debugParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.START
        }

        debugBoxesComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setContent {
                val showDebug by showVisionDebugger.collectAsState()
                if (showDebug) {
                    VisionDebugOverlay()
                }
            }
        }

        try {
            windowManager?.addView(closeTargetComposeView, closeTargetParams)
            windowManager?.addView(debugBoxesComposeView, debugParams)
        } catch (_: Exception) {}"""

content = content.replace(target, replacement)
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
