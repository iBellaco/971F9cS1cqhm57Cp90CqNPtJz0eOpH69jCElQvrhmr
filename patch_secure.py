import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )"""

replacement = """        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            layoutType,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_SECURE,
            PixelFormat.TRANSLUCENT
        )"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Added FLAG_SECURE successfully")
else:
    print("Could not find target")
