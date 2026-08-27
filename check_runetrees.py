import re

def extract_names(filepath, pattern, group=1):
    names = []
    with open(filepath, 'r', encoding='utf-8') as f:
        for m in re.finditer(pattern, f.read()):
            names.append(m.group(group))
    return set(names)

def main():
    runes_cat = extract_names("app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt", r'RuneItem\([^)]*name\s*=\s*"([^"]+)"')
    
    # We also have aliases in WildRiftSpellsAndRunes.kt inside `val dbRune = allRunes.find { ... }` or similar?
    # No, the catalog names are what we should match.
    
    files = [
        "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
        "app/src/main/java/com/example/data/champions/JungleChampions.kt",
        "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
        "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
        "app/src/main/java/com/example/data/champions/SupportChampions.kt"
    ]
    
    champ_secondary_runes = set()
    
    for fpath in files:
        with open(fpath, "r", encoding="utf-8") as f:
            content = f.read()
            for m in re.finditer(r'runeTreeDetails\s*=\s*"([^"]+)"', content):
                # E.g. "Valor: Fuente de Vida • Coraza Ósea • Sobrecrecimiento • Botanista"
                text = m.group(1)
                text = re.sub(r'^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+:\s*', '', text)
                parts = [p.strip() for p in text.split('•') if p.strip()]
                champ_secondary_runes.update(parts)

    unmatched = champ_secondary_runes - runes_cat
    
    print("\nUNMATCHED SECONDARY RUNES:")
    for r in sorted(unmatched): print(f" - {r}")

if __name__ == '__main__':
    main()
