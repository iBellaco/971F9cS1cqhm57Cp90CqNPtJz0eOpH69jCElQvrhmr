import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

print("allySlots assignment block:")
match = re.search(r'// Detección de Campeón Aliado por nombre explícito.*?if \(candidate != null\) \{.*?\}', text, re.DOTALL)
if match:
    print(match.group(0))

