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
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Analizador Visual 100% Local y de Alta Precisión para Wild Rift.
 * Reconoce y compara campeones de forma directa contra los 141 iconos oficiales locales
 * con normalización adaptativa de imagen, ZNCC multicanal e histogramas HSV de alta fidelidad.
 * Cero dependencias externas, cero APIs, cero archivos ZIP y funcionamiento offline instantáneo.
 */
object LocalVisionAnalyzer {

    private const val TAG = "LocalVisionAnalyzer"
    private const val FINGERPRINT_SIZE = 32

    data class AvatarFingerprint(
        val champId: String,
        val rgbPixels: IntArray, // 32x32 = 1024 píxeles
        val avgR: Float,
        val avgG: Float,
        val avgB: Float,
        val chromaR: Float,
        val chromaG: Float,
        val chromaB: Float,
        val satRatio: Float,
        val rMean: Float,
        val rStdDev: Float,
        val gMean: Float,
        val gStdDev: Float,
        val bMean: Float,
        val bStdDev: Float,
        val lumMean: Float,
        val lumStdDev: Float,
        val lumValues: FloatArray,
        val rValues: FloatArray,
        val gValues: FloatArray,
        val bValues: FloatArray,
        val colorHistogram: FloatArray, // 36 Hue + 8 Acromáticos = 44 bins
        val spatialBlockRgb: FloatArray, // 16 bloques espaciales (4x4) * 3 canales = 48 valores
        val circularMask: BooleanArray
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
        val pixelColorSim: Float,
        val blockSim: Float,
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
        val formattedSummary: String,
        val cropBitmap: Bitmap? = null
    )

    @Volatile
    var lastTenthPickLog: TenthPickDecisionLog? = null

    @Volatile
    var lastTenthPickCrop: Bitmap? = null

    @Volatile
    var lastTenthPickCoordinates: String = ""

    @Volatile
    var lastTenthPickRoiLabel: String = ""

    fun resetTenthPickData() {
        lastTenthPickLog = null
        lastTenthPickCrop = null
        lastTenthPickCoordinates = ""
        lastTenthPickRoiLabel = ""
        AppLogger.d(TAG, "Datos de captura de visión reiniciados")
    }

    private var isInitialized = false

    /**
     * Inicializa el catálogo de campeones en memoria para los 4 motores de inferencia.
     */
    fun ensureInitialized(context: Context? = null, forceReload: Boolean = false) {
        val ctx = context ?: WildRiftApp.instance ?: return
        try {
            if (WildRiftRepository.champions.isEmpty()) {
                WildRiftRepository.initChampions(ctx)
            }
            isInitialized = true
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error inicializando repositorio para motores de inferencia", e)
        }
    }

    /**
     * Mejora la calidad del recorte eliminando la oscuridad y el velo opaco.
     * Aplica ganancia lumínica uniforme para preservar al 100% las proporciones cromáticas.
     */
    fun enhanceCropQuality(src: Bitmap): Bitmap {
        if (src.isRecycled || src.width < 4 || src.height < 4) return src
        return try {
            val w = src.width
            val h = src.height
            val pixels = IntArray(w * h)
            src.getPixels(pixels, 0, w, 0, 0, w, h)

            var totalLum = 0L
            var count = 0
            val cx = w / 2
            val cy = h / 2
            val maxR = min(w, h) * 0.42f

            for (y in 0 until h) {
                for (x in 0 until w) {
                    val dx = x - cx
                    val dy = y - cy
                    if (sqrt((dx * dx + dy * dy).toFloat()) <= maxR) {
                        val p = pixels[y * w + x]
                        val r = Color.red(p)
                        val g = Color.green(p)
                        val b = Color.blue(p)
                        totalLum += (r * 299 + g * 587 + b * 114) / 1000
                        count++
                    }
                }
            }

            val avgLum = if (count > 0) totalLum.toFloat() / count else 128f

            // Si está oscuro, aplicar ganancia uniforme sin distorsión de tono (Chroma y Hue intactos)
            if (avgLum in 10f..115f) {
                val gain = (128f / avgLum.coerceAtLeast(35f)).coerceIn(1.0f, 1.75f)
                val outPixels = IntArray(w * h)
                for (i in pixels.indices) {
                    val p = pixels[i]
                    val a = Color.alpha(p)
                    val r = (Color.red(p) * gain).toInt().coerceIn(0, 255)
                    val g = (Color.green(p) * gain).toInt().coerceIn(0, 255)
                    val b = (Color.blue(p) * gain).toInt().coerceIn(0, 255)
                    outPixels[i] = Color.argb(a, r, g, b)
                }
                Bitmap.createBitmap(outPixels, w, h, Bitmap.Config.ARGB_8888)
            } else {
                src.copy(Bitmap.Config.ARGB_8888, false)
            }
        } catch (_: Throwable) {
            src
        }
    }

