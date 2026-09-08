import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

target = "val minThreshold = 0.60f"
replacement = "val minThreshold = 0.40f"

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
        f.write(content)
    print("Lowered ImageHashMatcher minThreshold to 0.40f")
else:
    print("Could not find target in ImageHashMatcher")

