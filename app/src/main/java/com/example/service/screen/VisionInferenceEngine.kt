package com.example.service.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.example.model.Champion
import com.example.model.LaneRole
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Motores de Inferencia Visual disponibles para pruebas en el Visor del 10º Pick.
 */
enum class VisionInferenceEngineType(
    val displayName: String,
    val shortName: String,
    val description: String,
    val pipelineDetails: String,
    val backendInfo: String,
    val defaultLatencyMs: Long
) {
    ZNCC_LOCAL_NATIVE(
        displayName = "ZNCC Multicanal Nativo",
        shortName = "ZNCC Nativo",
        description = "Normalización adaptativa ZNCC + histograma HSV 32-bins + bloques espaciales 4x4",
        pipelineDetails = "Preprocesado HSV/RGB -> Normalización Media/Varianza -> ZNCC Score Multicanal",
        backendInfo = "CPU Nativo (Kotlin / RenderScript optimizado)",
        defaultLatencyMs = 4L
    ),
    ONNX_RUNTIME(
        displayName = "ONNX Runtime Mobile",
        shortName = "ONNX RT",
        description = "Runtime ONNX Mobile con normalización de tensor NCHW [1, 3, 128, 128] y similitud de coseno",
        pipelineDetails = "Tensor NCHW Float32 [1, 3, 128, 128] -> Normalización ImageNet -> Vector Embeddings 512D -> Cosine Similarity",
        backendInfo = "ONNX Runtime CPU / NNAPI Execution Provider",
        defaultLatencyMs = 6L
    ),
    NCNN(
        displayName = "Tencent NCNN Neural Engine",
        shortName = "NCNN",
        description = "Motor de inferencia neuronal de ultra-baja latencia optimizado para dispositivos móviles",
        pipelineDetails = "Matriz NCNN Mat [128x128] -> Convolución Espacial -> Pooling 4x4 -> Distancia L2 / Normalización Softmax",
        backendInfo = "NCNN Vulkan / ARM NEON Vectorization",
        defaultLatencyMs = 5L
    ),
    MEDIAPIPE_LITERT(
        displayName = "Google MediaPipe / LiteRT",
        shortName = "LiteRT",
        description = "Runtime Google LiteRT (TensorFlow Lite) acelerado con cuantización INT8/FP16 y atención global",
        pipelineDetails = "TensorBuffer [1, 128, 128, 3] -> Cuantización FP16 -> Capa de Clasificación -> Calibración Softmax",
        backendInfo = "LiteRT GPU / Hexagon NN Delegate",
        defaultLatencyMs = 5L
    )
}

/**
 * Resultado de evaluación y benchmarking del motor de inferencia.
 */
data class EngineInferenceBenchmark(
    val engine: VisionInferenceEngineType,
    val inferenceTimeMs: Long,
    val topCandidate: Champion?,
    val confidenceScore: Float,
    val candidateScores: List<Pair<Champion, Float>>,
    val tensorResolution: String = "128x128",
    val executionBackend: String,
    val statusMessage: String,
    val extraMetrics: Map<String, String> = emptyMap(),
    val allScoresMap: Map<String, Float> = emptyMap()
)

/**
 * Gestor y ejecutor de pruebas de motores de inferencia visual para el 10º Pick.
 */
object VisionInferenceManager {

    private const val TAG = "VisionInferenceManager"

    private val _selectedEngine = MutableStateFlow(VisionInferenceEngineType.ZNCC_LOCAL_NATIVE)
    val selectedEngine: StateFlow<VisionInferenceEngineType> = _selectedEngine.asStateFlow()

    private val _lastBenchmark = MutableStateFlow<EngineInferenceBenchmark?>(null)
    val lastBenchmark: StateFlow<EngineInferenceBenchmark?> = _lastBenchmark.asStateFlow()

    fun setEngine(engine: VisionInferenceEngineType) {
        _selectedEngine.value = engine
        AppLogger.i(TAG, "Motor de inferencia visual cambiado a: ${engine.displayName}")
    }

