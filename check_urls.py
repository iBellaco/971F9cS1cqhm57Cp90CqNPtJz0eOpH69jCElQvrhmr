import re
import urllib.request

file_path = "app/src/main/java/com/example/data/WildRiftItemsData.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# find CDN_VERSION
version_match = re.search(r'CDN_VERSION = "(.*?)"', content)
version = version_match.group(1) if version_match else "14.3.1"
base = f"https://ddragon.leagueoflegends.com/cdn/{version}/img/item"

urls = re.findall(r'iconUrl = "\$ITEM_IMG/([0-9]+\.png)"', content)
missing = []
for u in urls:
    url = f"{base}/{u}"
    try:
        req = urllib.request.Request(url, method="HEAD")
        with urllib.request.urlopen(req) as response:
            if response.status != 200:
                missing.append(u)
    except Exception as e:
        missing.append(u)

print("Missing URLs:", missing)
