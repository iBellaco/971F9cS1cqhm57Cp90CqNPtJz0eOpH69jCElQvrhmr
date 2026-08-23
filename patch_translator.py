import re

with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    content = f.read()

target = """        "Balance de Daño Rival" to "Balanço de Dano Inimigo","""
replacement = """        "Balance de Daño Rival" to "Balanço de Dano Inimigo",
        "Balance de Daño Aliado" to "Balanço de Dano Aliado",
        "Exceso de Daño Físico aliado (AD)." to "Excesso de Dano Físico aliado (AD).",
        "Exceso de Daño Mágico aliado (AP)." to "Excesso de Dano Mágico aliado (AP).","""
content = content.replace(target, replacement)

target2 = """        "Balance de Daño Rival" to "Enemy Damage Balance","""
replacement2 = """        "Balance de Daño Rival" to "Enemy Damage Balance",
        "Balance de Daño Aliado" to "Ally Damage Balance",
        "Exceso de Daño Físico aliado (AD)." to "Too much allied Physical Damage (AD).",
        "Exceso de Daño Mágico aliado (AP)." to "Too much allied Magic Damage (AP).","""
content = content.replace(target2, replacement2)

with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
    f.write(content)
print("Done translator")
