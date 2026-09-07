import sys
with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

start = content.find("fun evaluateVisualMatch")
end = content.find("fun findBestVisualMatch", start)
if start != -1 and end != -1:
    print(content[start:end])
else:
    print("Not found")
