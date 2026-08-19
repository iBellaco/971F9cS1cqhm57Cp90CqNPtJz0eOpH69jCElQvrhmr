with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    c = f.read()

c = c.replace('        "Información" to "Information",',
'''        "Información" to "Information",
        "MID" to "MID",
        "TOP" to "TOP",
        "JUNGLE" to "JUNGLE",
        "ADC" to "ADC",
        "SUPPORT" to "SUPPORT",
        "Daño" to "Damage",
        "Roles" to "Roles",
        "Buscando composiciones..." to "Searching comps...",
''')

with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
    f.write(c)
