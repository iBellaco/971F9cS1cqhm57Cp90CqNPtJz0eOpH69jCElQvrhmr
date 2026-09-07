import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

start = content.find("val totalScore = ")
end = content.find("val champ = ", start)
if start != -1 and end != -1:
    print(content[start:end+150])
else:
    print("Matcher Not found")