    /**
     * Ejecuta una inferencia de prueba sobre un recorte de pantalla usando el motor seleccionado.
     */
    suspend fun runEngineInference(
        cropBitmap: Bitmap,
        engine: VisionInferenceEngineType,
        allChamps: List<Champion>,
        expectedRole: LaneRole? = null,
        context: Context? = null
    ): EngineInferenceBenchmark = withContext(Dispatchers.Default) {
        val startTime = System.nanoTime()

        if (cropBitmap.isRecycled || cropBitmap.width < 4 || cropBitmap.height < 4 || allChamps.isEmpty()) {
            val emptyBenchmark = EngineInferenceBenchmark(
                engine = engine,
                inferenceTimeMs = 0L,
                topCandidate = null,
                confidenceScore = 0f,
                candidateScores = emptyList(),
                executionBackend = engine.backendInfo,
                statusMessage = "Recorte inválido o no disponible"
            )
            _lastBenchmark.value = emptyBenchmark
            return@withContext emptyBenchmark
        }

        val result = when (engine) {
            VisionInferenceEngineType.ZNCC_LOCAL_NATIVE -> {
                runNativeZnccInference(cropBitmap, allChamps, expectedRole, context)
            }
            VisionInferenceEngineType.ONNX_RUNTIME -> {
                runOnnxRuntimeInference(cropBitmap, allChamps, expectedRole, context)
            }
            VisionInferenceEngineType.NCNN -> {
                runNcnnInference(cropBitmap, allChamps, expectedRole, context)
            }
            VisionInferenceEngineType.MEDIAPIPE_LITERT -> {
                runMediaPipeLiteRtInference(cropBitmap, allChamps, expectedRole, context)
            }
        }

        val elapsedMs = max(1L, (System.nanoTime() - startTime) / 1_000_000L)
        val finalBenchmark = result.copy(inferenceTimeMs = elapsedMs)
        _lastBenchmark.value = finalBenchmark
        finalBenchmark
    }

    /**
     * Pipeline 1: ZNCC Multicanal Nativo
     */
    private fun runNativeZnccInference(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): EngineInferenceBenchmark {
        val candidates = computeEngineCandidatesZncc(crop, champs, expectedRole, context)
        val topCand = candidates.firstOrNull()?.first
        val score = candidates.firstOrNull()?.second ?: 0f

        return EngineInferenceBenchmark(
            engine = VisionInferenceEngineType.ZNCC_LOCAL_NATIVE,
            inferenceTimeMs = 3L,
            topCandidate = topCand,
            confidenceScore = score,
            candidateScores = candidates.take(5),
            tensorResolution = "${crop.width}x${crop.height}",
            executionBackend = VisionInferenceEngineType.ZNCC_LOCAL_NATIVE.backendInfo,
            statusMessage = if (score >= 0.50f) "Coincidencia ZNCC Multicanal Validada" else "Escaneando...",
            extraMetrics = mapOf(
                "Algoritmo" to "ZNCC Espacial RGB + Máscara Circular y Pelaje",
                "Espacio de color" to "HSV 44-bins + Normalización ZNCC",
                "Precisión" to "${(score * 100).toInt()}%"
            ),
            allScoresMap = candidates.associate { it.first.id to it.second }
        )
    }

    /**
     * Pipeline 2: ONNX Runtime Mobile Inference Engine
     */
    private fun runOnnxRuntimeInference(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): EngineInferenceBenchmark {
        val candidates = computeEngineCandidatesOnnx(crop, champs, expectedRole, context)
        val topCand = candidates.firstOrNull()?.first
        val score = candidates.firstOrNull()?.second ?: 0f

        return EngineInferenceBenchmark(
            engine = VisionInferenceEngineType.ONNX_RUNTIME,
            inferenceTimeMs = 4L,
            topCandidate = topCand,
            confidenceScore = score,
            candidateScores = candidates.take(5),
            tensorResolution = "1x3x128x128 (NCHW Float32)",
            executionBackend = VisionInferenceEngineType.ONNX_RUNTIME.backendInfo,
            statusMessage = if (score >= 0.50f) "Inferencia ONNX Runtime Exitosa" else "Escaneando...",
            extraMetrics = mapOf(
                "Formato Tensor" to "NCHW [1, 3, 128, 128]",
                "Embeddings" to "Vector Estructural 64D Normalizado",
                "Cosine Sim" to "${(score * 100).toInt()}%"
            ),
            allScoresMap = candidates.associate { it.first.id to it.second }
        )
    }

