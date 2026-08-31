import os, re, json

pc_keywords = [
    r'\bnivel 6\b', r'\blvl 6\b', r'\blevel 6\b',
    r'\bminuto 14\b', r'\bminuto 20\b', r'\bminuto 8\b',
    r'\bhechizo de teletransporte\b', r'\bteleport spell\b',
    r'\btu Q\b', r'\btu W\b', r'\btu E\b', r'\bsu Q\b', r'\bsu W\b', r'\bsu E\b',
    r'\bla Q\b', r'\bla W\b', r'\bla E\b', r'\bel Q\b', r'\bel W\b', r'\bel E\b',
    r'\bcon Q\b', r'\bcon W\b', r'\bcon E\b',
    r'\bmaxear Q\b', r'\bmaxea Q\b', r'\bmaxear W\b', r'\bmaxear E\b',
    r'\bQ\+W\b', r'\bQ\+E\b', r'\bE\+Q\b', r'\bW\+Q\b',
    r'\btop lane\b', r'\bbot lane\b', r'\btoplane\b', r'\bbotlane\b'
]

findings = []

for root, dirs, files in os.walk('.'):
    if any(p in root for p in ['.git', '.gradle', 'build']):
        continue
    for f in files:
        if f.endswith(('.kt', '.json', '.xml', '.md')):
            path = os.path.join(root, f)
            with open(path, 'r', encoding='utf-8', errors='ignore') as file:
                lines = file.readlines()
                for line_no, line in enumerate(lines, 1):
                    for kw in pc_keywords:
                        if re.search(kw, line, re.IGNORECASE):
                            findings.append((path, line_no, kw, line.strip()[:120]))

print(f"Total occurrences found: {len(findings)}")
for path, line_no, kw, snippet in findings[:40]:
    print(f"{path}:{line_no} [{kw}] -> {snippet}")
