import sys

with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "r") as f:
    content = f.read()

target = """        "darius" to "darius","""
replacement = """        "diego" to "diego", // Ignorar nombre de invocador común
        "darius" to "darius","""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/ChampionNameResolver.kt", "w") as f:
        f.write(content)
    print("Added diego to map")
else:
    print("Could not find target")