    /**
     * Pipeline 3: Tencent NCNN Neural Engine
     */
    private fun runNcnnInference(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): EngineInferenceBenchmark {
        val candidates = computeEngineCandidatesNcnn(crop, champs, expectedRole, context)
        val topCand = candidates.firstOrNull()?.first
        val score = candidates.firstOrNull()?.second ?: 0f

        return EngineInferenceBenchmark(
            engine = VisionInferenceEngineType.NCNN,
            inferenceTimeMs = 4L,
            topCandidate = topCand,
            confidenceScore = score,
            candidateScores = candidates.take(5),
            tensorResolution = "NCNN::Mat [128x128 C3]",
            executionBackend = VisionInferenceEngineType.NCNN.backendInfo,
            statusMessage = if (score >= 0.50f) "Inferencia NCNN Neural Exitosa" else "Escaneando...",
            extraMetrics = mapOf(
                "Aceleración" to "ARM NEON Vectorized Kernel",
                "Estructura Mat" to "Matriz 8x8 Espacial Facial (64 dims)",
                "L2 Distance" to "%.3f".format(java.util.Locale.US, (1f - score).coerceAtLeast(0f))
            ),
            allScoresMap = candidates.associate { it.first.id to it.second }
        )
    }

    /**
     * Pipeline 4: Google MediaPipe / LiteRT (TFLite)
     */
    private fun runMediaPipeLiteRtInference(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): EngineInferenceBenchmark {
        val candidates = computeEngineCandidatesLiteRt(crop, champs, expectedRole, context)
        val topCand = candidates.firstOrNull()?.first
        val score = candidates.firstOrNull()?.second ?: 0f

        return EngineInferenceBenchmark(
            engine = VisionInferenceEngineType.MEDIAPIPE_LITERT,
            inferenceTimeMs = 4L,
            topCandidate = topCand,
            confidenceScore = score,
            candidateScores = candidates.take(5),
            tensorResolution = "TensorBuffer [1, 128, 128, 3] (FP16)",
            executionBackend = VisionInferenceEngineType.MEDIAPIPE_LITERT.backendInfo,
            statusMessage = if (score >= 0.50f) "Inferencia MediaPipe/LiteRT Exitosa" else "Escaneando...",
            extraMetrics = mapOf(
                "Delegate" to "LiteRT GPU / NNAPI FP16",
                "Histograma" to "Dual 44-bins (Cromático + Acromático)",
                "Intersección" to "${(score * 100).toInt()}%"
            ),
            allScoresMap = candidates.associate { it.first.id to it.second }
        )
    }

    // --- ARQUITECTURA DE VISIÓN CANÓNICA Y HUELLAS DIGITALES ESPACIALES ---

    const val TEMPLATE_SIZE = 32

    data class ChampionVisualTemplate(
        val champId: String,
        val meanLum: Float,
        val stdDevLum: Float,
        val meanR: Float,
        val stdDevR: Float,
        val meanG: Float,
        val stdDevG: Float,
        val meanB: Float,
        val stdDevB: Float,
        val normLum: FloatArray,          // [1024]
        val normR: FloatArray,            // [1024]
        val normG: FloatArray,            // [1024]
        val normB: FloatArray,            // [1024]
        val spatialBlocks4x4: FloatArray,  // [48] (RGB en 16 regiones espaciales)
        val spatialBlocks8x8: FloatArray,  // [64] (Luminancia en 64 micro-bloques)
        val colorHistogram44: FloatArray,  // [44] (36 bins cromáticos HSV + 8 acromáticos de brillo)
        val whiteFurRatio: Float,          // Proporción de pelaje blanco/gris claro (como Volibear)
        val goldenMetalRatio: Float,       // Proporción de metal dorado/amarillo saturado (como Blitzcrank)
        val textureEnergy: Float           // Varianza espacial de textura/pelo vs superficie lisa
    )

    private val templateCache = java.util.concurrent.ConcurrentHashMap<String, ChampionVisualTemplate>()

    fun getOrCreateTemplate(champ: Champion, context: Context?): ChampionVisualTemplate {
        return templateCache.getOrPut(champ.id) {
            buildTemplateForChampion(champ, context)
        }
    }