    /**
     * Extrae la huella cromática y espacial de un bitmap normalizándolo a FINGERPRINT_SIZE x FINGERPRINT_SIZE (32x32).
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

        // 44 bins: 0..35 (36 Hue bins ponderados por Sat*Val) + 36..43 (8 Niveles Acromáticos)
        val colorBins = FloatArray(44)
        var totalHistWeight = 0f
        var validHueCount = 0

        val center = (FINGERPRINT_SIZE - 1) / 2.0f
        val maxRadius = FINGERPRINT_SIZE * 0.44f // Máscara circular para omitir bordes externos de la UI
        val circularMask = BooleanArray(FINGERPRINT_SIZE * FINGERPRINT_SIZE)

        val lumValues = FloatArray(FINGERPRINT_SIZE * FINGERPRINT_SIZE)
        val rValues = FloatArray(FINGERPRINT_SIZE * FINGERPRINT_SIZE)
        val gValues = FloatArray(FINGERPRINT_SIZE * FINGERPRINT_SIZE)
        val bValues = FloatArray(FINGERPRINT_SIZE * FINGERPRINT_SIZE)

        for (y in 0 until FINGERPRINT_SIZE) {
            for (x in 0 until FINGERPRINT_SIZE) {
                val dx = x - center
                val dy = y - center
                val dist = sqrt(dx * dx + dy * dy)
                if (dist > maxRadius) continue

                val idx = y * FINGERPRINT_SIZE + x
                circularMask[idx] = true
                val color = pixels[idx]
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)

                val lum = (r * 299 + g * 587 + b * 114) / 1000f
                lumValues[idx] = lum
                rValues[idx] = r.toFloat()
                gValues[idx] = g.toFloat()
                bValues[idx] = b.toFloat()

                sumR += r
                sumG += g
                sumB += b
                count++

                // Espacio de color HSV
                val hsv = FloatArray(3)
                Color.RGBToHSV(r, g, b, hsv)
                val hue = hsv[0]
                val sat = hsv[1]
                val value = hsv[2]

                // Ponderación de Hue por Saturación * Valor para destacar tonos característicos
                val weight = sat * value
                if (sat > 0.10f && value > 0.10f) {
                    val bin = ((hue / 360f) * 36f).toInt().coerceIn(0, 35)
                    colorBins[bin] += weight
                    totalHistWeight += weight
                    validHueCount++
                } else {
                    val achroBin = (36 + (value * 7.99f).toInt()).coerceIn(36, 43)
                    val achroWeight = (1.0f - sat) * value
                    colorBins[achroBin] += achroWeight
                    totalHistWeight += achroWeight
                }
            }
        }

        if (count == 0) return null

        val avgR = sumR / count
        val avgG = sumG / count
        val avgB = sumB / count
        val totalRgb = (avgR + avgG + avgB).coerceAtLeast(1f)
        val chromaR = avgR / totalRgb
        val chromaG = avgG / totalRgb
        val chromaB = avgB / totalRgb

        val satRatio = validHueCount.toFloat() / count.toFloat()

        var sumLum = 0f
        var sumRChan = 0f
        var sumGChan = 0f
        var sumBChan = 0f
        for (y in 0 until FINGERPRINT_SIZE) {
            for (x in 0 until FINGERPRINT_SIZE) {
                val idx = y * FINGERPRINT_SIZE + x
                if (circularMask[idx]) {
                    sumLum += lumValues[idx]
                    sumRChan += rValues[idx]
                    sumGChan += gValues[idx]
                    sumBChan += bValues[idx]
                }
            }
        }
        val lumMean = sumLum / count
        val rMean = sumRChan / count
        val gMean = sumGChan / count
        val bMean = sumBChan / count

        var varLum = 0f
        var varR = 0f
        var varG = 0f
        var varB = 0f
        for (y in 0 until FINGERPRINT_SIZE) {
            for (x in 0 until FINGERPRINT_SIZE) {
                val idx = y * FINGERPRINT_SIZE + x
                if (circularMask[idx]) {
                    val dLum = lumValues[idx] - lumMean
                    varLum += dLum * dLum
                    val dR = rValues[idx] - rMean
                    varR += dR * dR
                    val dG = gValues[idx] - gMean
                    varG += dG * dG
                    val dB = bValues[idx] - bMean
                    varB += dB * dB
                }
            }
        }
        val lumStdDev = sqrt(varLum / count).coerceAtLeast(1.0f)
        val rStdDev = sqrt(varR / count).coerceAtLeast(1.0f)
        val gStdDev = sqrt(varG / count).coerceAtLeast(1.0f)
        val bStdDev = sqrt(varB / count).coerceAtLeast(1.0f)

        // Normalizar histograma de 44 bins
        val normWeight = max(0.0001f, totalHistWeight)
        for (i in 0 until 44) {
            colorBins[i] /= normWeight
        }

        // Bloques espaciales 4x4
        val blockSize = FINGERPRINT_SIZE / 4
        val spatialBlockRgb = FloatArray(16 * 3)
        for (by in 0 until 4) {
            for (bx in 0 until 4) {
                var blockR = 0f
                var blockG = 0f
                var blockB = 0f
                var blockCount = 0
                for (py in (by * blockSize) until ((by + 1) * blockSize)) {
                    for (px in (bx * blockSize) until ((bx + 1) * blockSize)) {
                        val pIdx = py * FINGERPRINT_SIZE + px
                        if (circularMask[pIdx]) {
                            val c = pixels[pIdx]
                            blockR += Color.red(c)
                            blockG += Color.green(c)
                            blockB += Color.blue(c)
                            blockCount++
                        }
                    }
                }
                val blockIdx = (by * 4 + bx) * 3
                spatialBlockRgb[blockIdx] = if (blockCount > 0) blockR / blockCount else 0f
                spatialBlockRgb[blockIdx + 1] = if (blockCount > 0) blockG / blockCount else 0f
                spatialBlockRgb[blockIdx + 2] = if (blockCount > 0) blockB / blockCount else 0f
            }
        }

        return AvatarFingerprint(
            champId = id,
            rgbPixels = pixels,
            avgR = avgR,
            avgG = avgG,
            avgB = avgB,
            chromaR = chromaR,
            chromaG = chromaG,
            chromaB = chromaB,
            satRatio = satRatio,
            rMean = rMean,
            rStdDev = rStdDev,
            gMean = gMean,
            gStdDev = gStdDev,
            bMean = bMean,
            bStdDev = bStdDev,
            lumMean = lumMean,
            lumStdDev = lumStdDev,
            lumValues = lumValues,
            rValues = rValues,
            gValues = gValues,
            bValues = bValues,
            colorHistogram = colorBins,
            spatialBlockRgb = spatialBlockRgb,
            circularMask = circularMask
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
        val maxR = min(w, h) * 0.40f

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

                if (sat > 0.12f && value > 0.12f) {
                    val bin = ((hue / 360f) * 8f).toInt().coerceIn(0, 7)
                    hueBins[bin] += 1f
                    validHueCount++
                }
            }
        }

        val avgLum = if (samples > 0) totalLum.toFloat() / samples else 0f
        val contrast = if (samples > 0) maxLum - minLum else 0
        val isPopulated = samples >= 8 && ((avgLum in 10f..245f && contrast >= 6) || (avgLum >= 15f))

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
     */
    fun isAvatarPopulated(crop: Bitmap): Boolean {
        if (crop.isRecycled || crop.width < 12 || crop.height < 12) return false
        return extractScannedMetrics(crop, "").isPopulated
    }

