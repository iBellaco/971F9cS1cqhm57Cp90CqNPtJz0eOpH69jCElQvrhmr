import re

files = [
    "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/JungleChampions.kt",
    "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/SupportChampions.kt",
    "app/src/main/java/com/example/util/ChampionRoleAdapter.kt"
]

MAPPING = {
    "Coraza Ósea": "Revestimiento de Huesos",
    "Destello Hextech": "Hextello",
    "Condicionamiento": "Perseverancia",
    "Acondicionamiento": "Perseverancia",
    "Golpe de Escudo": "Coraje del Coloso",
    "Cazador de Titanes": "Leyenda: Tenacidad",
    "Concentración": "Concentración Absoluta",
    "Anillo de Flujo de Maná": "Banda de Maná",
    "Goloso": "Botanista",
    "Dulces Frutos": "Botanista",
    "Demolición": "Demoler",
    "Se avecina tormenta": "Se Avecina Tormenta",
    "Tormenta Creciente": "Se Avecina Tormenta",
    "Entrega de Galletas": "Botanista",
    "Perspicacia Cósmica": "Semillero Ixtalí",
    "Calzado Mágico": "Hextello",
    "Cazador Voraz": "Cazador Incesante",
    "Marca del Verdugo": "Golpe Bajo",
    "Colección de Ojos": "Colección de Globos Oculares",
    "Cazador Titánico": "Leyenda: Tenacidad",
    "Pionero": "Leyenda: Tenacidad",
    "Fuente de vida": "Fuente de Vida"
}

for fpath in files:
    with open(fpath, "r", encoding="utf-8") as f:
        content = f.read()
    
    for old, new in MAPPING.items():
        content = content.replace(f"• {old} •", f"• {new} •")
        content = content.replace(f"• {old}", f"• {new}")
        content = content.replace(f"{old} •", f"{new} •")
        content = content.replace(f'"{old}"', f'"{new}"')
        
    with open(fpath, "w", encoding="utf-8") as f:
        f.write(content)

print("Mapped secondary runes")
