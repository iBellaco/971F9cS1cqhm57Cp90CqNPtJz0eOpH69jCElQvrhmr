import os, re

kt_files = []
for root, _, files in os.walk("app/src/main/java/com/example/ui"):
    for f in files:
        if f.endswith(".kt"):
            kt_files.append(os.path.join(root, f))

pattern = re.compile(r'Text\s*\(\s*(?:text\s*=\s*)?"([^"${}\n]+)"')

found = {}
for p in kt_files:
    with open(p) as f:
        content = f.read()
        matches = pattern.findall(content)
        unwrapped = [m for m in matches if len(m.strip()) > 1 and not m.isdigit() and m not in ['•', '⭐', '—', ':', '/', '+', '-', '%', 'vs', 'VS']]
        if unwrapped:
            found[p] = unwrapped

for p, texts in found.items():
    print(f"File: {p}")
    for t in texts:
        print(f"   '{t}'")
