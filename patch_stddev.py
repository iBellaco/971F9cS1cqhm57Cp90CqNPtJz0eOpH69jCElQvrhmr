import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

target = """            val isSpartanHelmetOrEmpty = (avgSaturation < 0.14f && (avgLuminance < 80f || stdDev < 22f)) ||
                    (stdDev < 16f) || (avgLuminance < 35f)"""
replacement = """            val isSpartanHelmetOrEmpty = (avgSaturation < 0.14f && (avgLuminance < 80f || stdDev < 22f)) ||
                    (stdDev < 10f) || (avgLuminance < 25f)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
        f.write(content)
    print("Patched isSpartanHelmetOrEmpty")
else:
    print("Could not find target")

