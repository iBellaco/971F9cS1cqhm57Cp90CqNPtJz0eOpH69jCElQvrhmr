import re
with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'r') as f:
    text = f.read()

print(re.search(r'val currentVirtualDisplay = virtualDisplay.*?try \{.*?catch', text, re.DOTALL).group(0))
