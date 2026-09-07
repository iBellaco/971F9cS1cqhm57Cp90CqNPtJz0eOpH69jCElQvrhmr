import sys
with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

target = """        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            enemyConfidencesByRole = enemyConfidences,
            detectedRole = userDetectedLane,
            detectedRawWords = detectedWords,
            discrepancies = auditList,
            diagnostics = diagnosticsList,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )
        
        lastDebugBitmap.value = bitmap.copy(Bitmap.Config.ARGB_8888, false)
        lastDiagnostics.value = diagnosticsList"""

replacement = """        lastDebugBitmap.value = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
        lastDiagnostics.value = diagnosticsList

        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            enemyConfidencesByRole = enemyConfidences,
            detectedRole = userDetectedLane,
            detectedRawWords = detectedWords,
            discrepancies = auditList,
            diagnostics = diagnosticsList,
            isSuccessful = total > 0,
            statusMessage = statusMsg
        )"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
