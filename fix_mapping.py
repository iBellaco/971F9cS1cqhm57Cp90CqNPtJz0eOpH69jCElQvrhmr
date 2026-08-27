import re

files = [
    "app/src/main/java/com/example/data/champions/BaronLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/JungleChampions.kt",
    "app/src/main/java/com/example/data/champions/MidLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/DragonLaneChampions.kt",
    "app/src/main/java/com/example/data/champions/SupportChampions.kt"
]

MAPPING = {
    # Spells
    "Castigo": "Aplastar",
    "Ignición": "Prender",

    # Runes
    "Aery (Brujería)": "Aery",
    "Cometa Arcano (Brujería)": "Cometa Arcano",
    "Compás Letal (Precisión)": "Compás Letal",
    "Conquistador (Precisión)": "Conquistador",
    "Electrocutar (Dominación)": "Electrocutar",
    "Garras del Inmortal (Valor)": "Garras del Inmortal",
    "Pies Veloces (Precisión)": "Pies Veloces",
    "Primer Golpe (Inspiración)": "Primer Golpe",
    "Soberano Gélido (Valor)": "Soberano Gélido",

    # Items
    "Amanecer y anochecer": "Anochecer y amanecer",
    "Apariencia Espiritual": "Velo de alma en pena", 
    "Botas de Maná": "Botas de maná",
    "Báculo del Vacío": "Bastón vacío",
    "Calibrador de Sterak": "Sterak's Gage",
    "Cañón de Fuego Rápido": "Blaster magnético", 
    "Convergencia de Zeke": "La convergencia de Zeke",
    "Coraza del Muerto": "Plato del hombre muerto",
    "Cota de Espinas": "malla de espinas",
    "Cuchilla Oscura": "Black Cleaver",
    "Danza de la Muerte": "La danza de la muerte",
    "Eco de Luden": "Luden's Echo",
    "Escudo Reliquia": "Escudo de reliquia",
    "Espada Fantasma de Youmuu": "El cuchillo fantasma de Youmuu",
    "Filo de la Noche": "Edge of Night",
    "Filo del Infinito": "Borde infinito",
    "Fuerza de la Naturaleza": "Fuerza de la naturaleza",
    "Fuerza de la Trinidad": "Fuerza trinitaria",
    "Hoja del Rey Arruinado": "Espada del Rey Arruinado",
    "Manto del Amanecer": "Sudario del alba",
    "Presagio de Randuin": "El presagio de Randuin",
    "Protector Pétreo": "Placa de piedra de gárgola",
    "Punteras Revestidas": "Botas blindadas",
    "Recordatorio Mortal": "Recordatorio mortal",
    "Recuerdos de Lord Dominik": "Saludos de Dominik",
    "Reloj de Arena de Zhonya": "El reloj de arena de Zhonya",
    "Rencor de Serylda": "El rencor de Serylda",
    "Rookern kaénico": "Kaenic Rookern",
    "Sombrero Mortal de Rabadon": "Gorro de muerte del miércoles",
    "WARMONG": "Armadura de Warmog",
    "Ángel Guardián": "Ángel custodio",
    "Égida de Fuego Solar": "Égida del fuego solar"
}

for fpath in files:
    with open(fpath, "r", encoding="utf-8") as f:
        content = f.read()
    
    for old, new in MAPPING.items():
        # Replace specifically in quotes to not affect code
        content = content.replace(f'"{old}"', f'"{new}"')
        
    with open(fpath, "w", encoding="utf-8") as f:
        f.write(content)
    print(f"Mapped correctly in {fpath}")

