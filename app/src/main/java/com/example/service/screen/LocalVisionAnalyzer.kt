package com.example.service.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.example.WildRiftApp
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Analizador Visual 100% Local y Autónomo para Wild Rift.
 * Identifica campeones mediante reconocimiento y comparación de huellas cromáticas/estructurales
 * contra los iconos oficiales almacenados en assets locales ('assets/champions/{id}.png').
 * Cero consumo de APIs externas, latencia sub-milisegundo y funcionamiento completamente offline.
 */
object LocalVisionAnalyzer {

    private const val TAG = "LocalVisionAnalyzer"
    private const val FINGERPRINT_SIZE = 16

    data class AvatarFingerprint(
        val champId: String,
        val rgbPixels: IntArray, // Array de tamaño 16x16 = 256
        val avgR: Float,
        val avgG: Float,
        val avgB: Float,
        val hueHistogram: FloatArray // 8 bins de tono cromático
    )

    data class ScannedCropMetrics(
        val roiLabel: String,
        val width: Int,
        val height: Int,
        val avgLum: Float,
        val contrast: Int,
        val minLum: Int,
        val maxLum: Int,
        val isPopulated: Boolean,
        val avgR: Float,
        val avgG: Float,
        val avgB: Float,
        val dominantHueBin: Int,
        val dominantHueName: String,
        val dominantHuePercent: Int,
        val hueHistogram: FloatArray
    )

    data class CandidateMatchComparison(
        val champion: Champion,
        val compositeScore: Float,
        val pixelSimilarity: Float,
        val histSimilarity: Float,
        val avgColorSim: Float,
        val roleBonus: Float
    )

    data class TenthPickDecisionLog(
        val selectedChampion: Champion?,
        val confidence: Float,
        val isConfirmed: Boolean,
        val phaseName: String,
        val scannedMetrics: ScannedCropMetrics,
        val candidatesEvaluatedCount: Int,
        val topCandidates: List<CandidateMatchComparison>,
        val decisionReason: String,
        val formattedSummary: String
    )

    @Volatile
    var lastTenthPickLog: TenthPickDecisionLog? = null

    private val cachedSignatures = ConcurrentHashMap<String, AvatarFingerprint>()
    private var isInitialized = false

    /**
     * Inicializa y cachea en memoria las huellas de los avatares locales.
     * Es ultra ligero (~120 KB de memoria total).
     */
    fun ensureInitialized(context: Context? = null) {
        val ctx = context ?: WildRiftApp.instance ?: return
        try {
            val allChamps = WildRiftRepository.champions.toList()
            if (allChamps.isEmpty()) return
            if (isInitialized && cachedSignatures.size >= allChamps.size) return

            val assetManager = ctx.assets

            for (champ in allChamps) {
                if (cachedSignatures.containsKey(champ.id)) continue
                val assetPath = "champions/${champ.id}.png"
                var inputStream: InputStream? = null
                var bmp: Bitmap? = null
                try {
                    inputStream = assetManager.open(assetPath)
                    bmp = BitmapFactory.decodeStream(inputStream)
                    if (bmp != null) {
                        val fp = extractFingerprint(bmp, champ.id)
                        if (fp != null) {
                            cachedSignatures[champ.id] = fp
                        }
                    }
                } catch (_: Exception) {
                    // Si el activo con id no abre, continuar con los demás
                } finally {
                    try { inputStream?.close() } catch (_: Throwable) {}
                    try { bmp?.recycle() } catch (_: Throwable) {}
                }
            }
            if (cachedSignatures.isNotEmpty()) {
                isInitialized = true
                AppLogger.d(TAG, "Inicializadas ${cachedSignatures.size} huellas locales de campeones en memoria.")
            }
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error inicializando huellas de avatares locales", e)
        }
    }

