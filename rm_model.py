import os
import re

# We should also remove the MetaDataSource data class if we can
file_path = "app/src/main/java/com/example/data/WildRiftRepository.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pattern = re.compile(r'data class MetaDataSource\(.*?\)\n\n', re.DOTALL)
content = pattern.sub("", content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("MetaDataSource removed!")
