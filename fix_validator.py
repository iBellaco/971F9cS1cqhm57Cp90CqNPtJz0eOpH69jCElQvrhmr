import sys

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    content = f.read()

target = """    fun isValidChampionToken(token: String, championId: String): Boolean {
        val cleanToken = normalize(token)"""
replacement = """    fun isValidChampionToken(token: String, championId: String): Boolean {
        val cleanToken = normalize(token).replace(" ", "")"""
        
content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "w") as f:
    f.write(content)
print("Validator patched")
