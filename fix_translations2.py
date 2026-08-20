with open("app/src/main/java/com/example/util/Translator.kt", "r") as f:
    content = f.read()

pt_add = '''
        "Inicio" to "Início",
        "Catálogo" to "Catálogo",
'''

en_add = '''
        "Inicio" to "Home",
        "Catálogo" to "Catalog",
'''

# insert into pt dictionary
content = content.replace('val pt = mapOf(', 'val pt = mapOf(' + pt_add)
content = content.replace('val en = mapOf(', 'val en = mapOf(' + en_add)

with open("app/src/main/java/com/example/util/Translator.kt", "w") as f:
    f.write(content)
