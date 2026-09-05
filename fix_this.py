with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

text = text.replace("isLandscapeMode = isDeviceLandscape.value", "isLandscapeMode = this@FloatingAssistantService.isDeviceLandscape.value")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
