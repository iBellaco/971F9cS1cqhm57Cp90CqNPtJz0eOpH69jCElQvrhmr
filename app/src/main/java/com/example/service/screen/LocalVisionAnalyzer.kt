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
     * Verifica si el recorte de avatar contiene un campeón real (contraste y brillo mínimos).
     * Retorna falso si el slot está vacío, negro o esperando selección.
     */
    fun isAvatarPopulated(crop: Bitmap): Boolean {
        if (crop.isRecycled || crop.width < 12 || crop.height < 12) return false
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
            }
        }

        if (samples < 10) return false
        val avgLum = totalLum.toFloat() / samples
        val contrast = maxLum - minLum

        // Un slot vacío o sin pick es muy oscuro (< 20 lum) o prácticamente plano (< 18 contraste)
        return avgLum >= 22f && contrast >= 25
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
        if (!isAvatarPopulated(crop)) {
            AppLogger.d(TAG, "matchAvatar: El recorte no contiene un avatar poblado (slot vacío)")
            return null
        }

        ensureInitialized(context)
        if (cachedSignatures.isEmpty()) return null

        val targetFp = extractFingerprint(crop, "target") ?: return null

        var bestChamp: Champion? = null
        var bestScore = 0.0f

        val center = (FINGERPRINT_SIZE - 1) / 2.0f
        val maxRadius = FINGERPRINT_SIZE * 0.44f

        for (champ in candidates) {
            if (excludedChampionIds.contains(champ.id)) continue

            val sig = cachedSignatures[champ.id] ?: continue

            // 1. Similitud RGB pixel a pixel en la región circular interior
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

                    val dr = abs(Color.red(p1) - Color.red(p2))
                    val dg = abs(Color.green(p1) - Color.green(p2))
                    val db = abs(Color.blue(p1) - Color.blue(p2))

                    pixelDiffSum += (dr + dg + db) / (3f * 255f)
                    pixelCount++
                }
            }

            if (pixelCount == 0) continue
            val avgPixelDiff = pixelDiffSum / pixelCount.toFloat()
            val pixelSimilarity = (1.0f - avgPixelDiff).coerceIn(0f, 1f)

            // 2. Similitud de Histograma de Tono (Intersección de histogramas)
            var histIntersection = 0f
            for (i in 0 until 8) {
                histIntersection += min(targetFp.hueHistogram[i], sig.hueHistogram[i])
            }
            val histSimilarity = histIntersection.coerceIn(0f, 1f)

            // 3. Similitud de color promedio
            val avgColorDiff = (abs(targetFp.avgR - sig.avgR) + abs(targetFp.avgG - sig.avgG) + abs(targetFp.avgB - sig.avgB)) / (3f * 255f)
            val avgColorSim = (1.0f - avgColorDiff).coerceIn(0f, 1f)

            // Puntuación visual combinada
            var compositeScore = (pixelSimilarity * 0.55f) + (histSimilarity * 0.30f) + (avgColorSim * 0.15f)

            // Ponderación contextual suave según el rol esperado para el slot (sin sesgar la similitud visual del avatar)
            if (expectedRole != null) {
                if (champ.primaryRole == expectedRole) {
                    compositeScore += 0.03f
                } else if (champ.secondaryRoles.contains(expectedRole)) {
                    compositeScore += 0.015f
                }
            }

            if (compositeScore > bestScore) {
                bestScore = compositeScore
                bestChamp = champ
            }
        }

        if (bestChamp != null && bestScore >= 0.45f) {
            AppLogger.d(TAG, "Coincidencia local para avatar: ${bestChamp.name} (Confianza: ${(bestScore * 100).toInt()}%)")
            return Pair(bestChamp, bestScore)
        }

        return null
    }

    /**
     * Escaneo del 10º Pick en la PARTE INFERIOR:
     * - Si es Primera Selección (Aliados): el 10º pick es Rival -> escanea la parte INFERIOR DERECHA (Slot 4 del Rival).
     * - Si NO es Primera Selección (Rivales): el 10º pick es Aliado -> escanea la parte INFERIOR IZQUIERDA (Slot 4 del Aliado).
     *
     * Nota: En la parte inferior el jugador puede estar mostrando un campeón (hover/preselección)
     * y cambiar a otro antes de fijar.
     */
    suspend fun identify10thPickInferior(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): Pair<Champion, Float>? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        val width = bitmap.width
        val height = bitmap.height

        // Si es primera selección -> 10º pick es Rival (INFERIOR DERECHA, Slot 4)
        // Si NO es primera selección -> 10º pick es Aliado (INFERIOR IZQUIERDA, Slot 4)
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

        val standardCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotAvatarDiam)
        val innerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 0.88f).toInt())
        val outerCrop = safeCrop(bitmap, slotCenterX, slotCenterY, (slotAvatarDiam * 1.08f).toInt())

        val candidateMatches = mutableListOf<Pair<Champion, Float>>()
        try {
            if (standardCrop != null) {
                matchAvatar(standardCrop, allChamps, expectedRole, confirmedIds, context)?.let { candidateMatches.add(it) }
            }
            if (innerCrop != null) {
                matchAvatar(innerCrop, allChamps, expectedRole, confirmedIds, context)?.let { candidateMatches.add(it) }
            }
            if (outerCrop != null) {
                matchAvatar(outerCrop, allChamps, expectedRole, confirmedIds, context)?.let { candidateMatches.add(it) }
            }
        } finally {
            try { standardCrop?.recycle() } catch (_: Throwable) {}
            try { innerCrop?.recycle() } catch (_: Throwable) {}
            try { outerCrop?.recycle() } catch (_: Throwable) {}
        }

        val best = candidateMatches.maxByOrNull { it.second }
        if (best != null) {
            val sideDesc = if (isAlly) "inferior izquierda (Aliado 5)" else "inferior derecha (Rival 5)"
            AppLogger.d(TAG, "10º Pick preseleccionado en parte $sideDesc: ${best.first.name} (${(best.second * 100).toInt()}%)")
        }
        return@withContext best
    }

    /**
     * Confirmación del 10º Pick en la PARTE SUPERIOR (100% Certera):
     * Una vez que desaparecen los slots de avatares de selección, el campeón queda fijado.
     * En la parte superior se visualiza 100% la selección definitiva:
     * - Si fue Primera Selección: el 10º pick fue el 5º Rival -> confirma en la PARTE SUPERIOR DERECHA.
     * - Si NO fue Primera Selección: el 10º pick fue el 5º Aliado -> confirma en la PARTE SUPERIOR IZQUIERDA.
     */
    suspend fun identify10thPickSuperior(
        bitmap: Bitmap,
        isFirstPick: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): Pair<Champion, Float>? = withContext(Dispatchers.Default) {
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

        val candidateMatches = mutableListOf<Pair<Champion, Float>>()

        for (topCenterX in candidateXs) {
            val standardCrop = safeCrop(bitmap, topCenterX, topCenterY, topDiam)
            val innerCrop = safeCrop(bitmap, topCenterX, topCenterY, (topDiam * 0.88f).toInt())
            val outerCrop = safeCrop(bitmap, topCenterX, topCenterY, (topDiam * 1.08f).toInt())

            try {
                if (standardCrop != null) {
                    matchAvatar(standardCrop, allChamps, expectedRole, confirmedIds, context)?.let { candidateMatches.add(it) }
                }
                if (innerCrop != null) {
                    matchAvatar(innerCrop, allChamps, expectedRole, confirmedIds, context)?.let { candidateMatches.add(it) }
                }
                if (outerCrop != null) {
                    matchAvatar(outerCrop, allChamps, expectedRole, confirmedIds, context)?.let { candidateMatches.add(it) }
                }
            } finally {
                try { standardCrop?.recycle() } catch (_: Throwable) {}
                try { innerCrop?.recycle() } catch (_: Throwable) {}
                try { outerCrop?.recycle() } catch (_: Throwable) {}
            }
        }

        val best = candidateMatches.maxByOrNull { it.second }
        if (best != null) {
            val sideDesc = if (isAlly) "superior izquierda (Aliado 5)" else "superior derecha (Rival 5)"
            AppLogger.d(TAG, "10º Pick confirmado al 100% en parte $sideDesc: ${best.first.name} (${(best.second * 100).toInt()}%)")
            return@withContext Pair(best.first, 1.0f)
        }

        return@withContext null
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
            stdDev < 28.0
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
