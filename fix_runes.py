import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    text = f.read()

fixes = {
    "Último Esfuerzo": "Infliges entre 5% y 11% de daño adicional a campeones enemigos mientras tengas menos del 60% de vida. El daño extra es máximo al 30% de vida.",
    "Leyenda: Linaje": "Otorgar derribos a monstruos épicos y campeones aumenta tu omnivampirismo de manera permanente.",
    "Revitalizar": "Tus curaciones y escudos son un 5% más fuertes (o 15% si el objetivo tiene menos del 40% de vida)."
}

for name, desc in fixes.items():
    pattern = r'(name = "' + name + r'".*?description = ")[^"]*(")'
    text = re.sub(pattern, r'\1' + desc + r'\2', text, flags=re.DOTALL)

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(text)
