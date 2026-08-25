import re

with open('app/src/main/java/com/example/model/Champion.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = re.sub(r'val description: String(\s*\))', r'val description: String,\n    val descriptionEn: String = "",\n    val descriptionPt: String = ""\1', content)

with open('app/src/main/java/com/example/model/Champion.kt', 'w', encoding='utf-8') as f:
    f.write(content)
