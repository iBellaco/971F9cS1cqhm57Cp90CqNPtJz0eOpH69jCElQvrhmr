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
        if (isInitialized && cachedSignatures.isNotEmpty()) return

        val ctx = context ?: WildRiftApp.instance ?: return
        try {
            val allChamps = WildRiftRepository.champions
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

            // Ponderación contextual según el rol esperado para el slot
            if (expectedRole != null) {
                if (champ.primaryRole == expectedRole) {
                    compositeScore += 0.08f
                } else if (champ.secondaryRoles.contains(expectedRole)) {
                    compositeScore += 0.04f
                }
            }

            if (compositeScore > bestScore) {
                bestScore = compositeScore
                bestChamp = champ
            }
        }

        if (bestChamp != null && bestScore >= 0.50f) {
            AppLogger.d(TAG, "Coincidencia local para avatar: ${bestChamp.name} (Confianza: ${(bestScore * 100).toInt()}%)")
            return Pair(bestChamp, bestScore)
        }

        return null
    }

    /**
     * Identificación visual del 10º Pick mediante análisis dual:
     * - Recorte del Slot Vertical #5 (lado izquierdo para aliado, derecho para rival)
     * - Recorte del Avatar #5 de la Barra Superior
     * Selecciona el campeón con mayor confianza de forma 100% offline.
     */
    suspend fun identify10thPickLocal(
        bitmap: Bitmap,
        isAlly: Boolean,
        calib: VisionCalibrationConfig,
        allChamps: List<Champion>,
        confirmedIds: Set<String>,
        expectedRole: LaneRole?,
        context: Context? = null
    ): Pair<Champion, Float>? = withContext(Dispatchers.Default) {
        if (bitmap.isRecycled) return@withContext null

        val width = bitmap.width
        val height = bitmap.height

        // 1. Coordenadas del Slot Vertical #5
        val slotAvatarDiam = (height * calib.avatarDiameterRatio).toInt().coerceAtLeast(32)
        val slotCenterX = if (!isAlly) {
            (width * calib.enemyAvatarCenterX).toInt()
        } else {
            (width * calib.allyAvatarCenterX).toInt()
        }
        val slotCenterY = if (!isAlly) {
            (height * calib.enemySlotYRatios[4]).toInt()
        } else {
            (height * calib.allySlotYRatios[4]).toInt()
        }

        // 2. Coordenadas del Avatar #5 Superior
        val topAvatarDiam = (height * calib.topAvatarDiameterRatio).toInt().coerceAtLeast(24)
        val topCenterX = if (!isAlly) {
            (width * calib.topEnemy5XRatio).toInt()
        } else {
            (width * calib.topAlly5XRatio).toInt()
        }
        val topCenterY = (height * calib.topAvatarYRatio).toInt()

        // Extraer recortes
        val slotCrop = safeCrop(bitmap, slotCenterX, slotCenterY, slotAvatarDiam)
        val topCrop = safeCrop(bitmap, topCenterX, topCenterY, topAvatarDiam)

        var bestMatchSlot: Pair<Champion, Float>? = null
        var bestMatchTop: Pair<Champion, Float>? = null

        try {
            if (slotCrop != null) {
                bestMatchSlot = matchAvatar(slotCrop, allChamps, expectedRole, confirmedIds, context)
            }
            if (topCrop != null) {
                bestMatchTop = matchAvatar(topCrop, allChamps, expectedRole, confirmedIds, context)
            }
        } finally {
            try { slotCrop?.recycle() } catch (_: Throwable) {}
            try { topCrop?.recycle() } catch (_: Throwable) {}
        }

        val candidates = listOfNotNull(bestMatchSlot, bestMatchTop)
        if (candidates.isEmpty()) {
            return@withContext null
        }

        // Si ambos coinciden en el mismo campeón, potenciar la confianza
        if (bestMatchSlot != null && bestMatchTop != null && bestMatchSlot.first.id == bestMatchTop.first.id) {
            val combinedConfidence = min(0.99f, max(bestMatchSlot.second, bestMatchTop.second) + 0.08f)
            AppLogger.d(TAG, "10º Pick confirmado dualmente por Slot y Top Bar: ${bestMatchSlot.first.name} ($combinedConfidence)")
            return@withContext Pair(bestMatchSlot.first, combinedConfidence)
        }

        return@withContext candidates.maxByOrNull { it.second }
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
