import re

with open("app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt") as f:
    content = f.read()

# Look for situational items rendering logic
idx = content.find("Card(1")
print(content[idx:idx+100])
