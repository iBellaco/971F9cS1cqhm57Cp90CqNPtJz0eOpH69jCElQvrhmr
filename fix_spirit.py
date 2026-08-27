files = [
    "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/JungleChampions.kt",
    "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/SupportChampions.kt"
]

for fpath in files:
    with open(fpath, "r", encoding="utf-8") as f:
        content = f.read()
    
    # We mapped "Apariencia Espiritual" to "Velo de alma en pena"
    # Let's change "Velo de alma en pena" back to "Fuerza de la naturaleza" ONLY if it was meant to be Apariencia Espiritual. 
    # Wait, it's easier to just leave it as Velo de alma en pena, or manually edit it.
