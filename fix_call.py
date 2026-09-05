import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

text = text.replace(
"""                        FloatingOverlayContent(
                            isLandscapeMode = isDeviceLandscape.value,""",
"""                        FloatingOverlayContent(
                            state = overlayState,
                            isLandscapeMode = isDeviceLandscape.value,""")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

