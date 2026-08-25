import re

with open("app/src/main/java/com/example/util/Translator.kt", "r", encoding="utf-8") as f:
    content = f.read()

runes_en = {
    "Cosecha Oscura": "Dark Harvest",
    "Electrocutar": "Electrocute",
    "Compás Letal": "Lethal Tempo",
    "Conquistador": "Conqueror",
    "Aumento Glacial": "Glacial Augment",
    "Primer Golpe": "First Strike",
    "Fase Veloz": "Phase Rush",
    "Reverberacción": "Aftershock",
    "Fuente de Vida": "Font of Life",
    "Garras del Inmortal": "Grasp of the Undying",
    "Guardián": "Guardian",
    "Cometa Arcano": "Arcane Comet",
    "Invocar a Aery": "Summon Aery",
    "Soberano Gélido": "Glacial Augment",
    "Fortalecimiento": "Empowerment",
    "Reverberación": "Aftershock",
    "Pies Veloces": "Fleet Footwork",
    "Ataque Intensificado": "Press the Attack",
    "Impacto Súbito": "Sudden Impact",
    "Debilidad": "Weakness",
    "Marca de los Débiles": "Mark of the Weak",
    "Marca del Débil": "Mark of the Weak",
    "Golpe Bajo": "Cheap Shot",
    "Poro Fantasma": "Ghost Poro",
    "Colección de Globos Oculares": "Eyeball Collection",
    "Cazador Ingenioso": "Ingenious Hunter",
    "Coraza Ósea": "Bone Plating",
    "Fuerzas Renovadas": "Second Wind",
    "Condicionamiento": "Conditioning",
    "Crecimiento Excesivo": "Overgrowth",
    "Inquebrantable": "Unflinching",
    "Revitalizar": "Revitalize",
    "Quemadura": "Scorch",
    "Tormenta Creciente": "Gathering Storm",
    "Trascendencia": "Transcendence",
    "Anillo de Flujo de Maná": "Manaflow Band",
    "Capa del Nimbo": "Nimbus Cloak",
    "Bandolada de Maná": "Manaflow Band",
    "Triunfo": "Triumph",
    "Claridad Mental": "Presence of Mind",
    "Leyenda: Celeridad": "Legend: Alacrity",
    "Leyenda: Tenacidad": "Legend: Tenacity",
    "Leyenda: Linaje": "Legend: Bloodline",
    "Último Esfuerzo": "Last Stand",
    "Golpe de Gracia": "Coup de Grace",
    "Cazador Cazado": "Cut Down",
    "Derribado": "Cut Down",
    "Dulces Frutos": "Sweet Tooth",
    "Cazador: Genio": "Hunter: Genius",
    "Tropa": "Pack Hunter",
    "Destructor": "Demolish",
    "Mente Maestra": "Mastermind",
    "Cazador - Titán": "Hunter - Titan",
    "Cazador - Vampirismo": "Hunter - Vampirism",
    "Se Avecina Tormenta": "Gathering Storm",
    "Calzado Mágico": "Magical Footwear",
    "Mercado del Futuro": "Future's Market",
    "Entrega de Galletas": "Biscuit Delivery",
    "Perspicacia Cósmica": "Cosmic Insight",
    "Velocidad de Aproximación": "Approach Velocity",
    "Tónico de Distorsión Temporal": "Time Warp Tonic",
    "Destello Hextech": "Hextech Flashtraption",
    "Golpe Escudo": "Shield Bash",
    "Corazón de Acero": "Heartsteel",
    "Impacto Repentino": "Sudden Impact",
    "Atadura Sensorial": "Psychic Bond",
    "Cazador - Asesino": "Hunter - Assassin"
}

