import sys

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    content = f.read()

target = """    fun isValidChampionToken(token: String, championId: String): Boolean {
        val cleanToken = normalize(token).replace(" ", "")
        val cleanChamp = normalize(championId).replace("_", "")"""
replacement = """    fun isValidChampionToken(token: String, championId: String): Boolean {
        val cleanToken = normalize(token).replace(Regex("[^a-z0-9]"), "")
        val cleanChamp = normalize(championId).replace(Regex("[^a-z0-9]"), "")"""
        
if "val cleanToken = normalize(token).replace(Regex" not in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "w") as f:
        f.write(content)
    print("Validator patched again")