    /**
     * Extrae la huella cromática de un bitmap normalizándolo a FINGERPRINT_SIZE x FINGERPRINT_SIZE.
     */
    fun extractFingerprint(bitmap: Bitmap, id: String = ""): AvatarFingerprint? {
        if (bitmap.isRecycled || bitmap.width < 8 || bitmap.height < 8) return null

        val scaled = try {
            Bitmap.createScaledBitmap(bitmap, FINGERPRINT_SIZE, FINGERPRINT_SIZE, true)
        } catch (_: Throwable) {
            return null
        }

        val pixels = IntArray(FINGERPRINT_SIZE * FINGERPRINT_SIZE)
        scaled.getPixels(pixels, 0, FINGERPRINT_SIZE, 0, 0, FINGERPRINT_SIZE, FINGERPRINT_SIZE)
        if (scaled != bitmap) {
            try { scaled.recycle() } catch (_: Throwable) {}
        }

        var sumR = 0f
        var sumG = 0f
        var sumB = 0f
        var count = 0

        val hueBins = FloatArray(8)
        var validHueCount = 0

        val center = (FINGERPRINT_SIZE - 1) / 2.0f
        val maxRadius = FINGERPRINT_SIZE * 0.44f // Máscara circular para omitir bordes/marcos

        for (y in 0 until FINGERPRINT_SIZE) {
            for (x in 0 until FINGERPRINT_SIZE) {
                val dx = x - center
                val dy = y - center
                val dist = sqrt(dx * dx + dy * dy)
                if (dist > maxRadius) continue

                val color = pixels[y * FINGERPRINT_SIZE + x]
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)

                sumR += r
                sumG += g
                sumB += b
                count++

                // Calcular tono en espacio HSV
                val hsv = FloatArray(3)
                Color.RGBToHSV(r, g, b, hsv)
                val hue = hsv[0] // 0 a 360
                val sat = hsv[1] // 0 a 1
                val value = hsv[2] // 0 a 1

                if (sat > 0.15f && value > 0.15f) {
                    val bin = ((hue / 360f) * 8f).toInt().coerceIn(0, 7)
                    hueBins[bin] += 1f
                    validHueCount++
                }
            }
        }

        if (count == 0) return null

        // Normalizar histograma de tono
        if (validHueCount > 0) {
            for (i in 0 until 8) {
                hueBins[i] /= validHueCount.toFloat()
            }
        }