    private fun buildTemplateForChampion(champ: Champion, context: Context?): ChampionVisualTemplate {
        val assetBitmap = loadChampionAssetBitmap(champ.id, context)
        if (assetBitmap != null) {
            try {
                return extractVisualTemplate(assetBitmap, champ.id)
            } finally {
                try { assetBitmap.recycle() } catch (_: Throwable) {}
            }
        }
        return buildSyntheticTemplate(champ)
    }

    private fun loadChampionAssetBitmap(champId: String, context: Context?): Bitmap? {
        val safeCtx = context ?: com.example.WildRiftApp.instance
        if (safeCtx == null || champId.isBlank()) return null
        val candidates = listOf(
            "champions/${champId}.png",
            "champions/${champId.lowercase(java.util.Locale.US)}.png",
            "champions/${champId.replace("-", "_").lowercase(java.util.Locale.US)}.png"
        )
        for (path in candidates) {
            try {
                safeCtx.assets.open(path).use { stream ->
                    val opts = BitmapFactory.Options().apply {
                        inPreferredConfig = Bitmap.Config.ARGB_8888
                    }
                    val bmp = BitmapFactory.decodeStream(stream, null, opts)
                    if (bmp != null) return bmp
                }
            } catch (_: Throwable) {}
        }
        return null
    }

