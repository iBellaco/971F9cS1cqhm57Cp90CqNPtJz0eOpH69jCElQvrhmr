with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Put it back as a parameter to the function
text = text.replace("private fun FloatingDraftCoachView(\n    activeRole: LaneRole,", "private fun FloatingDraftCoachView(\n    isLandscapeMode: Boolean,\n    activeRole: LaneRole,")

# Update call site
text = text.replace("FloatingDraftCoachView(\n                                            activeRole = activeRole,", "FloatingDraftCoachView(\n                                            isLandscapeMode = isDeviceLandscape.value,\n                                            activeRole = activeRole,")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
