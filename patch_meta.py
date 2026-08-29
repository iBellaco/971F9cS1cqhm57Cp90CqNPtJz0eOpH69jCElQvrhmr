import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Add SubscriptionManager if not imported
if 'import com.example.util.SubscriptionManager' not in text:
    text = text.replace('import com.example.model.Champion', 'import com.example.model.Champion\nimport com.example.util.SubscriptionManager')

# Find fun MetaAndDraftScreen
def_index = text.find('fun MetaAndDraftScreen(')
# Find the start of the body
body_start = text.find('{', def_index) + 1

# Insert val isPremium by SubscriptionManager.isPremium.collectAsState()
if 'val isPremium by SubscriptionManager' not in text:
    text = text[:body_start] + '\n    val isPremium by SubscriptionManager.isPremium.collectAsState()' + text[body_start:]

# 1. Favoritos Filter Chip
# FilterChip( ... onClick = { isFavoritesFilter = !isFavoritesFilter } ...
text = re.sub(
    r'onClick = \{ isFavoritesFilter = !isFavoritesFilter \}',
    r'onClick = { if (isPremium) isFavoritesFilter = !isFavoritesFilter else android.widget.Toast.makeText(context, "Requiere Premium", android.widget.Toast.LENGTH_SHORT).show() }',
    text
)

# 2. Favoritos icon in Champion Grid (IconToggleButton)
# onCheckedChange = { isChecked ->
#     if (isChecked) {
#         WildRiftRepository.addFavorite(champ.id)
#     } else {
#         WildRiftRepository.removeFavorite(champ.id)
#     }
#     onFavoriteToggled()
# }
fav_replacement = """onCheckedChange = { isChecked ->
                                                if (!isPremium) {
                                                    android.widget.Toast.makeText(context, "Requiere Premium", android.widget.Toast.LENGTH_SHORT).show()
                                                    return@IconToggleButton
                                                }
                                                if (isChecked) {
                                                    WildRiftRepository.addFavorite(champ.id)
                                                } else {
                                                    WildRiftRepository.removeFavorite(champ.id)
                                                }
                                                onFavoriteToggled()
                                            }"""
text = re.sub(
    r'onCheckedChange = \{ isChecked ->\s*if \(isChecked\) \{\s*WildRiftRepository.addFavorite\(champ.id\)\s*\} else \{\s*WildRiftRepository.removeFavorite\(champ.id\)\s*\}\s*onFavoriteToggled\(\)\s*\}',
    fav_replacement,
    text
)

# 3. Guardar Draft
# Button( onClick = { showSaveDraftDialog = true } ... 
text = re.sub(
    r'Button\(\s*onClick = \{ showSaveDraftDialog = true \}',
    r'Button(\n                        onClick = { if (isPremium) showSaveDraftDialog = true else android.widget.Toast.makeText(context, "Requiere Premium", android.widget.Toast.LENGTH_SHORT).show() }',
    text
)

# 4. Historial
# Button( onClick = onNavigateToDraftHistory ...
text = re.sub(
    r'Button\(\s*onClick = onNavigateToDraftHistory',
    r'Button(\n                        onClick = { if (isPremium) onNavigateToDraftHistory() else android.widget.Toast.makeText(context, "Requiere Premium", android.widget.Toast.LENGTH_SHORT).show() }',
    text
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