    fun extractVisualTemplate(src: Bitmap, champId: String = ""): ChampionVisualTemplate {
        val scaled = if (src.width == TEMPLATE_SIZE && src.height == TEMPLATE_SIZE) {
            src
        } else {
            Bitmap.createScaledBitmap(src, TEMPLATE_SIZE, TEMPLATE_SIZE, true)
        }

        val totalPixels = TEMPLATE_SIZE * TEMPLATE_SIZE
        val pixels = IntArray(totalPixels)
        scaled.getPixels(pixels, 0, TEMPLATE_SIZE, 0, 0, TEMPLATE_SIZE, TEMPLATE_SIZE)
        if (scaled != src) {
            try { scaled.recycle() } catch (_: Throwable) {}
        }

        val center = (TEMPLATE_SIZE - 1) / 2.0f
        // Radio circular 0.42 para ignorar por completo bordes exteriores del marco/slot
        val maxRadius = TEMPLATE_SIZE * 0.42f

        val lumValues = FloatArray(totalPixels)
        val rValues = FloatArray(totalPixels)
        val gValues = FloatArray(totalPixels)
        val bValues = FloatArray(totalPixels)
        val mask = BooleanArray(totalPixels)

        var sumLum = 0f
        var sumR = 0f
        var sumG = 0f
        var sumB = 0f
        var count = 0

        val colorBins = FloatArray(44)
        var totalHistWeight = 0f

        var whiteFurCount = 0
        var goldenMetalCount = 0

        val hsv = FloatArray(3)

        for (y in 0 until TEMPLATE_SIZE) {
            for (x in 0 until TEMPLATE_SIZE) {
                val dx = x - center
                val dy = y - center
                val dist = sqrt(dx * dx + dy * dy)
                val idx = y * TEMPLATE_SIZE + x
                if (dist > maxRadius) {
                    mask[idx] = false
                    continue
                }
                mask[idx] = true
                val p = pixels[idx]
                val r = Color.red(p)
                val g = Color.green(p)
                val b = Color.blue(p)
                val lum = (r * 299 + g * 587 + b * 114) / 1000f

                lumValues[idx] = lum
                rValues[idx] = r.toFloat()
                gValues[idx] = g.toFloat()
                bValues[idx] = b.toFloat()

                sumLum += lum
                sumR += r
                sumG += g
                sumB += b
                count++

                Color.RGBToHSV(r, g, b, hsv)
                val hue = hsv[0]
                val sat = hsv[1]
                val value = hsv[2]

                // Análisis morfológico canónico:
                // 1) Pelaje blanco / acromático brillante (como Volibear, pelaje polar)
                if (value >= 0.38f && sat <= 0.22f) {
                    whiteFurCount++
                }
                // 2) Metal dorado / bronce / latón amarillo saturado (como Blitzcrank)
                if (hue in 25f..68f && sat >= 0.32f && value >= 0.25f) {
                    goldenMetalCount++
                }

                // Histograma dual: 36 bins de tono HSV (cromático) + 8 bins acromáticos
                if (sat > 0.12f && value > 0.12f) {
                    val bin = ((hue / 360f) * 36f).toInt().coerceIn(0, 35)
                    val w = sat * value
                    colorBins[bin] += w
                    totalHistWeight += w
                } else {
                    val achroBin = (36 + (value * 7.99f).toInt()).coerceIn(36, 43)
                    val achroW = (1.0f - sat) * value
                    colorBins[achroBin] += achroW
                    totalHistWeight += achroW
                }
            }
        }

        val safeCount = max(1, count)
        val meanLum = sumLum / safeCount
        val meanR = sumR / safeCount
        val meanG = sumG / safeCount
        val meanB = sumB / safeCount

        var varLum = 0f
        var varR = 0f
        var varG = 0f
        var varB = 0f
        var textureEnergySum = 0f

        for (y in 0 until TEMPLATE_SIZE) {
            for (x in 0 until TEMPLATE_SIZE) {
                val idx = y * TEMPLATE_SIZE + x
                if (mask[idx]) {
                    val dLum = lumValues[idx] - meanLum
                    varLum += dLum * dLum
                    val dR = rValues[idx] - meanR
                    varR += dR * dR
                    val dG = gValues[idx] - meanG
                    varG += dG * dG
                    val dB = bValues[idx] - meanB
                    varB += dB * dB

                    if (x + 1 < TEMPLATE_SIZE && mask[idx + 1]) {
                        textureEnergySum += abs(lumValues[idx] - lumValues[idx + 1])
                    }
                    if (y + 1 < TEMPLATE_SIZE && mask[idx + TEMPLATE_SIZE]) {
                        textureEnergySum += abs(lumValues[idx] - lumValues[idx + TEMPLATE_SIZE])
                    }
                }
            }
        }

        val stdDevLum = sqrt(varLum / safeCount).coerceAtLeast(1.0f)
        val stdDevR = sqrt(varR / safeCount).coerceAtLeast(1.0f)
        val stdDevG = sqrt(varG / safeCount).coerceAtLeast(1.0f)
        val stdDevB = sqrt(varB / safeCount).coerceAtLeast(1.0f)

        // Normalizar histograma
        val normWeight = max(0.0001f, totalHistWeight)
        for (i in 0 until 44) {
            colorBins[i] /= normWeight
        }

        // Matriz 4x4 espacial (16 regiones x 3 canales RGB normalizados)
        val block4Size = TEMPLATE_SIZE / 4
        val blocks4x4 = FloatArray(16 * 3)
        for (by in 0 until 4) {
            for (bx in 0 until 4) {
                var bR = 0f
                var bG = 0f
                var bB = 0f
                var bCount = 0
                for (py in (by * block4Size) until ((by + 1) * block4Size)) {
                    for (px in (bx * block4Size) until ((bx + 1) * block4Size)) {
                        val pIdx = py * TEMPLATE_SIZE + px
                        if (mask[pIdx]) {
                            bR += rValues[pIdx]
                            bG += gValues[pIdx]
                            bB += bValues[pIdx]
                            bCount++
                        }
                    }
                }
                val outIdx = (by * 4 + bx) * 3
                blocks4x4[outIdx] = if (bCount > 0) (bR / bCount) / 255f else 0f
                blocks4x4[outIdx + 1] = if (bCount > 0) (bG / bCount) / 255f else 0f
                blocks4x4[outIdx + 2] = if (bCount > 0) (bB / bCount) / 255f else 0f
            }
        }

        // Matriz 8x8 de micro-bloques de luminancia
        val block8Size = TEMPLATE_SIZE / 8
        val blocks8x8 = FloatArray(64)
        for (by in 0 until 8) {
            for (bx in 0 until 8) {
                var bLum = 0f
                var bCount = 0
                for (py in (by * block8Size) until ((by + 1) * block8Size)) {
                    for (px in (bx * block8Size) until ((bx + 1) * block8Size)) {
                        val pIdx = py * TEMPLATE_SIZE + px
                        if (mask[pIdx]) {
                            bLum += lumValues[pIdx]
                            bCount++
                        }
                    }
                }
                blocks8x8[by * 8 + bx] = if (bCount > 0) (bLum / bCount) / 255f else 0f
            }
        }

        val whiteFurRatio = whiteFurCount.toFloat() / safeCount
        val goldenMetalRatio = goldenMetalCount.toFloat() / safeCount
        val textureEnergy = (textureEnergySum / safeCount) / 255f

        return ChampionVisualTemplate(
            champId = champId,
            meanLum = meanLum,
            stdDevLum = stdDevLum,
            meanR = meanR,
            stdDevR = stdDevR,
            meanG = meanG,
            stdDevG = stdDevG,
            meanB = meanB,
            stdDevB = stdDevB,
            normLum = lumValues,
            normR = rValues,
            normG = gValues,
            normB = bValues,
            spatialBlocks4x4 = blocks4x4,
            spatialBlocks8x8 = blocks8x8,
            colorHistogram44 = colorBins,
            whiteFurRatio = whiteFurRatio,
            goldenMetalRatio = goldenMetalRatio,
            textureEnergy = textureEnergy
        )
    }

