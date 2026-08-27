import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

old_code = """            val jsonString = context.assets.open("champions.json").bufferedReader().use { it.readText() }"""
new_code = """            val bytes = context.assets.open("champions.json").use { it.readBytes() }
            val jsonString = String(bytes, Charsets.UTF_8)"""

content = content.replace(old_code, new_code)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
