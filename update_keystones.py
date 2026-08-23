import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    content = f.read()

keystones = [
    ("Electrocutar", "Golpear a un campeón con 3 ataques o habilidades diferentes en 3s inflige daño adaptable adicional. Ideal para asesinos y combos rápidos."),
    ("Cosecha Oscura", "Dañar a un campeón con menos del 50% de vida inflige daño adaptable adicional y cosecha su alma, aumentando permanentemente el daño de esta runa."),
    ("Fortalecimiento", "Impactar a un campeón con 3 ataques básicos consecutivos inflige daño adaptable adicional y amplifica tu daño contra campeones."),
    ("Compás Letal", "Ganas velocidad de ataque acumulable al atacar campeones. Al máximo de acumulaciones, ganas alcance de ataque adicional."),
    ("Pies Veloces", "Moverte y atacar genera acumulaciones de Energía. A las 100 acumulaciones, tu próximo ataque cura y otorga velocidad de movimiento."),
    ("Conquistador", "Ganas daño adaptable al golpear a un campeón enemigo con ataques o habilidades. Al máximo de acumulaciones otorga omnivampirismo."),
    ("Garras del Inmortal", "Cada 3s en combate, tu próximo ataque básico contra un campeón inflige daño mágico adicional, te cura y aumenta tu vida máxima permanentemente."),
    ("Guardián", "Proteges a aliados dentro de 350 unidades de ti. Si tú o el aliado reciben daño por encima de un umbral, ambos reciben un escudo."),
    ("Aery", "Tus ataques y habilidades envían a Aery a dañar enemigos o dar un escudo a aliados. No puede volver a enviarse hasta que regrese."),
    ("Cometa Arcano", "Dañar a un campeón con una habilidad lanza un cometa que inflige daño adaptable en su ubicación."),
    ("Irrupción de Fase", "Usar 3 ataques o habilidades contra un campeón en 4s otorga velocidad de movimiento masiva y resistencia a ralentizaciones."),
    ("Primer Golpe", "Iniciar el combate contra un campeón enemigo otorga oro adicional y aumenta tu daño verdadero un 7% durante 3s. Otorga oro según el daño."),
    ("Soberano Gélido", "Inmovilizar a un enemigo crea áreas de hielo que ralentizan a los enemigos dentro del área y aumentan tus defensas.")
]

for name, desc in keystones:
    # Find the RuneItem with this name and replace its description
    pattern = r'(name = "' + name + r'".*?description = ")[^"]*(")'
    content = re.sub(pattern, r'\1' + desc + r'\2', content, flags=re.DOTALL)

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(content)
