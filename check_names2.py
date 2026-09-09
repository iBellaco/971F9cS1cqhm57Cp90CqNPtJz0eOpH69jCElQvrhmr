import json
import os

app_ids = set()
for file in ["app/src/main/res/raw/champions_part1.json", "app/src/main/res/raw/champions_part2.json"]:
    with open(file, 'r') as f:
        data = json.load(f)
        for c in data:
            if "id" in c:
                app_ids.add(c["id"])

downloaded_files = os.listdir("app/src/main/assets/champions")
downloaded_ids = set([f.replace(".png", "").replace(".webp", "") for f in downloaded_files])

print("Downloaded files not in App IDs:")
extra = downloaded_ids - app_ids
for m in sorted(extra):
    print(m)