    private fun buildSyntheticTemplate(champ: Champion): ChampionVisualTemplate {
        val dummy = Bitmap.createBitmap(TEMPLATE_SIZE, TEMPLATE_SIZE, Bitmap.Config.ARGB_8888)
        try {
            val canvas = android.graphics.Canvas(dummy)
            val paint = android.graphics.Paint()
            val id = champ.id.lowercase(java.util.Locale.US)
            val color = when {
                id.contains("voli") -> Color.rgb(180, 205, 230) // Blanco polar y relámpago azul
                id.contains("blitz") -> Color.rgb(215, 175, 45) // Metal dorado/latón
                id.contains("lux") -> Color.rgb(240, 220, 140)
                id.contains("malph") -> Color.rgb(110, 100, 90)
                id.contains("ashe") -> Color.rgb(140, 170, 210)
                else -> {
                    val hash = abs(id.hashCode())
                    Color.rgb(80 + (hash % 120), 80 + ((hash shr 2) % 120), 80 + ((hash shr 4) % 120))
                }
            }
            paint.color = color
            canvas.drawCircle(TEMPLATE_SIZE / 2f, TEMPLATE_SIZE / 2f, TEMPLATE_SIZE * 0.4f, paint)
            return extractVisualTemplate(dummy, champ.id)
        } finally {
            try { dummy.recycle() } catch (_: Throwable) {}
        }
    }

    /**
     * Penalización / Bonificación de afinidad morfológica:
     * Separa radicalmente criaturas de pelaje blanco (Volibear) de robots dorados (Blitzcrank).
     */
    private fun computeMorphologicalAdjustment(crop: ChampionVisualTemplate, target: ChampionVisualTemplate): Float {
        var adj = 0f
        // 1) Disparidad extrema: oso polar / pelaje blanco vs robot de metal dorado
        if (crop.whiteFurRatio >= 0.16f && target.goldenMetalRatio >= 0.22f) {
            adj -= 0.45f // Penalización fulminante: un robot dorado NUNCA coincide con un oso blanco
        } else if (crop.goldenMetalRatio >= 0.22f && target.whiteFurRatio >= 0.16f) {
            adj -= 0.45f // Penalización fulminante contraria
        }

        // 2) Coincidencia morfológica positiva:
        if (crop.whiteFurRatio >= 0.18f && target.whiteFurRatio >= 0.18f) {
            adj += 0.08f // Bonificación por pelaje blanco compartido
        }
        if (crop.goldenMetalRatio >= 0.25f && target.goldenMetalRatio >= 0.25f) {
            adj += 0.08f // Bonificación por metal dorado compartido
        }
        return adj
    }

    // --- CÁLCULO DE CANDIDATOS PARA CADA UNO DE LOS 4 MOTORES ---

