package com.example.service.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.example.model.Champion
import com.example.util.AppLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Reconocedor Visual Inteligente para Campeones de Wild Rift.
 * Utilizado primordialmente para el 10º Pick (Último pick del draft)
 * cuando el juego transiciona instantáneamente a la pantalla de carga
 * y el nombre en texto deja de ser legible por OCR.
 *
 * Utiliza una combinación de:
 * 1. Histograma Cromático 3D (64 bins: 4x4x4 RGB) para invariancia posicional.
 * 2. Matriz Espacial de Color 16x16 (256 píxeles muestreados).
 * 3. Filtrado estricto de exclusión (descarta los 9 campeones ya seleccionados).
 */
object ChampionVisualMatcher {
    private const val TAG = "ChampionVisualMatcher"

    data class VisualSignature(
        val histogram: FloatArray, // 64 bins normalizados (suma = 1.0)
        val zoneColors: FloatArray // 9 zonas (3x3) * 3 valores RGB normalizados (0.0..1.0)
    )

    data class VisualMatchResult(
        val champion: Champion,
        val confidence: Float,
        val isConfident: Boolean
    )

    private val signatureCache = ConcurrentHashMap<String, VisualSignature>()
    @Volatile
    private var isPreloaded = false

    /**
     * Precarga firmas visuales en segundo plano para optimizar latencia en tiempo de ejecución.
     */
    suspend fun preloadSignatures(context: Context, champions: List<Champion>) = withContext(Dispatchers.IO) {
        if (isPreloaded && signatureCache.size >= 80) return@withContext
        try {
            for (champ in champions) {
                if (!signatureCache.containsKey(champ.id)) {
                    loadSignature(context, champ.id)
                }
            }
            isPreloaded = true
            AppLogger.d(TAG, "Precargadas ${signatureCache.size} firmas visuales de campeones")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error precargando firmas visuales", e)
        }
    }

