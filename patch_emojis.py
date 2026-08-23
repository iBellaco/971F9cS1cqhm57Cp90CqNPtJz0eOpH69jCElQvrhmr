import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# 1. Tab label
text = text.replace('"${cat.iconEmoji} ${com.example.util.tr(cat.displayName)}', '"${com.example.util.tr(cat.displayName)}')

# 2. Section title Row
text = re.sub(
    r'Text\(category\.iconEmoji, fontSize = 14\.sp\)\s*Spacer\(modifier = Modifier\.width\(6\.dp\)\)',
    '',
    text
)

# 3. Item detail category
text = text.replace('"${item.category.iconEmoji} ${tr(item.category.displayName)}"', 'tr(item.category.displayName)')

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)

