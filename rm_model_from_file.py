import re

file_path = "app/src/main/java/com/example/model/Champion.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pattern = re.compile(r'data class MetaDataSource\(.*?\)\n', re.DOTALL)
content = pattern.sub("", content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

file_path2 = "app/src/main/java/com/example/data/WildRiftRepository.kt"
with open(file_path2, "r", encoding="utf-8") as f:
    content2 = f.read()

content2 = content2.replace('import com.example.model.MetaDataSource\n', '')

with open(file_path2, "w", encoding="utf-8") as f:
    f.write(content2)

print("MetaDataSource finally removed from Champion.kt and repo!")
