with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'r') as f:
    text = f.read()

# Fix the extra bracket
text = text.replace("        }\n        }\n    }\n}", "        }\n    }\n}")
# Fix import Icon
text = text.replace("import androidx.compose.material3.Text", "import androidx.compose.material3.Text\nimport androidx.compose.material3.Icon")

with open('app/src/main/java/com/example/ui/components/ChampionAvatar.kt', 'w') as f:
    f.write(text)
print("Fixed ChampionAvatar syntax")
