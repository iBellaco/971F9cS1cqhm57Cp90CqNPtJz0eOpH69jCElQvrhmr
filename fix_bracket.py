with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    content = f.read()

# I missed a closing brace when I replaced the block, so let's just append it.
content = content + "\n}"

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(content)
