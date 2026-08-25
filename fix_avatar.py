import re

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace('import coil.request.ImageRequest\\nimport coil.imageLoader', 'import coil.request.ImageRequest\nimport coil.imageLoader')

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w', encoding='utf-8') as f:
    f.write(content)