runes_pt = {
    "Cosecha Oscura": "Colheita Sombria",
    "Electrocutar": "Eletrocutar",
    "Compás Letal": "Ritmo Fatal",
    "Conquistador": "Conquistador",
    "Aumento Glacial": "Aprimoramento Glacial",
    "Primer Golpe": "Primeiro Ataque",
    "Fase Veloz": "Ímpeto Gradual",
    "Reverberacción": "Pós-choque",
    "Reverberación": "Pós-choque",
    "Fuente de Vida": "Fonte da Vida",
    "Garras del Inmortal": "Aperto dos Mortos-Vivos",
    "Guardián": "Guardião",
    "Cometa Arcano": "Cometa Arcano",
    "Invocar a Aery": "Invocar Aery",
    "Soberano Gélido": "Aprimoramento Glacial",
    "Fortalecimiento": "Fortalecimento",
    "Pies Veloces": "Agilidade nos Pés",
    "Ataque Intensificado": "Pressione o Ataque",
    "Impacto Súbito": "Impacto Repentino",
    "Debilidad": "Fraqueza",
    "Marca de los Débiles": "Marca do Fraco",
    "Marca del Débil": "Marca do Fraco",
    "Golpe Bajo": "Golpe Desleal",
    "Poro Fantasma": "Poro Fantasma",
    "Colección de Globos Oculares": "Globos Oculares",
    "Cazador Ingenioso": "Caçador Ardil",
    "Coraza Ósea": "Osso Revestido",
    "Fuerzas Renovadas": "Ventos Revigorantes",
    "Condicionamiento": "Condicionamento",
    "Crecimiento Excesivo": "Crescimento Excessivo",
    "Inquebrantable": "Inabalável",
    "Revitalizar": "Revitalizar",
    "Quemadura": "Chamuscar",
    "Tormenta Creciente": "Tempestade Crescente",
    "Trascendencia": "Transcendência",
    "Anillo de Flujo de Maná": "Faixa de Fluxo de Mana",
    "Capa del Nimbo": "Manto de Nimbus",
    "Bandolada de Maná": "Faixa de Fluxo de Mana",
    "Triunfo": "Triunfo",
    "Claridad Mental": "Presença de Espírito",
    "Leyenda: Celeridad": "Lenda: Espontaneidade",
    "Leyenda: Tenacidad": "Lenda: Tenacidade",
    "Leyenda: Linaje": "Lenda: Linhagem",
    "Último Esfuerzo": "Até a Morte",
    "Golpe de Gracia": "Golpe de Misericórdia",
    "Cazador Cazado": "Dilacerar",
    "Derribado": "Dilacerar",
    "Dulces Frutos": "Fruto Doce",
    "Cazador: Genio": "Caçador: Gênio",
    "Tropa": "Caçador em Bando",
    "Destructor": "Demolir",
    "Mente Maestra": "Mente Mestra",
    "Cazador - Titán": "Caçador - Titã",
    "Cazador - Vampirismo": "Caçador - Vampirismo",
    "Se Avecina Tormenta": "Tempestade Crescente",
    "Calzado Mágico": "Calçados Mágicos",
    "Mercado del Futuro": "Mercado do Futuro",
    "Entrega de Galletas": "Entrega de Biscoitos",
    "Perspicacia Cósmica": "Perspicácia Cósmica",
    "Velocidad de Aproximación": "Velocidade de Aproximação",
    "Tónico de Distorsión Temporal": "Tônico de Distorção no Tempo",
    "Destello Hextech": "Flashtração Hextec",
    "Golpe Escudo": "Golpe de Escudo",
    "Corazón de Acero": "Coração de Aço",
    "Impacto Repentino": "Impacto Repentino",
    "Atadura Sensorial": "Elo Psíquico",
    "Cazador - Asesino": "Caçador - Assassino"
}

spells_en = {
    "Fantasmal": "Ghost",
    "Curar": "Heal",
    "Barrera": "Barrier",
    "Extenuación": "Exhaust",
    "Destello": "Flash",
    "Prender": "Ignite",
    "Castigo": "Smite",
    "Aplastar": "Smite",
    "Estasis": "Stasis",
    "Claridad": "Clarity",
    "Marca / Lanzamiento": "Mark / Dash"
}

spells_pt = {
    "Fantasmal": "Fantasma",
    "Curar": "Curar",
    "Barrera": "Barreira",
    "Extenuación": "Exaustão",
    "Destello": "Flash",
    "Prender": "Incendiar",
    "Castigo": "Golpear",
    "Aplastar": "Golpear",
    "Estasis": "Estase",
    "Claridad": "Clareza",
    "Marca / Lanzamiento": "Marcação / Avanço"
}

# Add words if not existing
def inject_translations(content, lang, data):
    start = content.find(f'"{lang}" to mapOf(')
    if start == -1:
        return content
    block_start = content.find('{', start)
    if block_start == -1: # maybe it doesn't use {}? Ah it's mapOf( 
        block_start = content.find('(', start)
    
    # We will just insert them right after `"lang" to mapOf(\n`
    insertion = ""
    for k, v in data.items():
        if f'"{k}" to ' not in content[start:start+2000]: # rough check
            insertion += f'        "{k}" to "{v}",\n'
    
    new_content = content[:block_start+1] + "\n" + insertion + content[block_start+1:]
    return new_content

content = inject_translations(content, "en", runes_en)
content = inject_translations(content, "en", spells_en)

content = inject_translations(content, "pt", runes_pt)
content = inject_translations(content, "pt", spells_pt)

with open("app/src/main/java/com/example/util/Translator.kt", "w", encoding="utf-8") as f:
    f.write(content)

