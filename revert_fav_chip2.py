import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
    '''                // 1. Favoritos Filter Chip
                val favCount = favorites.size
                if (favCount > 0) {
                FilterChip(''',
    '''                // 1. Favoritos Filter Chip
                val favCount = favorites.size
                FilterChip('''
)

content = content.replace(
    '''                        borderColor = HextechGold,
                        borderWidth = 1.dp
                    )
                )
                }''',
    '''                        borderColor = HextechGold,
                        borderWidth = 1.dp
                    )
                )'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
