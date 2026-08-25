import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Replace images
replacements = {
    "boots_of_speed_basic": "https://i.postimg.cc/060Ck4xv/1753390529-boots-of-speed.webp",
    "long_sword_basic": "https://i.postimg.cc/DWPcvNFT/1753390561-long-sword.webp",
    "brawler_s_gloves_basic": "https://i.postimg.cc/QBJq8zsr/1753390558-brawlers-gloves.webp",
    "dagger_basic": "https://i.postimg.cc/CZgNV0y5/1753390550-dagger.webp",
    "shimmering_spark_basic": "https://i.postimg.cc/bGhTjPXv/1753390582-shimmering-spark.webp",
    "tear_of_the_goddess_basic": "https://i.postimg.cc/mcswG4xg/1611442459-tear-of-the-goddess.png",
    "amplifying_tome_basic": "https://i.postimg.cc/qtTLdrfM/1753390572-amplifying-tome.webp",
    "ruby_crystal_basic": "https://i.postimg.cc/rdkJLT6y/1753390626-ruby-crystal.webp",
    "cloth_armor_basic": "https://i.postimg.cc/3yTBHhM8/1753390581-cloth-armor.webp",
    "null_magic_mantle_basic": "https://i.postimg.cc/4Kg5TGCJ/1753390606-null-magic-mantle.webp",
    "ring_of_revelation_basic": "https://i.postimg.cc/wtpVdzKH/1753390605-ring-of-revelation.webp",
    "relic_shield_basic": "https://i.postimg.cc/sBrcRzFs/1753390612-relic-shield.webp",
    "spectral_sickle_basic": "https://i.postimg.cc/2qDwfYpY/1753390656-spectral-sickle.webp"
}

for item_id, new_url in replacements.items():
    # regex to find the block for the item and replace iconUrl
    pattern = r'(id\s*=\s*"' + item_id + r'".*?iconUrl\s*=\s*")[^"]+(")'
    content = re.sub(pattern, r'\g<1>' + new_url + r'\g<2>', content, flags=re.DOTALL)

# Delete the Spells that are listed as items.
# They start from flash_basic down to teleport_basic. Let's find the start of flash_basic and truncate
start_idx = content.find('add(WildRiftItem(\n            id = "flash_basic"')
if start_idx != -1:
    end_idx = content.rfind(')')
    if end_idx != -1:
        # wait, the list is wrapped in `buildList { ... }`
        list_end_idx = content.rfind('}')
        if list_end_idx != -1:
            content = content[:start_idx] + "    }\n}"

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(content)