    private fun loadSignature(context: Context, champId: String): VisualSignature? {
        signatureCache[champId]?.let { return it }
        return try {
            val assetPath = "champions/$champId.png"
            context.assets.open(assetPath).use { input ->
                val bitmap = BitmapFactory.decodeStream(input) ?: return null
                val signature = computeSignature(bitmap, isCroppedAvatar = false)
                bitmap.recycle()
                if (signature != null) {
                    signatureCache[champId] = signature
                }
                signature
            }
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Calcula la firma visual (histograma cromático + perfil 3x3 de zonas de color) de una imagen.
     * Ambas imágenes (asset del juego y captura de pantalla) se recortan al área central interior (70%)
     * para coincidir exactamente con el contenido facial/corporal inscrito en el círculo,
     * descartando marcos hexagonales, dorados, sombras o fondos oscuros del HUD.
     */
    fun computeSignature(bitmap: Bitmap, isCroppedAvatar: Boolean): VisualSignature? {
        if (bitmap.isRecycled || bitmap.width < 16 || bitmap.height < 16) return null

        val w = bitmap.width
        val h = bitmap.height

        // Tomar el área central (70%) para aislar el círculo del avatar e ignorar marcos o biseles externos
        val marginRatio = if (isCroppedAvatar) 0.16f else 0.12f
        val startX = (w * marginRatio).toInt()
        val endX = (w * (1.0f - marginRatio)).toInt()
        val startY = (h * marginRatio).toInt()
        val endY = (h * (1.0f - marginRatio)).toInt()

        val cropW = (endX - startX).coerceAtLeast(16)
        val cropH = (endY - startY).coerceAtLeast(16)

        val workingBmp = try {
            Bitmap.createBitmap(bitmap, startX, startY, cropW, cropH)
        } catch (_: Exception) {
            return null
        }

        // Redimensionar a 24x24 para análisis estandarizado de zonas 3x3 (cada bloque es de 8x8 px)
        val scaled = try {
            if (workingBmp.width == 24 && workingBmp.height == 24) {
                workingBmp
            } else {
                Bitmap.createScaledBitmap(workingBmp, 24, 24, true)
            }
        } catch (_: Exception) {
            if (workingBmp != bitmap) workingBmp.recycle()
            return null
        }

        if (workingBmp != bitmap && workingBmp != scaled) {
            workingBmp.recycle()
        }

        val totalPixels = 24 * 24 // 576
        val pixels = IntArray(totalPixels)
        scaled.getPixels(pixels, 0, 24, 0, 0, 24, 24)

        if (scaled != bitmap) {
            scaled.recycle()
        }

        // 1. Histograma 4x4x4 = 64 bins cromáticos
        val hist = FloatArray(64)

        // 2. Perfil de 9 Zonas (3x3 bloques de 8x8 píxeles cada uno)
        // Cada zona acumula la media de (R, G, B) normalizada (0.0..1.0)
        val zoneColors = FloatArray(9 * 3)
        val zoneCounts = IntArray(9)

        for (y in 0 until 24) {
            val zoneY = (y / 8).coerceIn(0, 2)
            for (x in 0 until 24) {
                val zoneX = (x / 8).coerceIn(0, 2)
                val zoneIdx = zoneY * 3 + zoneX

                val c = pixels[y * 24 + x]
                val r = Color.red(c)
                val g = Color.green(c)
                val b = Color.blue(c)

                // Binning cromático 4x4x4
                val rBin = (r / 64).coerceIn(0, 3)
                val gBin = (g / 64).coerceIn(0, 3)
                val bBin = (b / 64).coerceIn(0, 3)
                val binIdx = rBin * 16 + gBin * 4 + bBin
                hist[binIdx] += 1.0f

                // Acumular para media de la zona
                zoneColors[zoneIdx * 3] += (r / 255.0f)
                zoneColors[zoneIdx * 3 + 1] += (g / 255.0f)
                zoneColors[zoneIdx * 3 + 2] += (b / 255.0f)
                zoneCounts[zoneIdx]++
            }
        }

        // Normalizar histograma
        for (k in 0 until 64) {
            hist[k] = hist[k] / totalPixels.toFloat()
        }

        // Promediar colores de cada una de las 9 zonas
        for (z in 0 until 9) {
            val cnt = zoneCounts[z].coerceAtLeast(1).toFloat()
            zoneColors[z * 3] /= cnt
            zoneColors[z * 3 + 1] /= cnt
            zoneColors[z * 3 + 2] /= cnt
        }

        return VisualSignature(hist, zoneColors)
    }

    /**
     * Identifica el campeón correspondiente a un recorte de pantalla (ROI de avatar o carga).
     * @param context Contexto de Android para acceso a assets.
     * @param avatarCrop Bitmap recortado de la región del avatar del 10º pick.
     * @param candidates Lista completa de campeones disponibles.
     * @param excludedChampionIds IDs de campeones ya fijados en la partida (9 campeones).
     */
    fun matchChampion(
        context: Context,
        avatarCrop: Bitmap,
        candidates: List<Champion>,
        excludedChampionIds: Set<String> = emptySet()
    ): VisualMatchResult? {
        if (avatarCrop.isRecycled || avatarCrop.width < 16 || avatarCrop.height < 16) return null

        // Verificar si la región tiene contenido visual válido (evitar slots vacíos o negros)
        val w = avatarCrop.width
        val h = avatarCrop.height
        val sampleStepX = (w / 8).coerceAtLeast(1)
        val sampleStepY = (h / 8).coerceAtLeast(1)
        var sumLum = 0L
        var minLum = 255
        var maxLum = 0
        var samples = 0

        for (y in 0 until h step sampleStepY) {
            for (x in 0 until w step sampleStepX) {
                val pixel = avatarCrop.getPixel(x, y)
                val lum = (Color.red(pixel) * 299 + Color.green(pixel) * 587 + Color.blue(pixel) * 114) / 1000
                sumLum += lum
                if (lum < minLum) minLum = lum
                if (lum > maxLum) maxLum = lum
                samples++
            }
        }

        val avgLum = if (samples > 0) sumLum / samples else 0
        val contrast = maxLum - minLum

        // Si la región es casi negra o sin contraste, está vacía
        if (avgLum < 18 || contrast < 20) {
            return null
        }

        val targetSignature = computeSignature(avatarCrop, isCroppedAvatar = true) ?: return null

        var bestChamp: Champion? = null
        var bestScore = 0.0f

        val eligibleCandidates = candidates.filter { it.id !in excludedChampionIds }

        for (champ in eligibleCandidates) {
            val candidateSig = signatureCache[champ.id] ?: loadSignature(context, champ.id)
            if (candidateSig == null) continue

            // 1. Similaridad de Histograma (Intersección normalizada)
            var histSim = 0.0f
            for (i in 0 until 64) {
                histSim += min(targetSignature.histogram[i], candidateSig.histogram[i])
            }

            // 2. Similaridad Espacial de Zonas 3x3 (Invariante a pequeños desplazamientos)
            var zoneDiffSum = 0.0f
            for (z in 0 until 9) {
                val dr = targetSignature.zoneColors[z * 3] - candidateSig.zoneColors[z * 3]
                val dg = targetSignature.zoneColors[z * 3 + 1] - candidateSig.zoneColors[z * 3 + 1]
                val db = targetSignature.zoneColors[z * 3 + 2] - candidateSig.zoneColors[z * 3 + 2]
                zoneDiffSum += sqrt((dr * dr + dg * dg + db * db) / 3.0f)
            }
            val avgZoneDiff = zoneDiffSum / 9.0f
            val zoneSim = (1.0f - avgZoneDiff).coerceIn(0.0f, 1.0f)

            // Puntuación combinada ponderada: 50% distribución de colores, 50% zonas espaciales
            val combinedScore = 0.50f * histSim + 0.50f * zoneSim

            if (combinedScore > bestScore) {
                bestScore = combinedScore
                bestChamp = champ
            }
        }

        if (bestChamp != null && bestScore >= 0.44f) {
            AppLogger.d(TAG, "Match visual detectado: ${bestChamp.name} (Confianza: ${(bestScore * 100).toInt()}%)")
            return VisualMatchResult(
                champion = bestChamp,
                confidence = bestScore,
                isConfident = bestScore >= 0.55f
            )
        }

        return null
    }
}
