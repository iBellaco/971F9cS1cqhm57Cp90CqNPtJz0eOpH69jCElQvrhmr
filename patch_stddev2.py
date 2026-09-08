import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

target = """            val isAllyEmptyOrFlat = (stdDev < 13f) || (avgLuminance < 25f)"""
replacement = """            val isAllyEmptyOrFlat = (stdDev < 10f) || (avgLuminance < 20f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
        f.write(content)
    print("Patched isAllyEmptyOrFlat")
else:
    print("Could not find target")

