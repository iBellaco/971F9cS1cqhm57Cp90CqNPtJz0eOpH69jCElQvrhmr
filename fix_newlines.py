with open("app/src/main/java/com/example/util/Translator.kt", "r") as f:
    content = f.read()

content = content.replace('del meta.\n" to', 'del meta.\\n" to')
content = content.replace('do meta.\n",', 'do meta.\\n",')
content = content.replace('meta stats.\n",', 'meta stats.\\n",')

with open("app/src/main/java/com/example/util/Translator.kt", "w") as f:
    f.write(content)
