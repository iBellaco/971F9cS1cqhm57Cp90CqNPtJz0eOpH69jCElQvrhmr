import re

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    text = f.read()

# Replace all unresolved 'foundRune' occurrences where I added the clickable modifier
# It seems the regex replacement for Row(verticalAlignment = ...) incorrectly matched other random Row declarations in the file instead of just the one inside the rune list.

text = text.replace(', modifier = Modifier.clickable { if (foundRune != null) runeForDetail = foundRune }', '')

# Now let's carefully find the ONE correct row in the rune section
rune_section = r'(val foundRune = allRunes\.find [^\n]+)\s*Row\(verticalAlignment = Alignment\.CenterVertically\)'
rune_section_new = r'\1\n                                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { if (foundRune != null) runeForDetail = foundRune })'
text = re.sub(rune_section, rune_section_new, text)

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(text)

