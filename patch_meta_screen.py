import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Fix "Inspiración" in treeCategories and filterOptions
text = text.replace(' "Inspiración"', '')
text = text.replace('"INSPIRATION" to tr("Inspiración")', '')
# Because of the comma before "INSPIRATION" to tr("Inspiración"):
text = re.sub(r',\s*"INSPIRATION" to tr\("Inspiración"\)', '', text)
text = re.sub(r',\s*"Inspiración"', '', text)
text = re.sub(r'rune\.category\.contains\("Inspiración", ignoreCase = true\) \|\| rune\.category\.contains\("Inspiration", ignoreCase = true\)', 'false', text)
text = re.sub(r'"INSPIRATION" -> false', '', text)

# Fix "Árbol / Grid" -> "Cuadrícula"
text = text.replace('"Árbol / Grid"', '"Cuadrícula"')

# Remove icons from titles
# Find this block:
'''
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 10.dp)
                                ) {
                                    Text(iconPrefix, fontSize = 14.sp)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = categoryName.uppercase(),
'''
# I will just remove the Text(iconPrefix... and the Spacer next to it.
text = re.sub(r'Text\(iconPrefix, fontSize = 14\.sp\)\s*Spacer\(modifier = Modifier\.width\(6\.dp\)\)', '', text)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)

