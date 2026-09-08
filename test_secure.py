import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = "            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,"
replacement = "            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS or WindowManager.LayoutParams.FLAG_SECURE,"

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Added FLAG_SECURE")
else:
    print("Could not find target")
