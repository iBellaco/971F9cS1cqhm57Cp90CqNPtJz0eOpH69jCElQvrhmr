import re

with open('app/src/main/java/com/example/model/Champion.kt', 'r') as f:
    content = f.read()

target = """@Serializable
data class DraftAnalysisResult(
    val physicalDamagePercent: Int,
    val magicDamagePercent: Int,
    val trueDamagePercent: Int,
    val frontlineStatus: String,"""

replacement = """@Serializable
data class DraftAnalysisResult(
    val physicalDamagePercent: Int,
    val magicDamagePercent: Int,
    val trueDamagePercent: Int,
    val allyPhysicalDamagePercent: Int = 0,
    val allyMagicDamagePercent: Int = 0,
    val allyTrueDamagePercent: Int = 0,
    val allyCompositionWarning: String? = null,
    val frontlineStatus: String,"""

content = content.replace(target, replacement)
with open('app/src/main/java/com/example/model/Champion.kt', 'w') as f:
    f.write(content)
print("Done")
