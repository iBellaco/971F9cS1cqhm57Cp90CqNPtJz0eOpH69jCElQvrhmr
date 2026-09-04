package com.example.service.screen

import android.graphics.Bitmap
import android.graphics.Color
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
import kotlin.math.sqrt

data class DetectedChampionSlot(
    val champion: Champion,
    val isAlly: Boolean,
    val boundingBox: Rect?,
    val confidence: Float = 0.95f
)

data class DraftScanResult(
    val allies: List<Champion>,
    val enemies: List<Champion>,
    val detectedRole: com.example.model.LaneRole? = null,
    val detectedRawWords: List<String>,
    val isSuccessful: Boolean,
    val statusMessage: String
)

/**
 * Motor de Visión Computacional y Reconocimiento Óptico (OCR) para la pantalla de selección de campeón en Wild Rift.
 * Detecta campeones aliados (columna izquierda) y enemigos (columna derecha) a partir del frame de captura de pantalla.
 */
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

    // Mapa de alias comunes para campeones de Wild Rift (mapeados tanto por ID canónico como nombre)
    private val aliasMap = mapOf(
        "tf" to "twisted_fate",
        "twisted" to "twisted_fate",
        "twisted fate" to "twisted_fate",
        "mf" to "miss_fortune",
        "fortune" to "miss_fortune",
        "miss fortune" to "miss_fortune",
        "mundo" to "dr_mundo",
        "dr mundo" to "dr_mundo",
        "dr. mundo" to "dr_mundo",
        "dr.mundo" to "dr_mundo",
        "yi" to "master_yi",
        "master" to "master_yi",
        "master yi" to "master_yi",
        "aurelion" to "aurelion_sol",
        "sol" to "aurelion_sol",
        "asol" to "aurelion_sol",
        "aurelion sol" to "aurelion_sol",
        "jarvan" to "jarvan_iv",
        "jarvan 4" to "jarvan_iv",
        "jarvan iv" to "jarvan_iv",
        "j4" to "jarvan_iv",
        "nunu" to "nunu_willump",
        "willump" to "nunu_willump",
        "nunu & willump" to "nunu_willump",
        "nunu y willump" to "nunu_willump",
        "xin" to "xin_zhao",
        "zhao" to "xin_zhao",
        "xin zhao" to "xin_zhao",
        "lee" to "lee_sin",
        "sin" to "lee_sin",
        "lee sin" to "lee_sin",
        "tahm" to "tahm_kench",
        "kench" to "tahm_kench",
        "tahm kench" to "tahm_kench",
        "tk" to "tahm_kench",
        "renata" to "renata_glasc",
        "glasc" to "renata_glasc",
        "renata glasc" to "renata_glasc",
        "wukong" to "wukong",
        "monkey" to "wukong",
        "cait" to "caitlyn",
        "caitlin" to "caitlyn",
        "ez" to "ezreal",
        "eve" to "evelynn",
        "kass" to "kassadin",
        "kata" to "katarina",
        "kz" to "kha_zix",
        "k6" to "kha_zix",
        "kha" to "kha_zix",
        "kha'zix" to "kha_zix",
        "khazix" to "kha_zix",
        "renek" to "renekton",
        "vlad" to "vladimir",
        "voli" to "volibear",
        "yas" to "yasuo",
        "luc" to "lucian",
        "tris" to "tristana",
        "naut" to "nautilus",
        "ww" to "warwick",
        "kaisa" to "kai_sa",
        "kai'sa" to "kai_sa",
        "ksante" to "k_sante",
        "k'sante" to "k_sante",
        "chogath" to "cho_gath",
        "cho'gath" to "cho_gath",
        "velkoz" to "vel_koz",
        "vel'koz" to "vel_koz",
        "kogmaw" to "kog_maw",
        "kog'maw" to "kog_maw",
        "reksai" to "rek_sai",
        "rek'sai" to "rek_sai"
    )

    private val ignoredWords = setOf(
        "fase", "seleccion", "selección", "elegir", "confirmar", "bloquear", "bloqueo", "bloqueos",
        "ban", "bans", "maestria", "maestría", "nivel", "level", "jugador", "player", "miembro", "member",
        "wild", "rift", "ranked", "clasificatoria", "normal", "aram", "pvp", "victoria", "derrota",
        "equipo", "team", "azul", "rojo", "blue", "red", "chat", "mute", "op", "fps", "ms", "ping"
    )

    /**
     * Escanea el Bitmap de la pantalla capturada e identifica los campeones en selección y el rol asignado al jugador.
     */
    suspend fun scanDraftFromBitmap(bitmap: Bitmap, preferredSummonerName: String? = null): DraftScanResult {
        return try {
            val recognizer = getRecognizer() ?: return DraftScanResult(
                allies = emptyList(),
                enemies = emptyList(),
                detectedRawWords = emptyList(),
                isSuccessful = false,
                statusMessage = "El servicio de visión no se encuentra disponible en este entorno."
            )

            // Si el bitmap viene en vertical (portrait), rotarlo a horizontal para alinear la lectura con Wild Rift
            val processBitmap = if (bitmap.width < bitmap.height) {
                val matrix = android.graphics.Matrix().apply { postRotate(90f) }
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            } else {
                bitmap
            }

            val inputImage = InputImage.fromBitmap(processBitmap, 0)
            val visionText = recognizer.process(inputImage).await()

            val detectedWords = mutableListOf<String>()
            val foundAllies = mutableListOf<Champion>()
            val foundEnemies = mutableListOf<Champion>()

            val screenWidth = processBitmap.width
            val screenHeight = processBitmap.height
            val allChamps = WildRiftRepository.champions

            // Coordenadas relativas de las 5 ranuras de aliados (Columna Izquierda)
            val allySlotYCenters = floatArrayOf(0.185f, 0.335f, 0.485f, 0.635f, 0.785f)
            val allySlotRoles = arrayOfNulls<LaneRole>(5)
            val allySlotTexts = Array(5) { mutableListOf<String>() }

            // 1. Análisis de Resaltado Cian en el borde izquierdo de cada ranura (Identificador oficial de "TÚ / Yo voy")
            val cyanCounts = IntArray(5)
            val sampleXMin = (screenWidth * 0.035f).toInt().coerceAtLeast(0)
            val sampleXMax = (screenWidth * 0.070f).toInt().coerceAtMost(screenWidth - 1)

            for (i in 0 until 5) {
                val yCenter = (screenHeight * allySlotYCenters[i]).toInt()
                val yMin = (yCenter - screenHeight * 0.035f).toInt().coerceAtLeast(0)
                val yMax = (yCenter + screenHeight * 0.035f).toInt().coerceAtMost(screenHeight - 1)

                var cCount = 0
                for (y in yMin..yMax step 2) {
                    for (x in sampleXMin..sampleXMax step 2) {
                        val p = processBitmap.getPixel(x, y)
                        val r = (p shr 16) and 0xFF
                        val g = (p shr 8) and 0xFF
                        val b = p and 0xFF
                        // Detección de color cian brillante (borde de ranura del jugador local en Wild Rift)
                        if (b > 115 && g > 85 && b > r + 25) {
                            cCount++
                        }
                    }
                }
                cyanCounts[i] = cCount
            }

            // 2. Procesamiento de Texto OCR para asignar líneas a ranuras y detectar roles
            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    if (lineText.isNotBlank()) {
                        detectedWords.add(lineText)
                    }
                    val box = line.boundingBox
                    val centerX = box?.centerX() ?: 0
                    val centerY = box?.centerY() ?: 0

                    if (centerX < screenWidth * 0.42f) {
                        // Buscar la ranura aliada más cercana verticalmente
                        val slotIdx = allySlotYCenters.indices.minByOrNull {
                            val slotY = (screenHeight * allySlotYCenters[it]).toInt()
                            kotlin.math.abs(centerY - slotY)
                        } ?: -1

                        if (slotIdx in 0 until 5) {
                            allySlotTexts[slotIdx].add(lineText)
                            val lower = lineText.lowercase(Locale.ROOT)
                            if (lower.contains("central") || lower.contains("mid") || lower.contains("medio")) {
                                allySlotRoles[slotIdx] = LaneRole.MID
                            } else if (lower.contains("baron") || lower.contains("barón") || lower.contains("solo") || lower.contains("superior") || lower.contains("top")) {
                                allySlotRoles[slotIdx] = LaneRole.TOP
                            } else if (lower.contains("jungle") || lower.contains("jungla") || lower.contains("jg")) {
                                allySlotRoles[slotIdx] = LaneRole.JUNGLE
                            } else if (lower.contains("duo") || lower.contains("dúo") || lower.contains("dragon") || lower.contains("dragón") || lower.contains("bot") || lower.contains("adc") || lower.contains("tirador")) {
                                allySlotRoles[slotIdx] = LaneRole.ADC
                            } else if (lower.contains("support") || lower.contains("soporte") || lower.contains("apoyo") || lower.contains("sup")) {
                                allySlotRoles[slotIdx] = LaneRole.SUPPORT
                            }
                        }
                    }
                }
            }

            // 3. Determinar la ranura activa del jugador local ("yo voy")
            var userSlotIndex: Int? = null

            // Prioridad A: Coincidencia por nombre de invocador
            val normPreferred = preferredSummonerName?.let { normalizeString(it) }
            if (!normPreferred.isNullOrBlank() && normPreferred.length >= 3) {
                for (i in 0 until 5) {
                    val hasName = allySlotTexts[i].any { txt ->
                        val normTxt = normalizeString(txt)
                        normTxt.contains(normPreferred) || normPreferred.contains(normTxt)
                    }
                    if (hasName) {
                        userSlotIndex = i
                        AppLogger.d(TAG, "Jugador local identificado por nombre de invocador en ranura $i")
                        break
                    }
                }
            }

            // Prioridad B: Resaltado cian del marco de jugador local
            if (userSlotIndex == null) {
                val maxCyanIdx = cyanCounts.indices.maxByOrNull { cyanCounts[it] } ?: -1
                if (maxCyanIdx != -1 && cyanCounts[maxCyanIdx] >= 8) {
                    userSlotIndex = maxCyanIdx
                    AppLogger.d(TAG, "Jugador local identificado por borde cian en ranura $maxCyanIdx (score: ${cyanCounts[maxCyanIdx]})")
                }
            }

            val detectedRole: LaneRole? = if (userSlotIndex != null) {
                allySlotRoles[userSlotIndex]
            } else {
                null
            }

            // 4. Reconocimiento Visual por Iconos de Avatares Circulares (Aliados y Enemigos)
            for (i in 0 until 5) {
                val cx = (screenWidth * 0.115f).toInt()
                val cy = (screenHeight * allySlotYCenters[i]).toInt()
                val radius = (screenHeight * 0.042f).toInt()

                val detectedChamp = matchChampionFromAvatarCircle(
                    processBitmap, cx, cy, radius, allySlotRoles[i], allChamps
                )
                if (detectedChamp != null && foundAllies.none { it.id == detectedChamp.id } && foundAllies.size < 5) {
                    foundAllies.add(detectedChamp)
                    AppLogger.d(TAG, "Aliado detectado por icono en ranura $i: ${detectedChamp.name}")
                }
            }

            for (i in 0 until 5) {
                val cx = (screenWidth * 0.885f).toInt()
                val cy = (screenHeight * allySlotYCenters[i]).toInt()
                val radius = (screenHeight * 0.042f).toInt()

                val detectedChamp = matchChampionFromAvatarCircle(
                    processBitmap, cx, cy, radius, null, allChamps
                )
                if (detectedChamp != null && foundEnemies.none { it.id == detectedChamp.id } && foundEnemies.size < 5) {
                    foundEnemies.add(detectedChamp)
                    AppLogger.d(TAG, "Enemigo detectado por icono en ranura $i: ${detectedChamp.name}")
                }
            }

            // 5. Reconocimiento Complementario por OCR (Chat, búsqueda y hover central)
            for (block in visionText.textBlocks) {
                for (line in block.lines) {
                    val lineText = line.text.trim()
                    val box = line.boundingBox
                    val centerX = box?.centerX() ?: 0
                    val centerY = box?.centerY() ?: 0

                    // Ignorar la fila superior de BANS (Y < 8%) y extremos inferiores (Y > 93%)
                    if (centerY < screenHeight * 0.08f || centerY > screenHeight * 0.93f) {
                        continue
                    }

                    val candidateChamps = mutableListOf<Champion>()
                    candidateChamps.addAll(matchChampions(lineText, allChamps))

                    val words = lineText.split(Regex("[\\s,.:/()_-]+")).filter { it.isNotBlank() }
                    for (w in words) {
                        if (!ignoredWords.contains(w.lowercase(Locale.ROOT))) {
                            candidateChamps.addAll(matchChampions(w, allChamps))
                        }
                    }

                    for (matchedChamp in candidateChamps.distinctBy { it.id }) {
                        if (centerX < screenWidth * 0.45f) {
                            if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                foundAllies.add(matchedChamp)
                                AppLogger.d(TAG, "Aliado detectado (Texto Izquierda): ${matchedChamp.name}")
                            }
                        } else if (centerX > screenWidth * 0.55f) {
                            if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                foundEnemies.add(matchedChamp)
                                AppLogger.d(TAG, "Enemigo detectado (Texto Derecha): ${matchedChamp.name}")
                            }
                        } else {
                            // Centro: Hover / Campeón seleccionado actualmente
                            if (centerY in (screenHeight * 0.12f).toInt()..(screenHeight * 0.78f).toInt()) {
                                if (foundAllies.size <= foundEnemies.size) {
                                    if (foundAllies.none { it.id == matchedChamp.id } && foundAllies.size < 5) {
                                        foundAllies.add(matchedChamp)
                                        AppLogger.d(TAG, "Aliado detectado (Centro-Hover): ${matchedChamp.name}")
                                    }
                                } else {
                                    if (foundEnemies.none { it.id == matchedChamp.id } && foundEnemies.size < 5) {
                                        foundEnemies.add(matchedChamp)
                                        AppLogger.d(TAG, "Enemigo detectado (Centro-Hover): ${matchedChamp.name}")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            val totalDetected = foundAllies.size + foundEnemies.size
            val status = if (totalDetected > 0 || detectedRole != null) {
                "Escaneo exitoso: $totalDetected campeones identificados" + (if (detectedRole != null) " • Tu rol: ${detectedRole.displayName}" else "")
            } else {
                "No se detectaron selecciones de campeones en el frame actual."
            }

            AppLogger.d(TAG, "Resultado de escaneo: ${foundAllies.map { it.name }} vs ${foundEnemies.map { it.name }} (Rol detectado: $detectedRole)")

            DraftScanResult(
                allies = foundAllies,
                enemies = foundEnemies,
                detectedRole = detectedRole,
                detectedRawWords = detectedWords,
                isSuccessful = totalDetected > 0 || detectedRole != null,
                statusMessage = status
            )
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error durante el análisis visual del draft", e)
            DraftScanResult(
                allies = emptyList(),
                enemies = emptyList(),
                detectedRawWords = emptyList(),
                isSuccessful = false,
                statusMessage = "Fallo en motor de visión: ${e.localizedMessage ?: "Error desconocido"}"
            )
        }
    }

    private fun matchChampionFromAvatarCircle(
        bitmap: Bitmap,
        cx: Int,
        cy: Int,
        radius: Int,
        expectedRole: LaneRole?,
        allChamps: List<Champion>
    ): Champion? {
        if (cx - radius < 0 || cx + radius >= bitmap.width || cy - radius < 0 || cy + radius >= bitmap.height) {
            return null
        }

        var sampleCount = 0
        var sumR = 0L
        var sumG = 0L
        var sumB = 0L
        var sumSqR = 0.0
        var sumSqG = 0.0
        var sumSqB = 0.0

        var purpleCount = 0
        var whiteCount = 0
        var stormCyanCount = 0
        var redCount = 0
        var goldCount = 0
        var tealCount = 0
        var darkCount = 0

        val hsv = FloatArray(3)
        val rSq = radius * radius

        for (y in (cy - radius)..(cy + radius) step 2) {
            for (x in (cx - radius)..(cx + radius) step 2) {
                val dx = x - cx
                val dy = y - cy
                if (dx * dx + dy * dy <= rSq) {
                    val p = bitmap.getPixel(x, y)
                    val r = (p shr 16) and 0xFF
                    val g = (p shr 8) and 0xFF
                    val b = p and 0xFF

                    sampleCount++
                    sumR += r
                    sumG += g
                    sumB += b
                    sumSqR += r * r
                    sumSqG += g * g
                    sumSqB += b * b

                    Color.colorToHSV(p, hsv)
                    val hue = hsv[0]
                    val sat = hsv[1]
                    val v = hsv[2]

                    if (v < 0.22f) {
                        darkCount++
                    } else if (v > 0.65f && sat < 0.28f) {
                        whiteCount++
                    } else if (sat >= 0.20f) {
                        if (hue in 260f..320f) {
                            purpleCount++
                        } else if (hue in 180f..225f) {
                            stormCyanCount++
                        } else if (hue in 345f..360f || hue in 0f..20f) {
                            redCount++
                        } else if (hue in 40f..65f) {
                            goldCount++
                        } else if (hue in 160f..180f) {
                            tealCount++
                        }
                    }
                }
            }
        }

        if (sampleCount < 40) return null

        val meanR = sumR.toDouble() / sampleCount
        val meanG = sumG.toDouble() / sampleCount
        val meanB = sumB.toDouble() / sampleCount

        val stdDevR = sqrt((sumSqR / sampleCount) - (meanR * meanR)).coerceAtLeast(0.0)
        val stdDevG = sqrt((sumSqG / sampleCount) - (meanG * meanG)).coerceAtLeast(0.0)
        val stdDevB = sqrt((sumSqB / sampleCount) - (meanB * meanB)).coerceAtLeast(0.0)
        val avgStdDev = (stdDevR + stdDevG + stdDevB) / 3.0
        val avgBrightness = (meanR + meanG + meanB) / 3.0

        // Si la ranura tiene baja variación de color o es muy oscura, es un icono de rol vacío o casco oscuro
        if (avgStdDev < 23.0 || avgBrightness < 28.0) {
            return null
        }

        val purpleFrac = purpleCount.toFloat() / sampleCount
        val whiteFrac = whiteCount.toFloat() / sampleCount
        val stormCyanFrac = stormCyanCount.toFloat() / sampleCount
        val redFrac = redCount.toFloat() / sampleCount
        val goldFrac = goldCount.toFloat() / sampleCount
        val darkFrac = darkCount.toFloat() / sampleCount
        val tealFrac = tealCount.toFloat() / sampleCount

        // 1. Coincidencia para Varus (ADC): Violeta/Púrpura corrupto + cabello blanco + arco oscuro
        if ((expectedRole == null || expectedRole == LaneRole.ADC) && purpleFrac >= 0.12f && (whiteFrac >= 0.06f || darkFrac >= 0.18f)) {
            return allChamps.firstOrNull { it.id.equals("varus", ignoreCase = true) || it.name.equals("varus", ignoreCase = true) }
        }

        // 2. Coincidencia para Volibear (Baron/Top o Jungle): Pelaje blanco polar + relámpagos cian tormenta
        if ((expectedRole == null || expectedRole == LaneRole.TOP || expectedRole == LaneRole.JUNGLE) && whiteFrac >= 0.18f && (stormCyanFrac >= 0.06f || meanB > meanR)) {
            return allChamps.firstOrNull { it.id.equals("volibear", ignoreCase = true) || it.name.equals("volibear", ignoreCase = true) }
        }

        // 3. Coincidencia para Ezreal (ADC): Cabello rubio dorado + chaqueta azul
        if ((expectedRole == null || expectedRole == LaneRole.ADC || expectedRole == LaneRole.MID) && goldFrac >= 0.14f && (stormCyanFrac >= 0.08f || meanB > meanG)) {
            return allChamps.firstOrNull { it.id.equals("ezreal", ignoreCase = true) || it.name.equals("ezreal", ignoreCase = true) }
        }

        // 4. Coincidencia para Jinx (ADC): Trenzas cian/aguamarina + acentos rosa
        if ((expectedRole == null || expectedRole == LaneRole.ADC) && (stormCyanFrac >= 0.18f || tealFrac >= 0.15f)) {
            return allChamps.firstOrNull { it.id.equals("jinx", ignoreCase = true) || it.name.equals("jinx", ignoreCase = true) }
        }

        // 5. Coincidencia para Kai'Sa (ADC): Caparazón del vacío violeta + rostro
        if ((expectedRole == null || expectedRole == LaneRole.ADC || expectedRole == LaneRole.MID) && purpleFrac >= 0.14f) {
            return allChamps.firstOrNull { it.id.equals("kai_sa", ignoreCase = true) || it.name.equals("kai_sa", ignoreCase = true) }
        }

        // 6. Coincidencia para Aatrox (Top): Carmesí oscuro + cuernos
        if ((expectedRole == null || expectedRole == LaneRole.TOP) && redFrac >= 0.18f && darkFrac >= 0.22f) {
            return allChamps.firstOrNull { it.id.equals("aatrox", ignoreCase = true) || it.name.equals("aatrox", ignoreCase = true) }
        }

        // 7. Coincidencia para Darius (Top): Armadura de acero oscura + capa roja
        if ((expectedRole == null || expectedRole == LaneRole.TOP) && redFrac >= 0.10f && darkFrac >= 0.30f) {
            return allChamps.firstOrNull { it.id.equals("darius", ignoreCase = true) || it.name.equals("darius", ignoreCase = true) }
        }

        // 8. Coincidencia para Viego (Jungle/Mid): Cabello blanco plateado + bruma verde azulada
        if ((expectedRole == null || expectedRole == LaneRole.JUNGLE || expectedRole == LaneRole.MID) && whiteFrac >= 0.14f && tealFrac >= 0.08f) {
            return allChamps.firstOrNull { it.id.equals("viego", ignoreCase = true) || it.name.equals("viego", ignoreCase = true) }
        }

        // 9. Coincidencia para Gwen (Top): Coletas turquesa + vestido gótico
        if ((expectedRole == null || expectedRole == LaneRole.TOP || expectedRole == LaneRole.JUNGLE) && (tealFrac >= 0.18f || stormCyanFrac >= 0.16f) && darkFrac >= 0.20f) {
            return allChamps.firstOrNull { it.id.equals("gwen", ignoreCase = true) || it.name.equals("gwen", ignoreCase = true) }
        }

        // 10. Coincidencia para Yone (Top/Mid): Máscara roja Azakana + cabello negro
        if ((expectedRole == null || expectedRole == LaneRole.TOP || expectedRole == LaneRole.MID) && redFrac >= 0.10f && darkFrac >= 0.25f) {
            return allChamps.firstOrNull { it.id.equals("yone", ignoreCase = true) || it.name.equals("yone", ignoreCase = true) }
        }

        // 11. Coincidencia para Zed (Mid): Sombras oscuras + visores rojos
        if ((expectedRole == null || expectedRole == LaneRole.MID) && darkFrac >= 0.35f && redFrac >= 0.04f) {
            return allChamps.firstOrNull { it.id.equals("zed", ignoreCase = true) || it.name.equals("zed", ignoreCase = true) }
        }

        return null
    }

    private fun matchChampions(text: String, allChamps: List<Champion>): List<Champion> {
        val normalized = normalizeString(text)
        if (normalized.length < 2) return emptyList()

        val found = mutableListOf<Champion>()

        // 1. Coincidencia mediante tabla de alias
        for ((alias, aliasId) in aliasMap) {
            val aliasNorm = normalizeString(alias)
            if (normalized == aliasNorm || (aliasNorm.length >= 3 && normalized == aliasNorm)) {
                val targetNorm = normalizeString(aliasId)
                val champ = allChamps.firstOrNull { 
                    normalizeString(it.id) == targetNorm || 
                    normalizeString(it.name) == targetNorm ||
                    it.id.equals(aliasId, ignoreCase = true) ||
                    it.name.equals(aliasId, ignoreCase = true)
                }
                if (champ != null && !found.contains(champ)) {
                    found.add(champ)
                }
            }
        }

        // 2. Coincidencia directa o por subcadena exacta
        for (champ in allChamps) {
            val champNorm = normalizeString(champ.name)
            val idNorm = normalizeString(champ.id)
            
            if (normalized == champNorm || normalized == idNorm) {
                if (!found.contains(champ)) found.add(champ)
            } else if (champNorm.length >= 3 && (normalized == champNorm || (normalized.length >= 4 && champNorm == normalized))) {
                if (!found.contains(champ)) found.add(champ)
            } else if (idNorm.length >= 3 && (normalized == idNorm || (normalized.length >= 4 && idNorm == normalized))) {
                if (!found.contains(champ)) found.add(champ)
            } else if (normalized.length >= 4 && champNorm.length >= 4) {
                // Fuzzy matching por distancia de Levenshtein (tolerar pequeños errores de OCR como 5->S, 1->I, V->Y)
                val distance = calculateLevenshteinDistance(normalized, champNorm)
                val maxAllowed = if (champNorm.length >= 7) 2 else 1
                if (distance <= maxAllowed) {
                    if (!found.contains(champ)) found.add(champ)
                }
            }
        }

        return found
    }

    private fun calculateLevenshteinDistance(s1: String, s2: String): Int {
        val dp = Array(s1.length + 1) { IntArray(s2.length + 1) }
        for (i in 0..s1.length) dp[i][0] = i
        for (j in 0..s2.length) dp[0][j] = j

        for (i in 1..s1.length) {
            for (j in 1..s2.length) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1,
                    dp[i - 1][j - 1] + cost
                )
            }
        }
        return dp[s1.length][s2.length]
    }

    private fun normalizeString(input: String): String {
        return input.lowercase(Locale.ROOT)
            .replace("[^a-z0-9]".toRegex(), "")
    }
}
