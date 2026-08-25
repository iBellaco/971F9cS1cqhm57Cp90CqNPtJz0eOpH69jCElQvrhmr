import re

# 1. Update Champion.kt
with open('app/src/main/java/com/example/model/Champion.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace("val name: String = \"\",", "val name: String = \"\",\n    val nameEn: String = \"\",\n    val namePt: String = \"\",")
content = content.replace("val title: String = \"\",", "val title: String = \"\",\n    val titleEn: String = \"\",\n    val titlePt: String = \"\",")
content = content.replace("val name: String,", "val name: String,\n    val nameEn: String = \"\",\n    val namePt: String = \"\",")
content = content.replace("val stats: String,", "val stats: String,\n    val statsEn: String = \"\",\n    val statsPt: String = \"\",")
content = content.replace("val passive: String,", "val passive: String,\n    val passiveEn: String = \"\",\n    val passivePt: String = \"\",")
content = content.replace("val description: String,", "val description: String,\n    val descriptionEn: String = \"\",\n    val descriptionPt: String = \"\",")

with open('app/src/main/java/com/example/model/Champion.kt', 'w', encoding='utf-8') as f:
    f.write(content)

