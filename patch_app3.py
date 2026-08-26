with open("app/src/main/java/com/example/WildRiftApp.kt", "r") as f:
    text = f.read()

text = text.replace('is_db_seeded_multi_lang_v3', 'is_db_seeded_multi_lang_v4')

with open("app/src/main/java/com/example/WildRiftApp.kt", "w") as f:
    f.write(text)
