import sys

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    content = f.read()

target = """        val isMixedCase = trimmed.any { it.isUpperCase() } && trimmed.any { it.isLowerCase() }
        val hasSpaces = trimmed.contains(" ")

        // Excluir nombres canónicos de campeones con espacios
        val lower = trimmed.lowercase(Locale.ROOT)
        if (lower.startsWith("dr") || lower.startsWith("jarvan") || lower.startsWith("twisted") ||
            lower.startsWith("xin") || lower.startsWith("aurelion") || lower.startsWith("lee") ||
            lower.startsWith("miss") || lower.startsWith("master") || lower.startsWith("tahm") ||
            lower.startsWith("nunu")) {
            return false
        }"""

replacement = """        val isMixedCase = trimmed.any { it.isUpperCase() } && trimmed.any { it.isLowerCase() }
        val hasSpaces = trimmed.contains(" ")

        // Excluir nombres canónicos de campeones con espacios
        val lower = trimmed.lowercase(java.util.Locale.ROOT)
        if (lower.startsWith("dr") || lower.startsWith("jarvan") || lower.startsWith("twisted") ||
            lower.startsWith("xin") || lower.startsWith("aurelion") || lower.startsWith("lee") ||
            lower.startsWith("miss") || lower.startsWith("master") || lower.startsWith("tahm") ||
            lower.startsWith("nunu") || lower.startsWith("kha")) {
            return false
        }"""
        
content = content.replace(target, replacement)
with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "w") as f:
    f.write(content)
print("Summoner logic patched")
