import re
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

text = re.sub(r'catch \(e: Exception\) \{\n\s*AppLogger\.w\("FloatingService", "Error adaptando layout tras cambio de configuración',
              r'catch (e: Throwable) {\n            AppLogger.w("FloatingService", "Error adaptando layout tras cambio de configuración', text)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