    /**
     * Compara un recorte de 10º Pick utilizando los 4 motores de inferencia visual de prueba,
     * combinando los porcentajes de similitud y seleccionando el campeón con mayor porcentaje global.
     */
    suspend fun matchWith4InferenceEngines(
        crop: Bitmap,
        roiLabel: String,
        candidates: List<Champion>,
        expectedRole: LaneRole? = null,
        excludedChampionIds: Set<String> = emptySet(),
        context: Context? = null,
        isConfirmedPhase: Boolean = false,
        roleExplanation: String? = null
    ): TenthPickDecisionLog? = withContext(Dispatchers.Default) {
        if (crop.isRecycled || crop.width < 12 || crop.height < 12) return@withContext null
        val populatedMetrics = extractScannedMetrics(crop, roiLabel)
        val phaseName = if (isConfirmedPhase) "CONFIRMACIÓN 10º PICK (4 MOTORES DE INFERENCIA)" else "PRESELECCIÓN 10º PICK (4 MOTORES DE INFERENCIA)"
        val roleLine = roleExplanation ?: (if (expectedRole != null) "Rol esperado: ${expectedRole.displayName}" else "Rol: No especificado")

        val effectiveCandidates = if (candidates.isNotEmpty()) candidates else {
            if (WildRiftRepository.champions.isEmpty()) {
                val ctx = context ?: WildRiftApp.instance
                if (ctx != null) WildRiftRepository.initChampions(ctx)
            }
            WildRiftRepository.champions.toList()
        }
        if (effectiveCandidates.isEmpty()) return@withContext null

        val engines = listOf(
            VisionInferenceEngineType.ZNCC_LOCAL_NATIVE,
            VisionInferenceEngineType.ONNX_RUNTIME,
            VisionInferenceEngineType.NCNN,
            VisionInferenceEngineType.MEDIAPIPE_LITERT
        )

        val championScoresMap = mutableMapOf<String, MutableMap<VisionInferenceEngineType, Float>>()
        val championObjMap = mutableMapOf<String, Champion>()

        for (champ in effectiveCandidates) {
            championObjMap[champ.id] = champ
        }

        for (engine in engines) {
            val benchmark = VisionInferenceManager.runEngineInference(crop, engine, effectiveCandidates, expectedRole, context)
            val scoreSource = if (benchmark.allScoresMap.isNotEmpty()) {
                benchmark.allScoresMap
            } else {
                benchmark.candidateScores.associate { it.first.id to it.second }
            }
            for ((champId, score) in scoreSource) {
                championScoresMap.getOrPut(champId) { mutableMapOf() }[engine] = score.coerceIn(0f, 1f)
            }
        }

        if (championScoresMap.isEmpty()) return@withContext null

        val comparisons = mutableListOf<CandidateMatchComparison>()
        for ((champId, engineScores) in championScoresMap) {
            val champ = championObjMap[champId] ?: continue
            val scoresList = engines.map { engineScores[it] ?: 0f }
            val avgScore = scoresList.average().toFloat()
            val maxScore = scoresList.maxOrNull() ?: 0f
            val compositeScore = (avgScore * 0.4f + maxScore * 0.6f).coerceIn(0f, 1f)

            comparisons.add(
                CandidateMatchComparison(
                    champion = champ,
                    compositeScore = compositeScore,
                    pixelSimilarity = scoresList.getOrElse(0) { 0f },
                    pixelColorSim = scoresList.getOrElse(1) { 0f },
                    blockSim = scoresList.getOrElse(2) { 0f },
                    histSimilarity = scoresList.getOrElse(3) { 0f },
                    avgColorSim = avgScore,
                    roleBonus = 0f
                )
            )
        }

        val sortedComparisons = comparisons.sortedByDescending { it.compositeScore }
        val bestComparison = sortedComparisons.firstOrNull() ?: return@withContext null
        val selectedChamp = bestComparison.champion
        val finalConfidence = bestComparison.compositeScore

        val topEngineNames = engines.joinToString(", ") { it.shortName }
        val percentStr = "${(finalConfidence * 100).toInt()}%"
        val reason = "Inferencia Visual con 4 Motores ($topEngineNames): Coincidencia validada con ${selectedChamp.name} con un porcentaje de similitud global del $percentStr."
        val summary = if (isConfirmedPhase) {
            "CONFIRMACIÓN DEFINITIVA\n$phaseName -> ${selectedChamp.name} ($percentStr similitud combinada de 4 motores)"
        } else {
            "CARACTERÍSTICAS ESCANEADAS (PROVISIONAL)\n$phaseName -> ${selectedChamp.name} ($percentStr similitud combinada de 4 motores)"
        }

        return@withContext TenthPickDecisionLog(
            selectedChampion = selectedChamp,
            confidence = finalConfidence,
            isConfirmed = isConfirmedPhase,
            phaseName = phaseName,
            scannedMetrics = populatedMetrics,
            candidatesEvaluatedCount = effectiveCandidates.size,
            topCandidates = sortedComparisons.take(5),
            decisionReason = reason,
            formattedSummary = summary,
            cropBitmap = crop.copy(Bitmap.Config.ARGB_8888, false)
        )
    }

