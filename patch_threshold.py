import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

content = content.replace("val minThreshold = 0.90f", "val minThreshold = 0.75f")

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
    f.write(content)