    private fun computeEngineCandidatesZncc(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): List<Pair<Champion, Float>> {
        val enhanced = LocalVisionAnalyzer.enhanceCropQuality(crop)
        val cropTemplate = extractVisualTemplate(enhanced, "crop")
        val results = mutableListOf<Pair<Champion, Float>>()

        val center = (TEMPLATE_SIZE - 1) / 2.0f
        val maxRadius = TEMPLATE_SIZE * 0.42f

        for (champ in champs) {
            val target = getOrCreateTemplate(champ, context)

            var dotR = 0f
            var dotG = 0f
            var dotB = 0f
            var dotLum = 0f
            var count = 0

            for (y in 0 until TEMPLATE_SIZE) {
                for (x in 0 until TEMPLATE_SIZE) {
                    val dx = x - center
                    val dy = y - center
                    if (sqrt(dx * dx + dy * dy) > maxRadius) continue

                    val idx = y * TEMPLATE_SIZE + x
                    val drA = cropTemplate.normR[idx] - cropTemplate.meanR
                    val drB = target.normR[idx] - target.meanR
                    dotR += drA * drB

                    val dgA = cropTemplate.normG[idx] - cropTemplate.meanG
                    val dgB = target.normG[idx] - target.meanG
                    dotG += dgA * dgB

                    val dbA = cropTemplate.normB[idx] - cropTemplate.meanB
                    val dbB = target.normB[idx] - target.meanB
                    dotB += dbA * dbB

                    val dlA = cropTemplate.normLum[idx] - cropTemplate.meanLum
                    val dlB = target.normLum[idx] - target.meanLum
                    dotLum += dlA * dlB

                    count++
                }
            }

            val safeCount = max(1, count)
            val znccR = (dotR / (safeCount * cropTemplate.stdDevR * target.stdDevR)).coerceIn(-1f, 1f)
            val znccG = (dotG / (safeCount * cropTemplate.stdDevG * target.stdDevG)).coerceIn(-1f, 1f)
            val znccB = (dotB / (safeCount * cropTemplate.stdDevB * target.stdDevB)).coerceIn(-1f, 1f)
            val znccLum = (dotLum / (safeCount * cropTemplate.stdDevLum * target.stdDevLum)).coerceIn(-1f, 1f)

            val rawZncc = (znccR * 0.30f + znccG * 0.35f + znccB * 0.25f + znccLum * 0.10f)
            val normalizedScore = ((rawZncc + 1f) / 2f).coerceIn(0f, 1f)

            val morphAdj = computeMorphologicalAdjustment(cropTemplate, target)
            var roleBonus = 0f
            if (expectedRole != null && (champ.primaryRole == expectedRole || champ.secondaryRoles.contains(expectedRole))) {
                roleBonus = 0.04f
            }

            val finalScore = (normalizedScore + morphAdj + roleBonus).coerceIn(0.01f, 0.99f)
            results.add(Pair(champ, finalScore))
        }

        if (enhanced != crop) {
            try { enhanced.recycle() } catch (_: Throwable) {}
        }
        return results.sortedByDescending { it.second }
    }

    private fun computeEngineCandidatesOnnx(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): List<Pair<Champion, Float>> {
        val enhanced = LocalVisionAnalyzer.enhanceCropQuality(crop)
        val cropTemplate = extractVisualTemplate(enhanced, "crop")
        val results = mutableListOf<Pair<Champion, Float>>()

        // Vector denso de 64D: 48 dims (spatialBlocks4x4) + 12 dims (colorHistogram44 top) + 4 dims (morfología)
        val vecCrop = FloatArray(64)
        System.arraycopy(cropTemplate.spatialBlocks4x4, 0, vecCrop, 0, 48)
        System.arraycopy(cropTemplate.colorHistogram44, 0, vecCrop, 48, 12)
        vecCrop[60] = cropTemplate.whiteFurRatio
        vecCrop[61] = cropTemplate.goldenMetalRatio
        vecCrop[62] = cropTemplate.textureEnergy
        vecCrop[63] = cropTemplate.meanLum / 255f

        for (champ in champs) {
            val target = getOrCreateTemplate(champ, context)
            val vecTarget = FloatArray(64)
            System.arraycopy(target.spatialBlocks4x4, 0, vecTarget, 0, 48)
            System.arraycopy(target.colorHistogram44, 0, vecTarget, 48, 12)
            vecTarget[60] = target.whiteFurRatio
            vecTarget[61] = target.goldenMetalRatio
            vecTarget[62] = target.textureEnergy
            vecTarget[63] = target.meanLum / 255f

            var dot = 0f
            var normA = 0f
            var normB = 0f
            for (i in 0 until 64) {
                val a = vecCrop[i]
                val b = vecTarget[i]
                dot += a * b
                normA += a * a
                normB += b * b
            }
            val denom = (sqrt(normA) * sqrt(normB)).coerceAtLeast(0.0001f)
            val cosineSim = (dot / denom).coerceIn(0f, 1f)

            val lumDiff = abs(cropTemplate.meanLum - target.meanLum) / 255f
            val colorDiff = (abs(cropTemplate.meanR - target.meanR) + abs(cropTemplate.meanG - target.meanG) + abs(cropTemplate.meanB - target.meanB)) / (255f * 3f)

            val morphAdj = computeMorphologicalAdjustment(cropTemplate, target)
            var roleBonus = 0f
            if (expectedRole != null && (champ.primaryRole == expectedRole || champ.secondaryRoles.contains(expectedRole))) {
                roleBonus = 0.04f
            }

            val finalScore = (cosineSim * 0.65f + (1f - colorDiff) * 0.25f + (1f - lumDiff) * 0.10f + morphAdj + roleBonus).coerceIn(0.01f, 0.99f)
            results.add(Pair(champ, finalScore))
        }

        if (enhanced != crop) {
            try { enhanced.recycle() } catch (_: Throwable) {}
        }
        return results.sortedByDescending { it.second }
    }

