with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    c = f.read()
c = c.replace("^[0-9\\s:.,%#-]+$", "^[0-9\\\\s:.,%#-]+$")
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(c)
