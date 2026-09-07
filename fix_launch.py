import sys

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    content = f.read()

target = "coroutineScope.kotlinx.coroutines.launch"
replacement = "coroutineScope.launch"

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
        f.write(content)
    print("Fixed launch syntax")
else:
    print("Target not found")
