import json

def update_json(filename):
    with open(filename, "r", encoding="utf-8") as f:
        data = json.load(f)
    
    for champ in data:
        if champ["id"] == "ambessa":
            champ["avatarUrl"] = "https://wr-meta.com/uploads/posts/2024-10/1729619132_gacsdslw8a11au11q0i_11zon.webp"
        elif champ["id"] == "mel":
            champ["avatarUrl"] = "https://wr-meta.com/uploads/posts/2025-01/1737748919_mel_0.webp"
        elif champ["id"] == "yunara":
            champ["avatarUrl"] = "https://wr-meta.com/uploads/posts/2025-07/1751894236_yunara-lol-splash-art-8k-wallpaper-uhdpaper_com-4341225g.webp"
            
    with open(filename, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)

update_json("app/src/main/res/raw/champions_part1.json")
update_json("app/src/main/res/raw/champions_part2.json")
