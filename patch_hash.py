import sys
with open("app/src/main/java/com/example/util/ChampionHashes.kt", "r") as f:
    content = f.read()

content = content.replace("ImageHashMatcher.getInnerCrop(bitmap, 0.85f)", "ImageHashMatcher.getInnerCrop(bitmap, 0.70f)")

with open("app/src/main/java/com/example/util/ChampionHashes.kt", "w") as f:
    f.write(content)

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    content = f.read()

content = content.replace("getInnerCrop(bitmap, 0.60f)", "getInnerCrop(bitmap, 0.70f)")
content = content.replace("getInnerCrop(bitmap, 0.85f)", "getInnerCrop(bitmap, 0.70f)")

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
    f.write(content)

