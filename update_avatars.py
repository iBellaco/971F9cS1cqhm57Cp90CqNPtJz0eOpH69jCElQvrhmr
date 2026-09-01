import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r') as f:
    content = f.read()

content = content.replace('"Variado"', '"Poro"')

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'w') as f:
    f.write(content)

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'r') as f:
    content = f.read()

content = content.replace('region = "Variado"', 'region = "Poro"')

with open('app/src/main/java/com/example/data/AvatarCatalog.kt', 'w') as f:
    f.write(content)
