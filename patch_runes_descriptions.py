import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    content = f.read()

# I will write a regex loop to remove Inspiration runes.
# But instead of a huge regex, let's just delete the 3 Inspiration runes manually.
