import re

file_path = "app/src/main/java/com/example/data/WildRiftRepository.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

pattern = re.compile(r'    val metaSources: List<MetaDataSource> = listOf\(.*?    \)\n', re.DOTALL)
content = pattern.sub("", content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Meta sources removed from repository!")
