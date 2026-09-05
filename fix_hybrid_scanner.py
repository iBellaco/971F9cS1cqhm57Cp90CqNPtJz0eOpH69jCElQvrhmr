with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write("""package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Rect
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.tasks.await
import java.util.Locale

data class DetectedChampionSlot(
    val champion: Champion,
    val isAlly: Boolean,
    val boundingBox: Rect?,
    val confidence: Float = 0.95f
)

data class DraftScanResult(
    val allies: List<Champion>,
    val enemies: List<Champion>,
    val alliesByRole: Map<LaneRole, Champion> = emptyMap(),
    val enemiesByRole: Map<LaneRole, Champion> = emptyMap(),
    val detectedRole: LaneRole? = null,
    val detectedRawWords: List<String> = emptyList(),
    val isSuccessful: Boolean,
    val statusMessage: String
)

object DraftVisionScanner {
    private const val TAG = "DraftVisionScanner"
    
    private var recognizerInstance: com.google.mlkit.vision.text.TextRecognizer? = null

    private fun getRecognizer(): com.google.mlkit.vision.text.TextRecognizer? {
        if (recognizerInstance == null) {
            try {
                recognizerInstance = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            } catch (e: Throwable) {
                AppLogger.e(TAG, "ML Kit TextRecognizer initialization warning", e)
            }
        }
        return recognizerInstance
    }

    suspend fun scanDraftFromBitmap(bitmap: Bitmap, preferredSummonerName: String? = null): DraftScanResult {
        if (bitmap.isRecycled || bitmap.width < bitmap.height) {
            return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "Esperando horizontal...")
        }

        val recognizer = getRecognizer() ?: return DraftScanResult(emptyList(), emptyList(), isSuccessful = false, statusMessage = "OCR no disponible")

        val width = bitmap.width
        val height = bitmap.height

        // 1. OCR para detectar roles de texto explícitos (CARRIL DE BARÓN, CENTRAL, SOPORTE, etc.)
        val image = InputImage.fromBitmap(bitmap, 0)
        val allySlotRoles = arrayOfNulls<LaneRole>(5)
        val enemySlotRoles = arrayOfNulls<LaneRole>(5)
        val detectedWords = mutableListOf<String>()

        try {
            val visionText = recognizer.process(image).await()
            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isBlank()) continue
                    detectedWords.add(lineText)

                    val lower = lineText.lowercase(Locale.ROOT)
                    val box = line.boundingBox
                    val centerY = box?.centerY() ?: 0
                    val centerX = box?.centerX() ?: 0
                    val yRatio = centerY.toFloat() / height.toFloat()
                    val xRatio = centerX.toFloat() / width.toFloat()

                    if (yRatio < 0.02f || yRatio > 0.98f) continue

                    val role = when {
                        lower.contains("central") || lower.contains("mid") || lower.contains("medio") -> LaneRole.MID
                        lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top") -> LaneRole.TOP
                        lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg") || lower.contains("selva") -> LaneRole.JUNGLE
                        lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador") -> LaneRole.ADC
                        lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup") -> LaneRole.SUPPORT
                        else -> null
                    }

                    if (role != null) {
                        val bucket = (yRatio * 5).toInt().coerceIn(0, 4)
                        if (xRatio < 0.5f) {
                            allySlotRoles[bucket] = role
                        } else {
                            enemySlotRoles[bucket] = role
                        }
                    }
                }
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error OCR", e)
        }

        // 2. IMAGE MATCHING para detectar Campeones por sus avatares en los banners
        val allChamps = WildRiftRepository.champions
        val allySlots = arrayOfNulls<Champion>(5)
        val enemySlots = arrayOfNulls<Champion>(5)

        val avatarWidth = (width * 0.15f).toInt() 
        val avatarHeight = (height * 0.12f).toInt()
        
        val allyX = (width * 0.04f).toInt().coerceIn(0, width - avatarWidth)
        val enemyX = (width * 0.81f).toInt().coerceIn(0, width - avatarWidth)

        for (i in 0..4) {
            val yCenter = height * (0.1f + (i * 0.2f))
            val startY = (yCenter - avatarHeight / 2).toInt().coerceIn(0, height - avatarHeight)
            
            try {
                val allyCrop = Bitmap.createBitmap(bitmap, allyX, startY, avatarWidth, avatarHeight)
                allySlots[i] = com.example.util.ImageHashMatcher.findBestMatch(allyCrop, allChamps)
                allyCrop.recycle()
            } catch (e: Exception) { /* ignore */ }
            
            try {
                val enemyCrop = Bitmap.createBitmap(bitmap, enemyX, startY, avatarWidth, avatarHeight)
                enemySlots[i] = com.example.util.ImageHashMatcher.findBestMatch(enemyCrop, allChamps)
                enemyCrop.recycle()
            } catch (e: Exception) { /* ignore */ }
        }

        // 3. COMBINAR RESULTADOS (Asignación Híbrida: Campeón -> Rol detectado)
        val alliesMap = mutableMapOf<LaneRole, Champion>()
        val enemiesMap = mutableMapOf<LaneRole, Champion>()
        val standardOrder = listOf(LaneRole.TOP, LaneRole.JUNGLE, LaneRole.MID, LaneRole.ADC, LaneRole.SUPPORT)

        // Aliados
        val availableAllyRoles = standardOrder.toMutableList()
        // 3a. Primero asignar los que OCR detectó explícitamente como texto de rol ("CARRIL DE BARÓN")
        for (i in 0..4) {
            val champ = allySlots[i] ?: continue
            val ocrRole = allySlotRoles[i]
            if (ocrRole != null && !alliesMap.containsKey(ocrRole)) {
                alliesMap[ocrRole] = champ
                availableAllyRoles.remove(ocrRole)
            }
        }
        // 3b. Luego asignar los restantes por su rol primario/secundario natural
        for (i in 0..4) {
            val champ = allySlots[i] ?: continue
            if (alliesMap.containsValue(champ)) continue

            val assignedRole = if (availableAllyRoles.contains(champ.primaryRole)) {
                champ.primaryRole
            } else {
                champ.secondaryRoles.firstOrNull { availableAllyRoles.contains(it) } ?: availableAllyRoles.firstOrNull()
            }
            
            if (assignedRole != null) {
                alliesMap[assignedRole] = champ
                availableAllyRoles.remove(assignedRole)
            }
        }

        // Enemigos
        val availableEnemyRoles = standardOrder.toMutableList()
        for (i in 0..4) {
            val champ = enemySlots[i] ?: continue
            val ocrRole = enemySlotRoles[i]
            if (ocrRole != null && !enemiesMap.containsKey(ocrRole)) {
                enemiesMap[ocrRole] = champ
                availableEnemyRoles.remove(ocrRole)
            }
        }
        for (i in 0..4) {
            val champ = enemySlots[i] ?: continue
            if (enemiesMap.containsValue(champ)) continue

            val assignedRole = if (availableEnemyRoles.contains(champ.primaryRole)) {
                champ.primaryRole
            } else {
                champ.secondaryRoles.firstOrNull { availableEnemyRoles.contains(it) } ?: availableEnemyRoles.firstOrNull()
            }
            
            if (assignedRole != null) {
                enemiesMap[assignedRole] = champ
                availableEnemyRoles.remove(assignedRole)
            }
        }

        // 4. Deduplicación global (un campeón no puede estar en ambos equipos)
        val allyChampIds = alliesMap.values.map { it.id }.toSet()
        val finalEnemiesMap = enemiesMap.filterNot { entry -> allyChampIds.contains(entry.value.id) }

        val allyChampsList = alliesMap.values.toList()
        val enemyChampsList = finalEnemiesMap.values.toList()
        val total = allyChampsList.size + enemyChampsList.size

        return DraftScanResult(
            allies = allyChampsList,
            enemies = enemyChampsList,
            alliesByRole = alliesMap,
            enemiesByRole = finalEnemiesMap,
            detectedRole = null,
            detectedRawWords = detectedWords,
            isSuccessful = total > 0,
            statusMessage = if (total > 0) "Escaneo Híbrido: ${'$'}total detectados" else "Esperando campeones..."
        )
    }
}
""")
