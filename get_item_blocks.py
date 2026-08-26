import re

items = [
    "ring_of_revelation", "amplifying_tome", "stinger_wild_rift", "nashor_s_talon_wild_rift",
    "forbidden_idol_wild_rift", "hextech_alternator_wild_rift", "void_amethyst",
    "lol_wild_rift_seeker_s_armguard", "oblivion_orb_wild_rift", "sheen_wild_rift",
    "catalyst_of_aeons_wild_rift", "haunting_guise_wild_rift", "needlessly_large_rod_wild_rift",
    "blasting_wand_wild_rift", "fiendish_codex_wild_rift", "lost_chapter_wild_rift",
    "aether_wisp_wild_rift", "mejai_s_soulstealer_wild_rift", "fated_ashes", "verdant_barrier"
]

with open("app/src/main/java/com/example/data/WildRiftItemsData.kt", "r", encoding="utf-8") as f:
    content = f.read()

for item in items:
    pattern = r'(WildRiftItem\([^)]*id\s*=\s*"' + re.escape(item) + r'"[^)]*\))'
    match = re.search(pattern, content, re.DOTALL)
    if match:
        print(f"----- {item} -----")
        print(match.group(1))
    else:
        # maybe id is inside and it's long
        pattern2 = r'(WildRiftItem\(\s*id\s*=\s*"' + re.escape(item) + r'".*?iconUrl\s*=\s*"[^"]+"\s*\))'
        match2 = re.search(pattern2, content, re.DOTALL)
        if match2:
            print(f"----- {item} -----")
            print(match2.group(1))
        else:
            print(f"NOT FOUND: {item}")
