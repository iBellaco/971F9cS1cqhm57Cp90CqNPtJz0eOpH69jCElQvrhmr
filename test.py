import re
file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Let's see how selectedLanguage is fetched
match = re.search(r'val selectedLanguage = remember \{.*?\}', content)
print(match.group(0))
