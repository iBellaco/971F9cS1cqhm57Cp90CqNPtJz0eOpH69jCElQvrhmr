with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r') as f:
    text = f.read()

text = text.replace("import androidx.compose.material.icons.Icons", "import androidx.compose.material.icons.Icons\nimport androidx.compose.material.icons.filled.Warning")

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w') as f:
    f.write(text)
