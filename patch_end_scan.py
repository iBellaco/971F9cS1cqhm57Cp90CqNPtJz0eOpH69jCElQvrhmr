import sys

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

old_end = """        return DraftScanResult(
            allySlots = allyResolved.assignments.map { it.value }.take(5),
            enemySlots = enemyResolved.assignments.map { it.value }.take(5),
            allyTeamComposition = allyMapConverted,
            enemyTeamComposition = enemyMapConverted,
            diagnostics = allDiagnostics,
            isSuccessful = true,
            statusMessage = "Escaneo completado. Total detectados: $total. Resolución visual estricta aplicada."
        )"""

new_end = """        // Guardar bitmap de depuración
        try {
            val oldBitmap = lastDebugBitmap
            lastDebugBitmap = bitmap.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
            if (oldBitmap != null && !oldBitmap.isRecycled) {
                oldBitmap.recycle()
            }
            lastDiagnostics = allDiagnostics
        } catch (e: Exception) {
            // Ignorar OOM
        }

        return DraftScanResult(
            allySlots = allyResolved.assignments.map { it.value }.take(5),
            enemySlots = enemyResolved.assignments.map { it.value }.take(5),
            allyTeamComposition = allyMapConverted,
            enemyTeamComposition = enemyMapConverted,
            diagnostics = allDiagnostics,
            isSuccessful = true,
            statusMessage = "Escaneo completado. Total detectados: $total. Resolución visual estricta aplicada."
        )"""

content = content.replace(old_end, new_end)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)

