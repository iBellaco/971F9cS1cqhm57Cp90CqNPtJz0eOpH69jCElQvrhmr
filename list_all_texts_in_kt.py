import os, re

kt_files = []
for root, _, files in os.walk("app/src/main/java"):
    for f in files:
        if f.endswith(".kt"):
            kt_files.append(os.path.join(root, f))

# Let's extract all string literals in Text(...), tr("..."), string titles, etc.
text_calls = []
pattern_text = re.compile(r'Text\s*\(\s*(?:text\s*=\s*)?"([^"]+)"')
pattern_tr = re.compile(r'tr(?:Str)?\s*\([^"]*"([^"]+)"')

all_ui_strings = set()
for p in kt_files:
    with open(p) as f:
        content = f.read()
        for s in pattern_text.findall(content):
            if len(s.strip()) > 1 and not s.startswith("http") and not s.startswith("com.") and not s.startswith("res/"):
                all_ui_strings.add(s.strip())
        for s in pattern_tr.findall(content):
            if len(s.strip()) > 1:
                all_ui_strings.add(s.strip())

print(f"Total extracted potential UI strings: {len(all_ui_strings)}")
with open("extracted_ui_strings.txt", "w") as f:
    for s in sorted(all_ui_strings):
        f.write(s + "\n")
