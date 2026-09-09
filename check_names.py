import json

app_ids = set()
for file in ["app/src/main/res/raw/champions_part1.json", "app/src/main/res/raw/champions_part2.json"]:
    with open(file, 'r') as f:
        data = json.load(f)
        for c in data:
            if "id" in c:
                app_ids.add(c["id"])

import os
downloaded_files = os.listdir("app/src/main/assets/champions")
downloaded_ids = set([f.replace(".png", "").replace(".webp", "") for f in downloaded_files])

print(f"App IDs count: {len(app_ids)}")
print(f"Downloaded files count: {len(downloaded_ids)}")

missing_in_downloaded = app_ids - downloaded_ids
print("App IDs missing from downloaded files:")
for m in sorted(missing_in_downloaded):
    print(m)
