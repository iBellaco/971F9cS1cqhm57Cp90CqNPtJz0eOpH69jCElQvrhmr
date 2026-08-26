import re

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt") as f:
    content = f.read()

pattern = re.compile(r'Text\s*\(\s*(?:text\s*=\s*)?"([^"${}\n]+)"')
matches = pattern.findall(content)
unwrapped = [m for m in matches if len(m.strip()) > 1 and not m.isdigit() and m not in ['•', '⭐', '—', ':', '/', '+', '-', '%', 'vs', 'VS', 'Q', 'W', 'E', 'R', 'D', 'F']]
print(f"Unwrapped in FloatingAssistantService.kt ({len(unwrapped)}):")
for t in set(unwrapped):
    print(f"   '{t}'")
