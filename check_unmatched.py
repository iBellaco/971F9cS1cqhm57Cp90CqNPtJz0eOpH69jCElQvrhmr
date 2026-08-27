import re

def extract_names(filepath, pattern, group=1):
    names = []
    with open(filepath, 'r', encoding='utf-8') as f:
        for m in re.finditer(pattern, f.read()):
            names.append(m.group(group))
    return set(names)

def main():
    items_cat = extract_names("app/src/main/java/com/example/data/WildRiftItemsData.kt", r'name\s*=\s*"([^"]+)"')
    runes_cat = extract_names("app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt", r'RuneItem\([^)]*name\s*=\s*"([^"]+)"')
    spells_cat = extract_names("app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt", r'SummonerSpellItem\([^)]*name\s*=\s*"([^"]+)"')
    
    files = [
        "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
        "app/src/main/java/com/example/data/champions/JungleChampions.kt",
        "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
        "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
        "app/src/main/java/com/example/data/champions/SupportChampions.kt"
    ]
    
    champ_items = set()
    champ_runes = set()
    champ_spells = set()
    
    for fpath in files:
        with open(fpath, "r", encoding="utf-8") as f:
            content = f.read()
            # items
            for m in re.finditer(r'coreItems\s*=\s*listOf\(([^)]*)\)', content):
                items = [i.strip(' "') for i in m.group(1).split(',')]
                champ_items.update(i for i in items if i)
            for m in re.finditer(r'situationalItems\s*=\s*listOf\(([^)]*)\)', content):
                items = [i.strip(' "') for i in m.group(1).split(',')]
                champ_items.update(i for i in items if i)
            # spells
            for m in re.finditer(r'recommendedSpells\s*=\s*listOf\(([^)]*)\)', content):
                spells = [s.strip(' "') for s in m.group(1).split(',')]
                champ_spells.update(s for s in spells if s)
            # runes
            for m in re.finditer(r'recommendedRunes\s*=\s*"([^"]+)"', content):
                champ_runes.add(m.group(1))

    unmatched_items = champ_items - items_cat
    unmatched_runes = champ_runes - runes_cat
    unmatched_spells = champ_spells - spells_cat
    
    print("UNMATCHED ITEMS:")
    for i in sorted(unmatched_items): print(f" - {i}")
    
    print("\nUNMATCHED RUNES:")
    for r in sorted(unmatched_runes): print(f" - {r}")
    
    print("\nUNMATCHED SPELLS:")
    for s in sorted(unmatched_spells): print(f" - {s}")

if __name__ == '__main__':
    main()
