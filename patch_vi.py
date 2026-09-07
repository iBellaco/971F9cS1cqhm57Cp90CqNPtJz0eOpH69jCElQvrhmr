import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = 'val words = clean.split(" ").filter { it.length >= 3 && !UI_IGNORE_WORDS.contains(it) }'
replacement = 'val words = clean.split(" ").filter { it.length >= 2 && !UI_IGNORE_WORDS.contains(it) }'

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
    f.write(content)
print("Patched Vi bug")
