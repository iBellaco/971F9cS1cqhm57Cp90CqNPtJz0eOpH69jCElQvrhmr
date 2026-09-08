import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = "WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_SECURE"
replacement = "WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE"

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Removed FLAG_SECURE")
else:
    print("FLAG_SECURE not found")

