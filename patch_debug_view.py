import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

old_vars = """    private var closeTargetComposeView: ComposeView? = null
    private var windowManager: WindowManager? = null"""

new_vars = """    private var closeTargetComposeView: ComposeView? = null
    private var debugBoxesComposeView: ComposeView? = null
    private var windowManager: WindowManager? = null
    private val showVisionDebugger = kotlinx.coroutines.flow.MutableStateFlow(false)"""

content = content.replace(old_vars, new_vars)

old_close = """        closeTargetComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                FloatingCloseTarget(
                    isVisible = isCloseTargetVisible,
                    isTargeted = isCloseTargetHovered
                )
            }
        }"""

new_close = """        closeTargetComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {
                FloatingCloseTarget(
                    isVisible = isCloseTargetVisible,
                    isTargeted = isCloseTargetHovered
                )
            }
        }
        
        val debugParams = WindowManager.LayoutParams(
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
                val showDebug by showVisionDebugger.collectAsStateWithLifecycle()
                if (showDebug) {
                    VisionDebugOverlay()
                }
            }
        }"""

content = content.replace(old_close, new_close)

old_add = """        try {
            windowManager?.addView(closeTargetComposeView, closeTargetParams)
        } catch (_: Exception) {}"""

new_add = """        try {
            windowManager?.addView(closeTargetComposeView, closeTargetParams)
            windowManager?.addView(debugBoxesComposeView, debugParams)
        } catch (_: Exception) {}"""

content = content.replace(old_add, new_add)

old_remove = """            closeTargetComposeView?.let { view ->
                windowManager?.removeView(view)
            }
            closeTargetComposeView = null"""

new_remove = """            closeTargetComposeView?.let { view ->
                windowManager?.removeView(view)
            }
            closeTargetComposeView = null
            
            debugBoxesComposeView?.let { view ->
                windowManager?.removeView(view)
            }
            debugBoxesComposeView = null"""

content = content.replace(old_remove, new_remove)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
