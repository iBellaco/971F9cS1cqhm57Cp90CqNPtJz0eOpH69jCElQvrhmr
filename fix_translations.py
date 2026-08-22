with open('app/src/main/java/com/example/util/Translator.kt', 'r') as f:
    content = f.read()

content = content.replace('"Objetos" to "Itens",', '"Objetos" to "Itens",\n        "Ítems" to "Itens",\n        "Ítem" to "Item",')
content = content.replace('"Objetos" to "Items",', '"Objetos" to "Items",\n        "Ítems" to "Items",\n        "Ítem" to "Item",')

with open('app/src/main/java/com/example/util/Translator.kt', 'w') as f:
    f.write(content)