        return AvatarFingerprint(
            champId = id,
            rgbPixels = pixels,
            avgR = sumR / count,
            avgG = sumG / count,
            avgB = sumB / count,
            hueHistogram = hueBins
        )
    }

    /**
     * Extrae métricas cuantitativas del recorte (brillo, contraste, RGB promedio e histograma HUE).
     */
    fun extractScannedMetrics(crop: Bitmap, roiLabel: String): ScannedCropMetrics {
        val w = crop.width
        val h = crop.height

        val step = max(1, w / 16)
        var totalLum = 0L
        var minLum = 255
        var maxLum = 0
        var samples = 0

        val cx = w / 2
        val cy = h / 2
        val maxR = min(w, h) * 0.38f

        var sumR = 0f
        var sumG = 0f
        var sumB = 0f
        var colorSamples = 0

        val hueBins = FloatArray(8)
        var validHueCount = 0

        for (y in 0 until h step step) {
            for (x in 0 until w step step) {
                val dx = x - cx
                val dy = y - cy
                if (sqrt((dx * dx + dy * dy).toFloat()) > maxR) continue

                val pixel = crop.getPixel(x, y)
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)
                val lum = (r * 299 + g * 587 + b * 114) / 1000

                totalLum += lum
                if (lum < minLum) minLum = lum
                if (lum > maxLum) maxLum = lum
                samples++

                sumR += r
                sumG += g
                sumB += b
                colorSamples++

                val hsv = FloatArray(3)
                Color.RGBToHSV(r, g, b, hsv)
                val hue = hsv[0]
                val sat = hsv[1]
                val value = hsv[2]

                if (sat > 0.15f && value > 0.15f) {
                    val bin = ((hue / 360f) * 8f).toInt().coerceIn(0, 7)
                    hueBins[bin] += 1f
                    validHueCount++
                }
            }
        }

        val isPopulated = samples >= 10 && (totalLum.toFloat() / samples) >= 12f && (maxLum - minLum) >= 14
        val avgLum = if (samples > 0) totalLum.toFloat() / samples else 0f
        val contrast = if (samples > 0) maxLum - minLum else 0

        if (validHueCount > 0) {
            for (i in 0 until 8) {
                hueBins[i] /= validHueCount.toFloat()
            }
        }

        var dominantBin = 0
        var maxBinVal = 0f
        for (i in 0 until 8) {
            if (hueBins[i] > maxBinVal) {
                maxBinVal = hueBins[i]
                dominantBin = i
            }
        }

        val dominantName = when (dominantBin) {
            0 -> "Rojo / Naranja (0°-45°)"
            1 -> "Amarillo / Oro (45°-90°)"
            2 -> "Verde Claro (90°-135°)"
            3 -> "Verde / Esmeralda (135°-180°)"
            4 -> "Cian / Celeste (180°-225°)"
            5 -> "Azul / Índigo (225°-270°)"
            6 -> "Púrpura / Violeta (270°-315°)"
            else -> "Magenta / Carmesí (315°-360°)"
        }

        return ScannedCropMetrics(
            roiLabel = roiLabel,
            width = w,
            height = h,
            avgLum = avgLum,
            contrast = contrast,
            minLum = minLum,
            maxLum = maxLum,
            isPopulated = isPopulated,
            avgR = if (colorSamples > 0) sumR / colorSamples else 0f,
            avgG = if (colorSamples > 0) sumG / colorSamples else 0f,
            avgB = if (colorSamples > 0) sumB / colorSamples else 0f,
            dominantHueBin = dominantBin,
            dominantHueName = dominantName,
            dominantHuePercent = (maxBinVal * 100).toInt(),
            hueHistogram = hueBins
        )
    }

    /**
     * Verifica si el recorte de avatar contiene un campeón real (contraste y brillo mínimos).
     * Retorna falso si el slot está vacío, negro o esperando selección.
     */
    fun isAvatarPopulated(crop: Bitmap): Boolean {
        if (crop.isRecycled || crop.width < 12 || crop.height < 12) return false
        return extractScannedMetrics(crop, "").isPopulated
    }

    /**
     * Comparación detallada de un recorte contra el catálogo local de campeones.
     * Retorna un log exhaustivo de métricas escaneadas, métricas comparadas y justificación de decisión.
     */
    fun matchAvatarDetailed(
        crop: Bitmap,
        roiLabel: String,
        candidates: List<Champion>,
        expectedRole: LaneRole? = null,
        excludedChampionIds: Set<String> = emptySet(),
        context: Context? = null,
        isConfirmedPhase: Boolean = false
    ): TenthPickDecisionLog? {
        val populatedMetrics = extractScannedMetrics(crop, roiLabel)
        val phaseName = if (isConfirmedPhase) "CONFIRMACIÓN DEFINITIVA (BARRA SUPERIOR)" else "PRESELECCIÓN PROVISIONAL (HOVER INFERIOR)"

        if (!populatedMetrics.isPopulated) {
            val unpopReason = "Recorte en $roiLabel no contiene avatar activo (Brillo=${populatedMetrics.avgLum.toInt()}, Contraste=${populatedMetrics.contrast}, umbrales brillo>=12, contraste>=14). Slot vacío o en negro."
            val unpopSummary = buildString {
                appendLine("==================== [SELECCIÓN 10 - DIAGNÓSTICO VISUAL] ====================")
                appendLine("Fase: $phaseName")
                appendLine("Ubicación ROI: $roiLabel")
                appendLine("")
                appendLine("1. CARACTERÍSTICAS ESCANEADAS:")
                appendLine("- Dimensiones: ${populatedMetrics.width}x${populatedMetrics.height} px")
                appendLine("- Brillo y Contraste: Promedio=${populatedMetrics.avgLum.toInt()} | Contraste=${populatedMetrics.contrast} (Avatar activo: NO / Slot vacío)")
                appendLine("- Color Promedio RGB: R=${populatedMetrics.avgR.toInt()}, G=${populatedMetrics.avgG.toInt()}, B=${populatedMetrics.avgB.toInt()}")
                appendLine("")
                appendLine("2. RESULTADO:")
                appendLine("- $unpopReason")
                appendLine("=============================================================================")
            }
            val unpopLog = TenthPickDecisionLog(
                selectedChampion = null,
                confidence = 0f,
                isConfirmed = false,
                phaseName = phaseName,
                scannedMetrics = populatedMetrics,
                candidatesEvaluatedCount = 0,
                topCandidates = emptyList(),
                decisionReason = unpopReason,
                formattedSummary = unpopSummary
            )
            lastTenthPickLog = unpopLog
            AppLogger.i(TAG, unpopSummary)
            return null
        }

        ensureInitialized(context)
        if (cachedSignatures.isEmpty()) return null

        val targetFp = extractFingerprint(crop, "target") ?: return null

        val center = (FINGERPRINT_SIZE - 1) / 2.0f
        val maxRadius = FINGERPRINT_SIZE * 0.44f

        val targetLumAvg = (targetFp.avgR + targetFp.avgG + targetFp.avgB) / 3f

        val candidateComparisons = mutableListOf<CandidateMatchComparison>()

        for (champ in candidates) {
            if (excludedChampionIds.contains(champ.id)) continue
            val sig = cachedSignatures[champ.id] ?: continue

            val sigLumAvg = (sig.avgR + sig.avgG + sig.avgB) / 3f
            val lumOffset = targetLumAvg - sigLumAvg

            // 1. Similitud RGB pixel a pixel con compensación de iluminación media
            var pixelDiffSum = 0f
            var pixelCount = 0

            for (y in 0 until FINGERPRINT_SIZE) {
                for (x in 0 until FINGERPRINT_SIZE) {
                    val dx = x - center
                    val dy = y - center
                    if (sqrt(dx * dx + dy * dy) > maxRadius) continue

                    val idx = y * FINGERPRINT_SIZE + x
                    val p1 = targetFp.rgbPixels[idx]
                    val p2 = sig.rgbPixels[idx]

                    val expR = (Color.red(p2) + lumOffset).coerceIn(0f, 255f)
                    val expG = (Color.green(p2) + lumOffset).coerceIn(0f, 255f)
                    val expB = (Color.blue(p2) + lumOffset).coerceIn(0f, 255f)

                    val dr = abs(Color.red(p1) - expR)
                    val dg = abs(Color.green(p1) - expG)
                    val db = abs(Color.blue(p1) - expB)

                    pixelDiffSum += (dr + dg + db) / (3f * 255f)
                    pixelCount++
                }
            }

            if (pixelCount == 0) continue
            val avgPixelDiff = pixelDiffSum / pixelCount.toFloat()
            val pixelSimilarity = (1.0f - avgPixelDiff).coerceIn(0f, 1f)

            // 2. Similitud de Histograma de Tono (Intersección de histogramas HSV)
            var histIntersection = 0f
            for (i in 0 until 8) {
                histIntersection += min(targetFp.hueHistogram[i], sig.hueHistogram[i])
            }
            val histSimilarity = histIntersection.coerceIn(0f, 1f)

            // 3. Similitud de color promedio
            val avgColorDiff = (abs(targetFp.avgR - sig.avgR) + abs(targetFp.avgG - sig.avgG) + abs(targetFp.avgB - sig.avgB)) / (3f * 255f)
            val avgColorSim = (1.0f - avgColorDiff).coerceIn(0f, 1f)

            // 4. Puntuación visual combinada robusta
            var compositeScore = (pixelSimilarity * 0.45f) + (histSimilarity * 0.35f) + (avgColorSim * 0.20f)
            var roleBonus = 0f

            // Ponderación contextual suave según el rol esperado para el slot
            if (expectedRole != null) {
                if (champ.primaryRole == expectedRole) {
                    roleBonus = 0.03f
                } else if (champ.secondaryRoles.contains(expectedRole)) {
                    roleBonus = 0.015f
                }
                compositeScore += roleBonus
            }

            candidateComparisons.add(
                CandidateMatchComparison(
                    champion = champ,
                    compositeScore = compositeScore,
                    pixelSimilarity = pixelSimilarity,
                    histSimilarity = histSimilarity,
                    avgColorSim = avgColorSim,
                    roleBonus = roleBonus
                )
            )
        }

        candidateComparisons.sortByDescending { it.compositeScore }

        val best = candidateComparisons.firstOrNull()
        val runnerUp = candidateComparisons.getOrNull(1)
        val topCandidates = candidateComparisons.take(5)

        // Si ningún candidato supera el umbral de 0.38f, documentar detalladamente el diagnóstico y registrar
        if (best == null || best.compositeScore < 0.38f) {
            val delta = if (best != null && runnerUp != null) ((best.compositeScore - runnerUp.compositeScore) * 100).toInt() else 0
            val failReason = if (best != null) {
                "Sin coincidencia concluyente: El candidato más cercano fue ${best.champion.name} con ${(best.compositeScore * 100).toInt()}% (Píxeles: ${(best.pixelSimilarity * 100).toInt()}%, Hue: ${(best.histSimilarity * 100).toInt()}%, RGB: ${(best.avgColorSim * 100).toInt()}%), por debajo del umbral mínimo (38%). Requiere mayor nitidez o selección más clara."
            } else {
                "Sin candidatos válidos disponibles para comparar (todos los campeones evaluados estaban excluidos por selecciones 1-9)."
            }

            val failSummary = buildString {
                appendLine("==================== [SELECCIÓN 10 - DIAGNÓSTICO VISUAL] ====================")
                appendLine("Fase: $phaseName")
                appendLine("Ubicación ROI: $roiLabel")
                appendLine("")
                appendLine("1. CARACTERÍSTICAS ESCANEADAS:")
                appendLine("- Dimensiones: ${populatedMetrics.width}x${populatedMetrics.height} px")
                appendLine("- Brillo y Contraste: Promedio=${populatedMetrics.avgLum.toInt()} | Contraste=${populatedMetrics.contrast} (Avatar activo: Sí)")
                appendLine("- Color Promedio RGB: R=${populatedMetrics.avgR.toInt()}, G=${populatedMetrics.avgG.toInt()}, B=${populatedMetrics.avgB.toInt()}")
                appendLine("- Tono Dominante: Bin ${populatedMetrics.dominantHueBin} [${populatedMetrics.dominantHueName}] (${populatedMetrics.dominantHuePercent}% píxeles con color)")
                appendLine("- Histograma HUE: [${populatedMetrics.hueHistogram.joinToString(", ") { "${(it * 100).toInt()}%" }}]")
                appendLine("")
                appendLine("2. CARACTERÍSTICAS COMPARADAS:")
                appendLine("- Candidatos evaluados: ${candidateComparisons.size} campeones (excluyendo picks 1-9)")
                appendLine("- Rol esperado: ${expectedRole?.name ?: "Cualquiera"}")
                appendLine("- Criterios: Píxeles 45% + Histograma HUE 35% + Color RGB 20% + Bonus Rol")
                appendLine("- TOP CANDIDATOS MÁS CERCANOS:")
                topCandidates.forEachIndexed { idx, c ->
                    appendLine("  #${idx + 1} ${c.champion.name}: Score=${(c.compositeScore * 100).toInt()}% | Píxel=${(c.pixelSimilarity * 100).toInt()}% | Hue=${(c.histSimilarity * 100).toInt()}% | RGB=${(c.avgColorSim * 100).toInt()}%${if (c.roleBonus > 0) " | RolBonus=+${(c.roleBonus * 100).toInt()}%" else ""}")
                }
                appendLine("")
                appendLine("3. POR QUÉ SE RECHAZÓ / NO SE ASIGNÓ:")
                appendLine("- $failReason")
                appendLine("- El escaneo automático CONTINÚA ACTIVO en busca de mayor nitidez o selección definitiva.")
                appendLine("=============================================================================")
            }

            val failLog = TenthPickDecisionLog(
                selectedChampion = best?.champion,
                confidence = best?.compositeScore ?: 0f,
                isConfirmed = false,
                phaseName = phaseName,
                scannedMetrics = populatedMetrics,
                candidatesEvaluatedCount = candidateComparisons.size,
                topCandidates = topCandidates,
                decisionReason = failReason,
                formattedSummary = failSummary
            )
            lastTenthPickLog = failLog
            AppLogger.i(TAG, failSummary)
            return null
        }

        val delta = ((best.compositeScore - (runnerUp?.compositeScore ?: 0f)) * 100).toInt()

        val decisionReason = "Seleccionado ${best.champion.name} con ${(best.compositeScore * 100).toInt()}% de similitud global (Píxeles: ${(best.pixelSimilarity * 100).toInt()}%, Tono HUE: ${(best.histSimilarity * 100).toInt()}%, Color RGB: ${(best.avgColorSim * 100).toInt()}%${if (best.roleBonus > 0) ", Bonus Rol: +${(best.roleBonus * 100).toInt()}%" else ""}). Supera a ${runnerUp?.champion?.name ?: "N/A"} (${((runnerUp?.compositeScore ?: 0f) * 100).toInt()}%) por delta de +$delta%. Compatible con rol ${expectedRole?.name ?: "disponible"} y no colisiona con selecciones 1-9."

        val summary = buildString {
            appendLine("==================== [SELECCIÓN 10 - ANÁLISIS DE VISIÓN] ====================")
            appendLine("Fase: $phaseName")
            appendLine("Ubicación ROI: $roiLabel")
            appendLine("")
            appendLine("1. CARACTERÍSTICAS ESCANEADAS:")
            appendLine("- Dimensiones: ${populatedMetrics.width}x${populatedMetrics.height} px")
            appendLine("- Brillo y Contraste: Promedio=${populatedMetrics.avgLum.toInt()} | Contraste=${populatedMetrics.contrast} (Avatar activo: Sí)")
            appendLine("- Color Promedio RGB: R=${populatedMetrics.avgR.toInt()}, G=${populatedMetrics.avgG.toInt()}, B=${populatedMetrics.avgB.toInt()}")
            appendLine("- Tono Dominante: Bin ${populatedMetrics.dominantHueBin} [${populatedMetrics.dominantHueName}] (${populatedMetrics.dominantHuePercent}% píxeles con color)")
            appendLine("- Histograma HUE (8 Bins): [${populatedMetrics.hueHistogram.joinToString(", ") { "${(it * 100).toInt()}%" }}]")
            appendLine("")
            appendLine("2. CARACTERÍSTICAS COMPARADAS:")
            appendLine("- Candidatos evaluados: ${candidateComparisons.size} campeones (excluyendo picks 1-9)")
            appendLine("- Rol esperado: ${expectedRole?.name ?: "Cualquiera"}")
            appendLine("- Criterios: Píxeles 45% + Histograma HUE 35% + Color RGB 20% + Bonus Rol")
            appendLine("- TOP CANDIDATOS:")
            topCandidates.forEachIndexed { idx, c ->
                appendLine("  #${idx + 1} ${c.champion.name}: Score=${(c.compositeScore * 100).toInt()}% | Píxel=${(c.pixelSimilarity * 100).toInt()}% | Hue=${(c.histSimilarity * 100).toInt()}% | RGB=${(c.avgColorSim * 100).toInt()}%${if (c.roleBonus > 0) " | RolBonus=+${(c.roleBonus * 100).toInt()}%" else ""}")
            }
            appendLine("")
            appendLine("3. POR QUÉ SE DECIDIÓ ESTA SELECCIÓN:")
            appendLine("- Decisión: ${best.champion.name}")
            appendLine("- Motivo: $decisionReason")
            appendLine("- Estado Escaneo: ${if (isConfirmedPhase) "CONFIRMADO AL 100%. Selección sellada." else "PRESELECCIÓN PROVISIONAL. El escaneo automático CONTINÚA ACTIVO esperando confirmación final."}")
            appendLine("=============================================================================")
        }

        val decisionLog = TenthPickDecisionLog(
            selectedChampion = best.champion,
            confidence = best.compositeScore,
            isConfirmed = isConfirmedPhase,
            phaseName = phaseName,
            scannedMetrics = populatedMetrics,
            candidatesEvaluatedCount = candidateComparisons.size,
            topCandidates = topCandidates,
            decisionReason = decisionReason,
            formattedSummary = summary
        )

        lastTenthPickLog = decisionLog
        AppLogger.i(TAG, summary)

        return decisionLog
    }

    /**
     * Compara un recorte de pantalla contra el catálogo local de campeones.
     * Devuelve el mejor campeón coincidente y la puntuación de confianza (0.0 a 1.0).
     */
    fun matchAvatar(
        crop: Bitmap,
        candidates: List<Champion>,
        expectedRole: LaneRole? = null,
        excludedChampionIds: Set<String> = emptySet(),
        context: Context? = null
    ): Pair<Champion, Float>? {
        val detailed = matchAvatarDetailed(
            crop = crop,
            roiLabel = "Avatar Slot",
            candidates = candidates,
            expectedRole = expectedRole,
            excludedChampionIds = excludedChampionIds,
            context = context
        ) ?: return null
        val champ = detailed.selectedChampion ?: return null
        return Pair(champ, detailed.confidence)
    }

    /**
     * Escaneo del 10º Pick en la PARTE INFERIOR con métricas y justificación completa:
     * - Si es Primera Selección (Aliados): el 10º pick es Rival -> escanea la parte INFERIOR DERECHA (Slot 4 del Rival).
     * - Si NO es Primera Selección (Rivales): el 10º pick es Aliado -> escanea la parte INFERIOR IZQUIERDA (Slot 4 del Aliado).
     */
    suspend fun identify10thPickInferiorDetailed(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): TenthPickDecisionLog? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        val width = bitmap.width
        val height = bitmap.height

        val isAlly = !isFirstPick
        val slotAvatarDiam = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        val slotCenterX = if (isAlly) {
            (width * calib.allyAvatarCenterX).toInt()
        } else {
            (width * calib.enemyAvatarCenterX).toInt()
        }
        val slotCenterY = if (isAlly) {
            (height * calib.allySlotYRatios[4]).toInt()
        } else {
            (height * calib.enemySlotYRatios[4]).toInt()
        }

        val sideDesc = if (isAlly) "Inferior Izquierda (Aliado 5 - 10º Pick)" else "Inferior Derecha (Rival 5 - 10º Pick)"

        val standardCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotAvatarDiam)
        val innerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 0.88f).toInt())
        val outerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 1.08f).toInt())

        val candidateDecisions = mutableListOf<TenthPickDecisionLog>()
        try {
            if (standardCrop != null) {
                matchAvatarDetailed(standardCrop, "$sideDesc [Estándar]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = false)?.let { candidateDecisions.add(it) }
            }
            if (innerCrop != null) {
                matchAvatarDetailed(innerCrop, "$sideDesc [Interior 88%]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = false)?.let { candidateDecisions.add(it) }
            }
            if (outerCrop != null) {
                matchAvatarDetailed(outerCrop, "$sideDesc [Exterior 108%]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = false)?.let { candidateDecisions.add(it) }
            }
        } finally {
            try { standardCrop?.recycle() } catch (_: Throwable) {}
            try { innerCrop?.recycle() } catch (_: Throwable) {}
            try { outerCrop?.recycle() } catch (_: Throwable) {}
        }

        return@withContext candidateDecisions.maxByOrNull { it.confidence }
    }

    suspend fun identify10thPickInferior(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): Pair<Champion, Float>? {
        val detailed = identify10thPickInferiorDetailed(bitmap, isFirstPick, calib, allChamps, confirmedIds, expectedRole, context) ?: return null
        val champ = detailed.selectedChampion ?: return null
        return Pair(champ, detailed.confidence)
    }

    /**
     * Confirmación del 10º Pick en la PARTE SUPERIOR (100% Certera) con registro detallado:
     * Tras cerrarse la cuadrícula inferior, el campeón queda fijado en la barra superior:
     * - Si fue Primera Selección: el 10º pick fue el 5º Rival -> confirma en la PARTE SUPERIOR DERECHA.
     * - Si NO fue Primera Selección: el 10º pick fue el 5º Aliado -> confirma en la PARTE SUPERIOR IZQUIERDA.
     */
    suspend fun identify10thPickSuperiorDetailed(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): TenthPickDecisionLog? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        val width = bitmap.width
        val height = bitmap.height

        val isAlly = !isFirstPick
        val topDiam = (height * calib.topAvatarDiameterRatio).toInt().coerceAtLeast(26)
        val topCenterY = (height * calib.topAvatarYRatio).toInt()

        val candidateXs = if (isAlly) {
            listOf((width * calib.topAlly5XRatio).toInt())
        } else {
            listOf(
                (width * calib.topEnemy5XRatio).toInt(),
                (width * (calib.topEnemyXRatios.firstOrNull() ?: 0.856f)).toInt()
            )
        }

        val sideDesc = if (isAlly) "Superior Izquierda (Aliado 5 - 10º Pick)" else "Superior Derecha (Rival 5 - 10º Pick)"
        val candidateDecisions = mutableListOf<TenthPickDecisionLog>()

        for (topCenterX in candidateXs) {
            val standardCrop = safeCrop(bitmap, topCenterX, topCenterY, topDiam)
            val innerCrop = safeCrop(bitmap, topCenterX, topCenterY, (topDiam * 0.88f).toInt())
            val outerCrop = safeCrop(bitmap, topCenterX, topCenterY, (topDiam * 1.08f).toInt())

            try {
                if (standardCrop != null) {
                    matchAvatarDetailed(standardCrop, "$sideDesc [Estándar]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = true)?.let { candidateDecisions.add(it) }
                }
                if (innerCrop != null) {
                    matchAvatarDetailed(innerCrop, "$sideDesc [Interior 88%]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = true)?.let { candidateDecisions.add(it) }
                }
                if (outerCrop != null) {
                    matchAvatarDetailed(outerCrop, "$sideDesc [Exterior 108%]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = true)?.let { candidateDecisions.add(it) }
                }
            } finally {
                try { standardCrop?.recycle() } catch (_: Throwable) {}
                try { innerCrop?.recycle() } catch (_: Throwable) {}
                try { outerCrop?.recycle() } catch (_: Throwable) {}
            }
        }

        return@withContext candidateDecisions.maxByOrNull { it.confidence }
    }

    suspend fun identify10thPickSuperior(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): Pair<Champion, Float>? {
        val detailed = identify10thPickSuperiorDetailed(bitmap, isFirstPick, calib, allChamps, confirmedIds, expectedRole, context) ?: return null
        val champ = detailed.selectedChampion ?: return null
        return Pair(champ, 1.0f)
    }

    /**
     * Detecta si los slots/cuadrícula de avatares de selección del centro/inferior
     * han desaparecido, lo cual indica que el 10º pick ya fijó y la selección concluyó.
     */
    fun areAvatarSlotsDismissed(bitmap: Bitmap, isPrepPhaseDetected: Boolean): Boolean {
        if (isPrepPhaseDetected) return true
        if (bitmap.isRecycled) return false

        val width = bitmap.width
        val height = bitmap.height

        val checkAreaLeft = (width * 0.35f).toInt()
        val checkAreaTop = (height * 0.72f).toInt()
        val checkAreaWidth = (width * 0.30f).toInt()
        val checkAreaHeight = (height * 0.15f).toInt()

        if (checkAreaLeft + checkAreaWidth > width || checkAreaTop + checkAreaHeight > height) return false

        return try {
            val sampleColors = mutableListOf<Int>()
            val step = 8

            for (y in checkAreaTop until (checkAreaTop + checkAreaHeight) step step) {
                for (x in checkAreaLeft until (checkAreaLeft + checkAreaWidth) step step) {
                    sampleColors.add(bitmap.getPixel(x, y))
                }
            }

            if (sampleColors.isEmpty()) return false

            val brightnesses = sampleColors.map {
                val r = (it shr 16) and 0xFF
                val g = (it shr 8) and 0xFF
                val b = it and 0xFF
                (r * 0.299 + g * 0.587 + b * 0.114).toInt()
            }
            val mean = brightnesses.average()
            val stdDev = Math.sqrt(brightnesses.map { Math.pow(it - mean, 2.0) }.average())

            // Con la cuadrícula de avatares activa, la desviación estándar es alta (> 38).
            // Al cerrarse la cuadrícula, la zona central queda plana/oscura (desviación baja).
            val dismissed = stdDev < 28.0
            AppLogger.d(TAG, "areAvatarSlotsDismissed: stdDev=${String.format(java.util.Locale.US, "%.1f", stdDev)} (umbral < 28.0) -> cuadrícula cerrada=$dismissed")
            dismissed
        } catch (_: Throwable) {
            false
        }
    }

    /**
     * Identificación visual del 10º Pick comparando el slot vertical correspondiente
     * contra el catálogo local de avatares en assets/champions.
     * Utiliza muestreo multi-escala (estándar, enfoque interior al 88% para eliminar halos de selección
     * o temporizadores circulares, y ampliado al 108%) de forma 100% offline.
     */
    suspend fun identify10thPickLocal(
        bitmap: Bitmap,
        isAlly: Boolean,
        targetSlotIndex: Int = 4,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): Pair<Champion, Float>? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        val width = bitmap.width
        val height = bitmap.height

        val slotIdx = targetSlotIndex.coerceIn(0, 4)
        val slotAvatarDiam = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        val slotCenterX = if (!isAlly) {
            (width * calib.enemyAvatarCenterX).toInt()
        } else {
            (width * calib.allyAvatarCenterX).toInt()
        }
        val slotCenterY = if (!isAlly) {
            (height * calib.enemySlotYRatios[slotIdx]).toInt()
        } else {
            (height * calib.allySlotYRatios[slotIdx]).toInt()
        }

        // Multi-escala en el slot vertical de la selección para máxima robustez:
        // 1. Recorte estándar según calibración
        // 2. Recorte interior al 88% (ignora anillos de selección, temporizadores circulares brillantes y bordes)
        // 3. Recorte ampliado al 108% (para capturar retratos ligeramente desfasados)
        val standardCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotAvatarDiam)
        val innerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 0.88f).toInt())
        val outerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 1.08f).toInt())

        val candidateMatches = mutableListOf<Pair<Champion, Float>>()

        try {
            if (standardCrop != null) {
                matchAvatar(standardCrop, allChamps, expectedRole, confirmedIds, context)?.let {
                    candidateMatches.add(it)
                }
            }
            if (innerCrop != null) {
                matchAvatar(innerCrop, allChamps, expectedRole, confirmedIds, context)?.let {
                    candidateMatches.add(it)
                }
            }
            if (outerCrop != null) {
                matchAvatar(outerCrop, allChamps, expectedRole, confirmedIds, context)?.let {
                    candidateMatches.add(it)
                }
            }
        } finally {
            try { standardCrop?.recycle() } catch (_: Throwable) {}
            try { innerCrop?.recycle() } catch (_: Throwable) {}
            try { outerCrop?.recycle() } catch (_: Throwable) {}
        }

        if (candidateMatches.isEmpty()) {
            return@withContext null
        }

        val bestCandidate = candidateMatches.maxByOrNull { it.second }
        if (bestCandidate != null) {
            AppLogger.d(TAG, "10º Pick detectado con precisión visual local en slot $slotIdx: ${bestCandidate.first.name} (Confianza: ${(bestCandidate.second * 100).toInt()}%)")
        }
        return@withContext bestCandidate
    }

    private fun safeCrop(src: Bitmap, cx: Int, cy: Int, diameter: Int): Bitmap? {
        if (src.isRecycled || diameter <= 10) return null
        val radius = diameter / 2
        val left = (cx - radius).coerceIn(0, src.width - diameter)
        val top = (cy - radius).coerceIn(0, src.height - diameter)
        if (left + diameter > src.width || top + diameter > src.height) return null

        return try {
            Bitmap.createBitmap(src, left, top, diameter, diameter)
        } catch (_: Throwable) {
            null
        }
    }
}
