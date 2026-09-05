import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Add dispatchConfigurationChanged inside onConfigurationChanged
target = "super.onConfigurationChanged(newConfig)\n        try {"
replacement = """super.onConfigurationChanged(newConfig)
        try {
            floatingComposeView?.dispatchConfigurationChanged(newConfig)
            closeTargetComposeView?.dispatchConfigurationChanged(newConfig)
        } catch (_: Throwable) {}
        try {"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
