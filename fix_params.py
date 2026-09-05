with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Add isLandscapeMode to the function signature
text = text.replace("private fun FloatingDraftCoachView(\n    context: Context,", "private fun FloatingDraftCoachView(\n    context: Context,\n    isLandscapeMode: Boolean,")
text = text.replace("val isLandscapeMode = isDeviceLandscape.value", "")
# Update where it's called
text = text.replace("FloatingDraftCoachView(\n                    context = this@FloatingAssistantService,", "FloatingDraftCoachView(\n                    context = this@FloatingAssistantService,\n                    isLandscapeMode = isDeviceLandscape.value,")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
