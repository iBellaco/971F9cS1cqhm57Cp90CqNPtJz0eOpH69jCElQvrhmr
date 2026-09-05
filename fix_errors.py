import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text_overlay = f.read()

# isDeviceLandscape needs to be accessible in the Compose block. 
# Wait, I defined `private val isDeviceLandscape` in the class `FloatingAssistantService`? Yes!
# But in `FloatingDraftCoachView`, how did I access it? `val isLandscapeMode by isDeviceLandscape`
# Let's check where the error is: line 2149.
# Let's just fix it properly by passing the value, or making sure the reference works.
text_overlay = text_overlay.replace("val isLandscapeMode by isDeviceLandscape", "val isLandscapeMode = isDeviceLandscape.value")
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text_overlay)

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text_scanner = f.read()

# Conflicting declarations: detectedEnemyChamps
text_scanner = text_scanner.replace("val detectedEnemyChamps = enemySlots.filterNotNull()", "val finalEnemyChamps = enemySlots.filterNotNull()")
text_scanner = text_scanner.replace("for (champ in detectedEnemyChamps)", "for (champ in finalEnemyChamps)")
text_scanner = text_scanner.replace("val roleMatches = detectedEnemyChamps.count", "val roleMatches = finalEnemyChamps.count")

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text_scanner)