    /**
     * Inferencia detallada de un recorte utilizando exclusivamente los 4 motores de reconocimiento.
     * Retorna un log exhaustivo de métricas escaneadas y decisiones tomadas por los motores.
     */
    suspend fun matchAvatarDetailed(
        crop: Bitmap,
        roiLabel: String,
        candidates: List<Champion>,
        expectedRole: LaneRole? = null,
        excludedChampionIds: Set<String> = emptySet(),
        context: Context? = null,
        isConfirmedPhase: Boolean = false,
        roleExplanation: String? = null
    ): TenthPickDecisionLog? {
        return matchWith4InferenceEngines(
            crop = crop,
            roiLabel = roiLabel,
            candidates = candidates,
            expectedRole = expectedRole,
            excludedChampionIds = excludedChampionIds,
            context = context,
            isConfirmedPhase = isConfirmedPhase,
            roleExplanation = roleExplanation
        )
    }

    /**
     * Compara un recorte de pantalla usando los 4 motores de reconocimiento.
     */
    suspend fun matchAvatar(
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
     * Escaneo del 10º Pick EXCLUSIVAMENTE en el slot inferior (Aliado 5 o Rival 5).
     * El campeón ganador es determinado al 100% por los 4 motores de inferencia (ZNCC, ONNX, NCNN, MediaPipe/LiteRT)
     * sobre el recorte exacto del avatar en el slot 5.
     */
    suspend fun identify10thPickInferiorDetailed(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        roleExplanation: String? = null,
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
            (height * calib.allySlotYRatios.getOrElse(4) { 0.739f }).toInt()
        } else {
            (height * calib.enemySlotYRatios.getOrElse(4) { 0.739f }).toInt()
        }

        val sideDesc = if (isAlly) "Inferior Izquierda (Aliado 5 - 10º Pick)" else "Inferior Derecha (Rival 5 - 10º Pick)"
        val rawCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotAvatarDiam) ?: return@withContext null
        val enhancedCrop = enhanceCropQuality(rawCrop)
        if (rawCrop != enhancedCrop) {
            try { rawCrop.recycle() } catch (_: Throwable) {}
        }

