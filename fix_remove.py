import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

target = """    private fun removeFloatingOverlay() {
        try {
            floatingComposeView?.let { view ->
                if (view.isAttachedToWindow) {
                    windowManager?.removeView(view)
                }
            }
            floatingComposeView = null
            closeTargetComposeView?.let { view ->
                if (view.isAttachedToWindow) {
                    windowManager?.removeView(view)
                }
            }
            closeTargetComposeView = null
        } catch (_: Exception) {}
    }"""

replacement = """    private fun removeFloatingOverlay() {
        try {
            floatingComposeView?.let { view ->
                try { windowManager?.removeViewImmediate(view) } catch (_: Exception) {}
            }
            floatingComposeView = null
            closeTargetComposeView?.let { view ->
                try { windowManager?.removeViewImmediate(view) } catch (_: Exception) {}
            }
            closeTargetComposeView = null
        } catch (_: Exception) {}
    }"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

