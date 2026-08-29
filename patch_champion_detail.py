import re

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    text = f.read()

# Add SubscriptionManager if not imported
if 'import com.example.util.SubscriptionManager' not in text:
    text = text.replace('import com.example.model.Champion', 'import com.example.model.Champion\nimport com.example.util.SubscriptionManager\nimport androidx.compose.runtime.collectAsState\nimport androidx.compose.runtime.getValue')

# Find fun ChampionDetailSheet
def_index = text.find('fun ChampionDetailSheet(')
# Find the start of the body
body_start = text.find('{', def_index) + 1

# Insert val isPremium by SubscriptionManager.isPremium.collectAsState()
if 'val isPremium by SubscriptionManager' not in text:
    text = text[:body_start] + '\n    val isPremium by SubscriptionManager.isPremium.collectAsState()' + text[body_start:]

# Favoritos IconToggleButton
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
                            isFavorite = isChecked
                            onFavoriteToggled()
                        }"""
text = re.sub(
    r'onCheckedChange = \{ isChecked ->\s*if \(isChecked\) \{\s*WildRiftRepository.addFavorite\(champ.id\)\s*\} else \{\s*WildRiftRepository.removeFavorite\(champ.id\)\s*\}\s*isFavorite = isChecked\s*onFavoriteToggled\(\)\s*\}',
    fav_replacement,
    text
)

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(text)
