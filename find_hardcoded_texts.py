import re
import os

screens = [
    'MainDraftingScreen.kt',
    'MetaAndDraftScreen.kt',
    'InfoScreen.kt',
    'ChampionDetailSheet.kt',
    'DatabaseTestScreen.kt',
    'FloatingAssistantService.kt'
]

for s in screens:
    for root, _, files in os.walk('app/src/main/java'):
        if s in files:
            path = os.path.join(root, s)
            with open(path, 'r', encoding='utf-8') as f:
                lines = f.readlines()
                for idx, line in enumerate(lines):
                    # Check Text("...") without tr(
                    matches = re.findall(r'Text\(\s*"([^"]+)"', line)
                    for m in matches:
                        if not m.startswith("$") and not m.isdigit() and len(m) > 2 and not m.startswith("http") and not m.startswith("com."):
                            print(f"{s}:{idx+1}: {m}")
