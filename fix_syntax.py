with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    text = f.read()

# I see it left a `."        )` at the end. Let's just fix the end of the file.
text = text[:text.find('// 6. INSPIRACIÓN')] + "    )\n}"

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(text)
