import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

start = content.find("override fun onConfigurationChanged")
end = content.find("private fun handleScanResult", start)
if start != -1 and end != -1:
    print(content[start:end])
else:
    print("Not found")
