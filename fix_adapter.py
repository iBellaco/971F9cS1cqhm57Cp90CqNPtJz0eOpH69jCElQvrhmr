import re

fpath = "app/src/main/java/com/example/util/ChampionRoleAdapter.kt"
with open(fpath, "r", encoding="utf-8") as f:
    content = f.read()

# Spells & Runes mapping
# "Electrocutar" is "Electrocutar"
# "Conquistador" is "Conquistador"
# "Réplica" is "Reverberacción" ?? Let's check WildRiftSpellsAndRunes.kt for Aftershock
# "Invocar a Aery" -> "Aery"
# "Agarre del Perpetuo" -> "Garras del Inmortal"
MAPPING = {
    # Items
    '"Eco de Luden"': '"Luden\'s Echo"',
    '"Orbe del Infinito"': '"Orbe infinito"',
    '"Sombrero Mortal de Rabadon"': '"Gorro de muerte del miércoles"',
    '"Botas Jonias de la Lucidez"': '"Botas jonias de la lucidez"',
    '"Báculo del Vacío"': '"Bastón vacío"',
    '"Coraza del Muerto"': '"Plato del hombre muerto"',
    '"Malla de Espinas"': '"malla de espinas"',
    '"Fuerza de la Naturaleza"': '"Fuerza de la naturaleza"',
    '"Punteras Revestidas"': '"Botas blindadas"',
    '"Corona Abrasadora"': '"Corona abrasadora"',
    '"Protector Pétreo"': '"Placa de piedra de gárgola"',
    '"Fuerza de la Trinidad"': '"Fuerza trinitaria"',
    '"Recaudadora"': '"El coleccionista"',
    '"Filo del Infinito"': '"Borde infinito"',
    '"Grebas Berserker"': '"Grebas de berserker"',
    '"Recuerdos de Lord Dominik"': '"Saludos de Dominik"',
    '"Ángel Guardián"': '"Ángel custodio"',
    '"Cuchilla Negra"': '"Black Cleaver"',
    '"Danza de la Muerte"': '"La danza de la muerte"',
    '"Calibrador de Sterak"': '"Sterak\'s Gage"',
    '"Hoz Espectral"': '"Hoz espectral"',
    '"Morellonomicón"': '"Morellonomicón"',
    '"Baluarte de la Montaña"': '"Baluarte de la montaña"',
    '"Manto del Amanecer"': '"Sudario del alba"',
    '"Convergencia de Zeke"': '"La convergencia de Zeke"',
    '"Incensario Ardiente"': '"Incensario Ardiente"',
    '"Bastón de Aguas Fluidas"': '"Staff of Flowing Water"',
    '"Redención"': '"Redención"',
    '"Promesa del Caballero"': '"Voto de caballero"',
    '"Filoscuro de Draktharr"': '"Hoja del Ocaso de Draktharr"',
    '"Colmillo de Serpiente"': '"Serpent\'s Fang"',
    '"Guantelete de Hielo"': '"Guantelete nacido del hielo"',
    '"Protección Gemela de Amaranth"': '"Guardia gemela de amaranto"',
    '"Creagrietas"': '"Hacedor de grietas"',
    '"Diente de Nashor"': '"Diente de Nashor"',
    '"Reloj de Arena de Zhonya"': '"El reloj de arena de Zhonya"',
    '"Hoja del Rey Arruinado"': '"Espada del Rey Arruinado"',
    '"Bailarín Espectral"': '"Bailarina fantasma"',
    '"Rompecascos"': '"Rompecascos"',
    '"Cañón de Fuego Rápido"': '"Blaster magnético"',
    '"Sanguinaria"': '"sanguinario"',
    '"Corona de la Reina Ahogada"': '"Corona de la Reina Ahogada"', # Wait, is this in the catalog?
    '"Filo Fantasma de Youmuu"': '"El cuchillo fantasma de Youmuu"',
    '"Rencor de Serylda"': '"El rencor de Serylda"',
    '"Velo de la Banshee"': '"Velo de alma en pena"',
    '"Tridente de Oceánida"': '"Tridente de Oceanida"',
    '"Presagio de Randuin"': '"El presagio de Randuin"',
    '"Rookern Kaénico"': '"Kaenic Rookern"',
    '"Recordatorio Mortal"': '"Recordatorio mortal"',
    '"Fajín de Mercurio"': '"Fajín de mercurio"',
    '"Filo de la Noche"': '"Edge of Night"',
    '"Cimitarra Mercurial"': '"Cimitarra mercurial"',
    '"Espada Sierra Quimopunk"': '"Chempunk Chainsword"',
}

for old, new in MAPPING.items():
    content = content.replace(old, new)

with open(fpath, "w", encoding="utf-8") as f:
    f.write(content)
print("Replaced items in adapter")