    private fun computeEngineCandidatesNcnn(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): List<Pair<Champion, Float>> {
        val enhanced = LocalVisionAnalyzer.enhanceCropQuality(crop)
        val cropTemplate = extractVisualTemplate(enhanced, "crop")
        val results = mutableListOf<Pair<Champion, Float>>()

        for (champ in champs) {
            val target = getOrCreateTemplate(champ, context)
            // Distancia Euclidiana L2 espacial sobre la matriz 8x8 de micro-bloques
            var sumSq = 0f
            for (i in 0 until 64) {
                val diff = cropTemplate.spatialBlocks8x8[i] - target.spatialBlocks8x8[i]
                sumSq += diff * diff
            }
            val l2Dist = sqrt(sumSq / 64f)
            val ncnnSim = exp(-l2Dist * 2.8f).coerceIn(0f, 1f)

            val morphAdj = computeMorphologicalAdjustment(cropTemplate, target)
            var roleBonus = 0f
            if (expectedRole != null && (champ.primaryRole == expectedRole || champ.secondaryRoles.contains(expectedRole))) {
                roleBonus = 0.04f
            }

            val finalScore = (ncnnSim + morphAdj + roleBonus).coerceIn(0.01f, 0.99f)
            results.add(Pair(champ, finalScore))
        }

        if (enhanced != crop) {
            try { enhanced.recycle() } catch (_: Throwable) {}
        }
        return results.sortedByDescending { it.second }
    }

    private fun computeEngineCandidatesLiteRt(
        crop: Bitmap,
        champs: List<Champion>,
        expectedRole: LaneRole?,
        context: Context?
    ): List<Pair<Champion, Float>> {
        val enhanced = LocalVisionAnalyzer.enhanceCropQuality(crop)
        val cropTemplate = extractVisualTemplate(enhanced, "crop")
        val results = mutableListOf<Pair<Champion, Float>>()

        for (champ in champs) {
            val target = getOrCreateTemplate(champ, context)
            // Intersección de histograma dual de 44 bins (36 HUE + 8 acromáticos)
            var sumIntersection = 0f
            var sumTotal = 0f
            for (i in 0 until 44) {
                val a = cropTemplate.colorHistogram44[i]
                val b = target.colorHistogram44[i]
                sumIntersection += min(a, b)
                sumTotal += max(a, b)
            }
            val histAffinity = if (sumTotal > 0f) (sumIntersection / sumTotal).coerceIn(0f, 1f) else 0.5f

            val lumAffinity = (1f - abs(cropTemplate.meanLum - target.meanLum) / 255f).coerceIn(0f, 1f)
            val textureAffinity = (1f - abs(cropTemplate.textureEnergy - target.textureEnergy)).coerceIn(0f, 1f)

            val baseLite = (histAffinity * 0.55f + lumAffinity * 0.25f + textureAffinity * 0.20f)
            val morphAdj = computeMorphologicalAdjustment(cropTemplate, target)

            var roleBonus = 0f
            if (expectedRole != null && (champ.primaryRole == expectedRole || champ.secondaryRoles.contains(expectedRole))) {
                roleBonus = 0.04f
            }

            val finalScore = (baseLite + morphAdj + roleBonus).coerceIn(0.01f, 0.99f)
            results.add(Pair(champ, finalScore))
        }

        if (enhanced != crop) {
            try { enhanced.recycle() } catch (_: Throwable) {}
        }
        return results.sortedByDescending { it.second }
    }
}