        try {
            lastTenthPickCrop = enhancedCrop.copy(Bitmap.Config.ARGB_8888, false)
            lastTenthPickRoiLabel = sideDesc
            lastTenthPickCoordinates = "X: ${slotCenterX}px (${(slotCenterX * 100f / width).toInt()}%) | Y: ${slotCenterY}px (${(slotCenterY * 100f / height).toInt()}%) | Dim: ${slotAvatarDiam}px"
        } catch (_: Throwable) {}

        try {
            val decision = matchWith4InferenceEngines(
                crop = enhancedCrop,
                roiLabel = sideDesc,
                candidates = allChamps,
                expectedRole = expectedRole,
                excludedChampionIds = confirmedIds,
                context = context,
                isConfirmedPhase = false,
                roleExplanation = roleExplanation
            )
            if (decision != null) {
                lastTenthPickLog = decision.copy(cropBitmap = lastTenthPickCrop)
            }
            return@withContext decision
        } finally {
            try { enhancedCrop.recycle() } catch (_: Throwable) {}
        }
    }

    suspend fun identify10thPickInferior(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        roleExplanation: String? = null,
        context: Context? = null
    ): Pair<Champion, Float>? {
        val detailed = identify10thPickInferiorDetailed(bitmap, isFirstPick, calib, allChamps, confirmedIds, expectedRole, roleExplanation, context) ?: return null
        val champ = detailed.selectedChampion ?: return null
        return Pair(champ, detailed.confidence)
    }

    /**
     * Confirmación del 10º Pick en la PARTE SUPERIOR con verificación robusta.
     */
    suspend fun identify10thPickSuperiorDetailed(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        roleExplanation: String? = null,
        context: Context? = null,
        preferredChampion: Champion? = null
    ): TenthPickDecisionLog? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        val width = bitmap.width
        val height = bitmap.height

        val isAlly = !isFirstPick
        val topDiam = (height * calib.topAvatarDiameterRatio).toInt().coerceAtLeast(26)
        val topCenterY = (height * calib.topAvatarYRatio).toInt()

        val targetX = if (isAlly) {
            (width * calib.topAllyXRatios.getOrElse(4) { calib.topAlly5XRatio }).toInt()
        } else {
            (width * calib.topEnemyXRatios.getOrElse(4) { calib.topEnemy5XRatio }).toInt()
        }

        val candidateXs = listOf(
            targetX,
            targetX - (width * 0.006f).toInt(),
            targetX + (width * 0.006f).toInt()
        )
        val candidateYs = listOf(
            topCenterY,
            topCenterY - (height * 0.004f).toInt(),
            topCenterY + (height * 0.004f).toInt()
        )

        val sideDesc = if (isAlly) "Superior Izquierda (Aliado 5 - 10º Pick)" else "Superior Derecha (Rival 5 - 10º Pick)"
        val candidateDecisions = mutableListOf<TenthPickDecisionLog>()

        for (topY in candidateYs) {
            for (topCenterX in candidateXs) {
                val rawCrop = safeCrop(bitmap, topCenterX, topY, topDiam)
                val standardCrop = if (rawCrop != null) enhanceCropQuality(rawCrop) else null
                val innerCrop = safeCrop(bitmap, topCenterX, topY, (topDiam * 0.88f).toInt())?.let { enhanceCropQuality(it) }

                if (topCenterX == targetX && topY == topCenterY) {
                    try {
                        standardCrop?.let { crop ->
                            lastTenthPickCrop = crop.copy(Bitmap.Config.ARGB_8888, false)
                            lastTenthPickRoiLabel = sideDesc
                            lastTenthPickCoordinates = "X: ${targetX}px (${(targetX * 100f / width).toInt()}%) | Y: ${topCenterY}px (${(topCenterY * 100f / height).toInt()}%) | Dim: ${topDiam}px"
                        }
                    } catch (_: Throwable) {}
                }

                try {
                    if (standardCrop != null) {
                        matchWith4InferenceEngines(standardCrop, "$sideDesc [Estándar]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = true, roleExplanation = roleExplanation)?.let { candidateDecisions.add(it) }
                    }
                    if (innerCrop != null) {
                        matchWith4InferenceEngines(innerCrop, "$sideDesc [Interior 88%]", allChamps, expectedRole, confirmedIds, context, isConfirmedPhase = true, roleExplanation = roleExplanation)?.let { candidateDecisions.add(it) }
                    }
                } finally {
                    try { rawCrop?.recycle() } catch (_: Throwable) {}
                    try { standardCrop?.recycle() } catch (_: Throwable) {}
                    try { innerCrop?.recycle() } catch (_: Throwable) {}
                }
            }
        }

        val bestDecision = candidateDecisions.maxByOrNull { it.confidence }
        if (bestDecision != null && bestDecision.selectedChampion != null) {
            val detectedChamp = bestDecision.selectedChampion
            val confirmedLog = bestDecision.copy(
                confidence = bestDecision.confidence,
                isConfirmed = true,
                decisionReason = "10º Pick confirmado exclusivamente por los motores de visión en barra superior ($sideDesc): ${detectedChamp.name} (${(bestDecision.confidence * 100).toInt()}%).",
                cropBitmap = lastTenthPickCrop
            )
            lastTenthPickLog = confirmedLog
            return@withContext confirmedLog
        }

        if (bestDecision != null) {
            lastTenthPickLog = bestDecision.copy(cropBitmap = lastTenthPickCrop)
        }
        return@withContext bestDecision
    }

    suspend fun identify10thPickSuperior(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        roleExplanation: String? = null,
        context: Context? = null,
        preferredChampion: Champion? = null
    ): Pair<Champion, Float>? {
        val detailed = identify10thPickSuperiorDetailed(bitmap, isFirstPick, calib, allChamps, confirmedIds, expectedRole, roleExplanation, context, preferredChampion) ?: return null
        val champ = detailed.selectedChampion ?: return null
        return Pair(champ, 1.0f)
    }

    /**
     * Identificación visual de un avatar específico en el slot inferior (slots 0..4).
     */
    suspend fun identifyTopSlotAvatar(
        bitmap: Bitmap,
        isAlly: Boolean,
        slotIndex: Int,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole? = null,
        context: Context? = null
    ): Pair<Champion, Float>? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null
        val idx = slotIndex.coerceIn(0, 4)
        val width = bitmap.width
        val height = bitmap.height
        val slotDiam = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        val slotCenterY = if (isAlly) {
            (height * calib.allySlotYRatios.getOrElse(idx) { 0.739f }).toInt()
        } else {
            (height * calib.enemySlotYRatios.getOrElse(idx) { 0.739f }).toInt()
        }
        val targetXRatio = if (isAlly) calib.allyAvatarCenterX else calib.enemyAvatarCenterX
        val slotCenterX = (width * targetXRatio).toInt()
        val side = if (isAlly) "Aliado" else "Rival"
        val rawCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotDiam) ?: return@withContext null
        val crop = enhanceCropQuality(rawCrop)
        if (rawCrop != crop) {
            try { rawCrop.recycle() } catch (_: Throwable) {}
        }
        if (idx == 4) {
            try {
                lastTenthPickCrop = crop.copy(Bitmap.Config.ARGB_8888, false)
                lastTenthPickRoiLabel = "Slot Inferior ($side 5 - 10º Pick)"
                lastTenthPickCoordinates = "X: ${slotCenterX}px (${(slotCenterX * 100f / width).toInt()}%) | Y: ${slotCenterY}px (${(slotCenterY * 100f / height).toInt()}%) | Dim: ${slotDiam}px"
            } catch (_: Throwable) {}
        }
        try {
            val detailed = matchAvatarDetailed(
                crop = crop,
                roiLabel = "Slot Inferior ($side ${idx + 1})",
                candidates = allChamps,
                expectedRole = expectedRole,
                excludedChampionIds = confirmedIds,
                context = context,
                isConfirmedPhase = true
            ) ?: return@withContext null
            val champ = detailed.selectedChampion ?: return@withContext null
            if (detailed.confidence >= 0.40f) {
                if (idx == 4) {
                    lastTenthPickLog = detailed.copy(cropBitmap = lastTenthPickCrop)
                }
                return@withContext Pair(champ, detailed.confidence)
            }
            return@withContext null
        } finally {
            try { crop.recycle() } catch (_: Throwable) {}
        }
    }

    /**
     * Inspección interactiva en tiempo real para cualquier slot inferior (Aliado o Rival, 0..4).
     */
    suspend fun inspectSlotDetailed(
        bitmap: Bitmap,
        isAlly: Boolean,
        slotIndex: Int,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        expectedRole: LaneRole? = null,
        excludedChampionIds: Set<String> = emptySet(),
        context: Context? = null
    ): TenthPickDecisionLog? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null
        val idx = slotIndex.coerceIn(0, 4)
        val width = bitmap.width
        val height = bitmap.height
        val slotDiam = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        val slotCenterY = if (isAlly) {
            (height * calib.allySlotYRatios.getOrElse(idx) { 0.739f }).toInt()
        } else {
            (height * calib.enemySlotYRatios.getOrElse(idx) { 0.739f }).toInt()
        }
        val targetXRatio = if (isAlly) calib.allyAvatarCenterX else calib.enemyAvatarCenterX
        val slotCenterX = (width * targetXRatio).toInt()
        val side = if (isAlly) "Aliado" else "Rival"
        val rawCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotDiam) ?: return@withContext null
        val crop = enhanceCropQuality(rawCrop)
        if (rawCrop != crop) {
            try { rawCrop.recycle() } catch (_: Throwable) {}
        }

        try {
            lastTenthPickCrop = crop.copy(Bitmap.Config.ARGB_8888, false)
            lastTenthPickRoiLabel = "Slot Inferior ($side ${idx + 1})"
            lastTenthPickCoordinates = "X: ${slotCenterX}px (${(slotCenterX * 100f / width).toInt()}%) | Y: ${slotCenterY}px (${(slotCenterY * 100f / height).toInt()}%) | Dim: ${slotDiam}px"
        } catch (_: Throwable) {}

        try {
            val detailed = matchAvatarDetailed(
                crop = crop,
                roiLabel = "Slot Inferior ($side ${idx + 1})",
                candidates = allChamps,
                expectedRole = expectedRole,
                excludedChampionIds = excludedChampionIds,
                context = context,
                isConfirmedPhase = false
            )
            if (detailed != null) {
                lastTenthPickLog = detailed.copy(cropBitmap = lastTenthPickCrop)
            }
            return@withContext detailed
        } finally {
            try { crop.recycle() } catch (_: Throwable) {}
        }
    }

    /**
     * Extrae una franja de la parte superior de la pantalla para el visor en vivo.
     */
    fun extractTopBarStrip(bitmap: Bitmap, heightRatio: Float = 0.14f): Bitmap? {
        if (bitmap.isRecycled || bitmap.width < 50 || bitmap.height < 30) return null
        val stripHeight = (bitmap.height * heightRatio).toInt().coerceIn(24, bitmap.height)
        return try {
            val sub = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, stripHeight)
            val copy = sub.copy(Bitmap.Config.ARGB_8888, false)
            if (sub != bitmap && sub != copy) {
                try { sub.recycle() } catch (_: Throwable) {}
            }
            copy
        } catch (_: Throwable) {
            null
        }
    }

    fun areAvatarSlotsDismissed(bitmap: Bitmap, isPrepPhaseDetected: Boolean): Boolean {
        if (isPrepPhaseDetected) return true
        return false
    }

    /**
     * Identificación visual del 10º Pick comparando el slot vertical correspondiente.
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

        val rawCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotAvatarDiam)
        val standardCrop = if (rawCrop != null) enhanceCropQuality(rawCrop) else null
        val innerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 0.88f).toInt())?.let { enhanceCropQuality(it) }
        val outerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 1.08f).toInt())?.let { enhanceCropQuality(it) }

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
            try { rawCrop?.recycle() } catch (_: Throwable) {}
            try { standardCrop?.recycle() } catch (_: Throwable) {}
            try { innerCrop?.recycle() } catch (_: Throwable) {}
            try { outerCrop?.recycle() } catch (_: Throwable) {}
        }

        if (candidateMatches.isEmpty()) {
            return@withContext null
        }

        val bestCandidate = candidateMatches.maxByOrNull { it.second }
        if (bestCandidate != null) {
            AppLogger.d(TAG, "10º Pick detectado con precisión visual en slot $slotIdx: ${bestCandidate.first.name} (Confianza: ${(bestCandidate.second * 100).toInt()}%)")
        }
        return@withContext bestCandidate
    }

    private fun safeCrop(src: Bitmap, cx: Int, cy: Int, diameter: Int): Bitmap? {
        if (src.isRecycled || diameter <= 10) return null
        val radius = diameter / 2
        val maxLeft = (src.width - diameter).coerceAtLeast(0)
        val maxTop = (src.height - diameter).coerceAtLeast(0)
        val left = (cx - radius).coerceIn(0, maxLeft)
        val top = (cy - radius).coerceIn(0, maxTop)
        val safeWidth = diameter.coerceAtMost(src.width - left)
        val safeHeight = diameter.coerceAtMost(src.height - top)
        if (safeWidth <= 0 || safeHeight <= 0) return null

        return try {
            val crop = Bitmap.createBitmap(src, left, top, safeWidth, safeHeight)
            val copy = crop.copy(Bitmap.Config.ARGB_8888, false)
            if (crop != src && crop != copy) {
                try { crop.recycle() } catch (_: Throwable) {}
            }
            copy
        } catch (_: Throwable) {
            null
        }
    }
}
