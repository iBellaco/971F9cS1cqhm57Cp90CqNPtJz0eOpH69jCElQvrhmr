with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# 1. Add isLandscapeMode to FloatingOverlayContent
text = text.replace("private fun FloatingOverlayContent(\n    screenCaptureManager: ScreenCaptureManager?,", "private fun FloatingOverlayContent(\n    isLandscapeMode: Boolean,\n    screenCaptureManager: ScreenCaptureManager?,")

# 2. Pass it from createFloatingOverlay
text = text.replace("FloatingOverlayContent(\n                            screenCaptureManager = screenCaptureManager,", "FloatingOverlayContent(\n                            isLandscapeMode = isDeviceLandscape.value,\n                            screenCaptureManager = screenCaptureManager,")

# 3. Update the call to FloatingDraftCoachView
text = text.replace("isLandscapeMode = this@FloatingAssistantService.isDeviceLandscape.value,", "isLandscapeMode = isLandscapeMode,")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
