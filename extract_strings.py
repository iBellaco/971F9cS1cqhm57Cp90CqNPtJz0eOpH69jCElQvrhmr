import re
import os
import json

data_dirs = [
    "app/src/main/java/com/example/data/champions",
    "app/src/main/java/com/example/data"
]

unique_strings = set()

def extract_from_file(filepath):
    with open(filepath, 'r') as f:
        content = f.read()
    
    # regex for title, summary, tacticalAdvice, description, passive
    # This might be tricky because of multiline strings or simple strings.
    # Let's find all `summary = "..."`
    patterns = [
        r'title\s*=\s*"([^"]+)"',
        r'summary\s*=\s*"([^"]+)"',
        r'tacticalAdvice\s*=\s*"([^"]+)"',
        r'description\s*=\s*"([^"]+)"',
        r'passive\s*=\s*"([^"]+)"',
        r'stats\s*=\s*"([^"]+)"',
        r'reasonDesc\s*=\s*"([^"]+)"'
    ]
    
    for p in patterns:
        matches = re.findall(p, content)
        for m in matches:
            if m.strip():
                unique_strings.add(m.strip())

for d in data_dirs:
    for root, dirs, files in os.walk(d):
        for file in files:
            if file.endswith(".kt"):
                extract_from_file(os.path.join(root, file))

print(f"Found {len(unique_strings)} unique strings.")
with open('strings_to_translate.json', 'w') as f:
    json.dump(list(unique_strings), f, ensure_ascii=False, indent=2)

