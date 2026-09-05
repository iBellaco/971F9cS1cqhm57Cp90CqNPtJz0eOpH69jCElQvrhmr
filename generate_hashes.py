import json
import urllib.request
from PIL import Image
import io

def ahash(image):
    image = image.resize((8, 8), Image.Resampling.LANCZOS).convert('L')
    pixels = list(image.getdata())
    avg = sum(pixels) / len(pixels)
    hash_val = 0
    for i, p in enumerate(pixels):
        if p >= avg:
            hash_val |= (1 << i)
    return hash_val

champions = []
try:
    with open('app/src/main/res/raw/champions_part1.json', 'r') as f:
        champions.extend(json.load(f))
except Exception as e:
    print("Err1", e)

try:
    with open('app/src/main/res/raw/champions_part2.json', 'r') as f:
        champions.extend(json.load(f))
except Exception as e:
    print("Err2", e)

hashes = {}
for c in champions:
    champ_id = c.get("id")
    url = c.get("portraitUrl")
    if not url or champ_id in hashes:
        continue
    # Download
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(req) as response:
            img_data = response.read()
        img = Image.open(io.BytesIO(img_data))
        h = ahash(img)
        hashes[champ_id] = h
        print(f"Downloaded {champ_id}: {h}")
    except Exception as e:
        print(f"Failed {champ_id}: {e}")

with open('app/src/main/java/com/example/util/ChampionHashes.kt', 'w') as f:
    f.write("package com.example.util\n\n")
    f.write("object ChampionHashes {\n")
    f.write("    val map = mapOf<String, Long>(\n")
    for k, v in hashes.items():
        f.write(f'        "{k}" to {v}L,\n')
    f.write("    )\n")
    f.write("}\n")
