import urllib.request
from PIL import Image
import io

# URLs for the role icons in Wild Rift
roles = {
    "TOP": "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-top.png",
    "JUNGLE": "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-jungle.png",
    "MID": "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-middle.png",
    "ADC": "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-bottom.png",
    "SUPPORT": "https://raw.communitydragon.org/latest/plugins/rcp-fe-lol-static-assets/global/default/images/icon-position-utility.png"
}

def ahash(image):
    image = image.resize((8, 8), Image.Resampling.LANCZOS).convert('L')
    pixels = list(image.getdata())
    avg = sum(pixels) / len(pixels)
    hash_val = 0
    for i, p in enumerate(pixels):
        if p >= avg:
            hash_val |= (1 << (63 - i))
    return hash_val

hashes = {}
for role, url in roles.items():
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        with urllib.request.urlopen(req) as response:
            img_data = response.read()
        img = Image.open(io.BytesIO(img_data))
        h = ahash(img)
        hashes[role] = h
        print(f"Downloaded {role}: {h}")
    except Exception as e:
        print(f"Failed {role}: {e}")

with open('app/src/main/java/com/example/util/RoleHashes.kt', 'w') as f:
    f.write("package com.example.util\n\n")
    f.write("import com.example.model.LaneRole\n\n")
    f.write("object RoleHashes {\n")
    f.write("    val map = mapOf<LaneRole, Long>(\n")
    for k, v in hashes.items():
        f.write(f'        LaneRole.{k} to {v}L,\n')
    f.write("    )\n")
    f.write("}\n")
