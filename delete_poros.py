import re

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'r') as f:
    content = f.read()

# First, fix Aatrox to "Runaterra"
content = content.replace('''        AvatarItem(
            id = "aatrox",
            name = "Aatrox",
            title = "la Espada de los Oscuros",
            region = "Poro",''', '''        AvatarItem(
            id = "aatrox",
            name = "Aatrox",
            title = "la Espada de los Oscuros",
            region = "Runaterra",''')

# Now remove all AvatarItem blocks that have region = "Poro"
# Since they are formatted with whitespace, we can use a regex:
# \s*AvatarItem\([^)]*region = "Poro"[^)]*\),?
pattern = re.compile(r'\s*AvatarItem\(\s*id = "[^"]+",\s*name = "[^"]+",\s*title = "[^"]+",\s*region = "Poro",.*?\),?', re.DOTALL)
content = re.sub(pattern, '', content)

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'w') as f:
    f.write(content)
