import re

file_path = "app/src/main/java/com/example/ui/screens/InfoScreen.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will just revert them to strings so it builds for now, to ensure I don't break anything else
# I already sed replaced them above. Let me check if they are replaced.
