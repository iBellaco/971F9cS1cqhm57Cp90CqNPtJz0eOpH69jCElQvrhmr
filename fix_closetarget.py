import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

target = "windowManager?.updateViewLayout(view, params)\n                } catch (_: Exception) {}"
replacement = """windowManager?.updateViewLayout(view, params)
                } catch (_: Exception) {}
                
                try {
                    val closeTargetParams = closeTargetComposeView?.layoutParams as? WindowManager.LayoutParams
                    if (closeTargetParams != null) {
                        closeTargetParams.y = (24 * density).toInt()
                        windowManager?.updateViewLayout(closeTargetComposeView, closeTargetParams)
                    }
                } catch (_: Exception) {}"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
