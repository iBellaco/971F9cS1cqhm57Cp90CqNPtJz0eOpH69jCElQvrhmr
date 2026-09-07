import sys

with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "r") as f:
    matcher_content = f.read()

# Let's adjust the minimum threshold, the red/blue tint might be dragging scores down too far even with a good mask
threshold_target = """                if (totalScore > bestScore) {
                    bestScore = totalScore
                    bestChamp = champ
                }"""
threshold_replacement = """                if (totalScore > bestScore) {
                    bestScore = totalScore
                    bestChamp = champ
                }
            }
            
            // Penalizamos fuertemente las detecciones con baja correlación
            if (bestScore < 0.35f) {
                bestChamp = null
            }"""

if "if (bestScore < 0.35f)" not in matcher_content:
    matcher_content = matcher_content.replace(threshold_target, threshold_replacement)
    with open("app/src/main/java/com/example/util/ImageHashMatcher.kt", "w") as f:
        f.write(matcher_content)
    print("Threshold updated")
