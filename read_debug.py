import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

start = content.find("val right = rect.right * scaleX")
end = content.find("drawRect(", start)
if start != -1 and end != -1:
    print(content[start:end+100])
else:
    print("Not found")
