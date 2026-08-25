import re

# Fix MainDraftingScreen
with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r', encoding='utf-8') as f:
    content = f.read()
content = content.replace('import com.example.ui.components.DownloadProgressDialog', 'import com.example.ui.components.DownloadProgressWidget')
with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w', encoding='utf-8') as f:
    f.write(content)

# Fix ChampionAvatar
with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r', encoding='utf-8') as f:
    content = f.read()
if 'import coil.imageLoader' not in content:
    content = content.replace('import coil.request.ImageRequest', 'import coil.request.ImageRequest\\nimport coil.imageLoader')
with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w', encoding='utf-8') as f:
    f.write(content)

