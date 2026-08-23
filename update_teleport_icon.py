import re

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'r') as f:
    text = f.read()

text = re.sub(
    r'const val SPELL_TELEPORT = "https://wr-meta.com/uploads/posts/2025-07/1753389748_teleport-enchant.webp"',
    'const val SPELL_TELEPORT = "https://i.postimg.cc/13w5fnWb/1611110740-teleport-enchant.png"',
    text
)

with open('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'w') as f:
    f.write(text)

with open('app/build.gradle.kts', 'r') as f:
    text = f.read()

text = re.sub(r'versionCode = 150', 'versionCode = 151', text)
text = re.sub(r'versionName = "2.49"', 'versionName = "2.50"', text)

with open('app/build.gradle.kts', 'w') as f:
    f.write(text)
