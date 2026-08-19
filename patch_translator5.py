import re

file_path = "app/src/main/java/com/example/util/Translator.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will also just build the app, maybe it's not strictly necessary to remove the unused translations, but it keeps things clean.
