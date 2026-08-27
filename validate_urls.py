import json
import urllib.request
from concurrent.futures import ThreadPoolExecutor

with open('app/src/main/assets/champions.json', 'r') as f:
    champs = json.load(f)

def check_url(url):
    if not url: return None
    try:
        req = urllib.request.Request(url, method='HEAD', headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(req) as response:
            return None if response.status == 200 else url
    except Exception:
        return url

urls_to_check = set()
for c in champs:
    urls_to_check.add(c.get('avatarUrl'))
    for s in c.get('skills', []):
        urls_to_check.add(s.get('iconUrl'))

urls_to_check = [u for u in urls_to_check if u]
print(f"Checking {len(urls_to_check)} URLs...")

broken = []
with ThreadPoolExecutor(max_workers=20) as executor:
    results = executor.map(check_url, urls_to_check)
    for r in results:
        if r: broken.append(r)

print(f"Found {len(broken)} broken URLs.")
for b in broken[:10]:
    print(b)
