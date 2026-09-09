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
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Reconocedor Visual Inteligente para Campeones de Wild Rift por Similitud de Avatares.
 * Compara recortes de pantalla del draft/pantalla de carga contra los avatares locales de campeones
 * utilizando:
 * 1. Enmascaramiento circular para ignorar bordes y fondos de la interfaz.
 * 2. Matriz Espacial de Color 4x4 (16 zonas).
 * 3. Histograma Cromático 3D (64 bins: 4x4x4 RGB).
 * 4. Ponderación de similitud máxima relativa (Best Match) excluyendo campeones ya seleccionados.
 */
object ChampionVisualMatcher {
    private const val TAG = "ChampionVisualMatcher"

    data class VisualSignature(
        val histogram: FloatArray,   // 64 bins normalizados (suma = 1.0)
        val zoneColors: FloatArray,  // 16 zonas (4x4) * 3 valores RGB normalizados (0.0..1.0)
        val avgR: Float,             // Color promedio normalizado
        val avgG: Float,
        val avgB: Float
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
     * Almacena en memoria las 141 firmas visuales calculadas a partir de los avatares locales.
     */
    suspend fun preloadSignatures(context: Context, champions: List<Champion>) = withContext(Dispatchers.IO) {
        if (isPreloaded && signatureCache.size >= champions.size && champions.isNotEmpty()) return@withContext
        try {
            for (champ in champions) {
                if (!signatureCache.containsKey(champ.id)) {
                    loadSignature(context, champ.id)
                }
            }
            isPreloaded = true
            AppLogger.d(TAG, "Precargadas ${signatureCache.size}/${champions.size} firmas visuales locales de campeones")
        } catch (e: Exception) {
            AppLogger.e(TAG, "Error precargando firmas visuales", e)
        }
    }

    private fun loadSignature(context: Context, champId: String): VisualSignature? {
        signatureCache[champId]?.let { return it }

        // Lista de posibles rutas de asset para asegurar compatibilidad con todos los nombres de archivo
        val normalizedId = champId.lowercase().trim()
        val candidatePaths = mutableListOf(
            "champions/$normalizedId.png",
            "champions/${normalizedId.replace("-", "_")}.png",
            "champions/${normalizedId.replace("_", "")}.png",
            "avatars/$normalizedId.png",
            "avatars/${normalizedId.replace("-", "_")}.png"
        )

        // Aliases específicos para nombres con variantes en assets
        when (normalizedId) {
            "nunu_and_willump", "nunuandwillump" -> candidatePaths.add(0, "champions/nunu_willump.png")
            "wukong", "monkeyking" -> {
                candidatePaths.add(0, "champions/wukong.png")
                candidatePaths.add(1, "champions/monkey_king.png")
            }
            "swain", "sw_ain" -> {
                candidatePaths.add(0, "champions/swain.png")
                candidatePaths.add(1, "champions/sw_ain.png")
            }
            "drmundo", "dr_mundo" -> {
                candidatePaths.add(0, "champions/dr_mundo.png")
                candidatePaths.add(1, "champions/drmundo.png")
            }
            "jarvaniv", "jarvan_iv" -> {
                candidatePaths.add(0, "champions/jarvan_iv.png")
                candidatePaths.add(1, "champions/jarvaniv.png")
            }
            "missfortune", "miss_fortune" -> {
                candidatePaths.add(0, "champions/miss_fortune.png")
                candidatePaths.add(1, "champions/missfortune.png")
            }
            "twistedfate", "twisted_fate" -> {
                candidatePaths.add(0, "champions/twisted_fate.png")
                candidatePaths.add(1, "champions/twistedfate.png")
            }
            "xinzhao", "xin_zhao" -> {
                candidatePaths.add(0, "champions/xin_zhao.png")
                candidatePaths.add(1, "champions/xinzhao.png")
            }
            "aurelionsol", "aurelion_sol" -> {
                candidatePaths.add(0, "champions/aurelion_sol.png")
                candidatePaths.add(1, "champions/aurelionsol.png")
            }
            "ksante", "k_sante" -> {
                candidatePaths.add(0, "champions/k_sante.png")
                candidatePaths.add(1, "champions/ksante.png")
            }
            "kaisa", "kai_sa" -> {
                candidatePaths.add(0, "champions/kai_sa.png")
                candidatePaths.add(1, "champions/kaisa.png")
            }
            "chogath", "cho_gath" -> {
                candidatePaths.add(0, "champions/cho_gath.png")
                candidatePaths.add(1, "champions/chogath.png")
            }
            "velkoz", "vel_koz" -> {
                candidatePaths.add(0, "champions/vel_koz.png")
                candidatePaths.add(1, "champions/velkoz.png")
            }
            "kogmaw", "kog_maw" -> {
                candidatePaths.add(0, "champions/kog_maw.png")
                candidatePaths.add(1, "champions/kogmaw.png")
            }
        }

        for (path in candidatePaths.distinct()) {
            try {
                context.assets.open(path).use { input ->
                    val bitmap = BitmapFactory.decodeStream(input)
                    if (bitmap != null) {
                        val signature = computeSignature(bitmap, isCroppedAvatar = false)
                        bitmap.recycle()
                        if (signature != null) {
                            signatureCache[champId] = signature
                            return signature
                        }
                    }
                }
            } catch (_: Exception) {
                // Probar siguiente ruta candidata
            }
        }
        return null
    }

    /**
     * Calcula la firma visual utilizando un enmascaramiento circular centrado
     * para aislar el retrato del campeón de cualquier borde o fondo cuadrado.
     */
    fun computeSignature(bitmap: Bitmap, isCroppedAvatar: Boolean): VisualSignature? {
        if (bitmap.isRecycled || bitmap.width < 12 || bitmap.height < 12) return null

        val w = bitmap.width
        val h = bitmap.height

        // Margen de seguridad: 14% para recortes de pantalla, 10% para avatares directos
        val marginRatio = if (isCroppedAvatar) 0.14f else 0.10f
        val startX = (w * marginRatio).toInt()
        val endX = (w * (1.0f - marginRatio)).toInt()
        val startY = (h * marginRatio).toInt()
        val endY = (h * (1.0f - marginRatio)).toInt()

        val cropW = (endX - startX).coerceAtLeast(12)
        val cropH = (endY - startY).coerceAtLeast(12)

        val workingBmp = try {
            Bitmap.createBitmap(bitmap, startX, startY, cropW, cropH)
        } catch (_: Exception) {
            return null
        }

        // Redimensionar a 32x32 para muestreo estandarizado en 4x4 zonas (cada zona es de 8x8 px)
        val scaled = try {
            if (workingBmp.width == 32 && workingBmp.height == 32) {
                workingBmp
            } else {
                Bitmap.createScaledBitmap(workingBmp, 32, 32, true)
            }
        } catch (_: Exception) {
            if (workingBmp != bitmap) workingBmp.recycle()
            return null
        }

        if (workingBmp != bitmap && workingBmp != scaled) {
            workingBmp.recycle()
        }

        val totalPixels = 32 * 32 // 1024 píxeles
        val pixels = IntArray(totalPixels)
        scaled.getPixels(pixels, 0, 32, 0, 0, 32, 32)

        if (scaled != bitmap) {
            scaled.recycle()
        }

        val hist = FloatArray(64)
        val zoneColors = FloatArray(16 * 3) // 4x4 = 16 zonas
        val zoneCounts = IntArray(16)

        var totalR = 0.0f
        var totalG = 0.0f
        var totalB = 0.0f
        var validMaskPixels = 0

        val center = 15.5f
        val maxRadius = 15.0f

        for (y in 0 until 32) {
            val dy = y - center
            val zoneY = (y / 8).coerceIn(0, 3)

            for (x in 0 until 32) {
                val dx = x - center
                val dist = sqrt(dx * dx + dy * dy)

                // Enmascaramiento circular: ignorar esquinas fuera del radio del avatar
                if (dist > maxRadius) continue

                val zoneX = (x / 8).coerceIn(0, 3)
                val zoneIdx = zoneY * 4 + zoneX

                val c = pixels[y * 32 + x]
                val r = Color.red(c)
                val g = Color.green(c)
                val b = Color.blue(c)

                // Binning cromático 4x4x4 (64 bins)
                val rBin = (r / 64).coerceIn(0, 3)
                val gBin = (g / 64).coerceIn(0, 3)
                val bBin = (b / 64).coerceIn(0, 3)
                val binIdx = rBin * 16 + gBin * 4 + bBin
                hist[binIdx] += 1.0f

                val normR = r / 255.0f
                val normG = g / 255.0f
                val normB = b / 255.0f

                zoneColors[zoneIdx * 3] += normR
                zoneColors[zoneIdx * 3 + 1] += normG
                zoneColors[zoneIdx * 3 + 2] += normB
                zoneCounts[zoneIdx]++

                totalR += normR
                totalG += normG
                totalB += normB
                validMaskPixels++
            }
        }

        if (validMaskPixels == 0) return null

        // Normalizar histograma
        val normFactor = validMaskPixels.toFloat()
        for (k in 0 until 64) {
            hist[k] = hist[k] / normFactor
        }

        // Promediar colores de cada zona espacial
        for (z in 0 until 16) {
            val cnt = zoneCounts[z].coerceAtLeast(1).toFloat()
            zoneColors[z * 3] /= cnt
            zoneColors[z * 3 + 1] /= cnt
            zoneColors[z * 3 + 2] /= cnt
        }

        return VisualSignature(
            histogram = hist,
            zoneColors = zoneColors,
            avgR = totalR / normFactor,
            avgG = totalG / normFactor,
            avgB = totalB / normFactor
        )
    }

    /**
     * Comprueba si el recorte contiene un retrato de campeón real o si es un slot vacío,
     * ícono de línea geométrico, fondo plano o casco de marcador de posición.
     */
    fun isRealChampionPortrait(bitmap: Bitmap): Boolean {
        if (bitmap.isRecycled || bitmap.width < 12 || bitmap.height < 12) return false

        val w = bitmap.width
        val h = bitmap.height
        val sampleStepX = (w / 10).coerceAtLeast(1)
        val sampleStepY = (h / 10).coerceAtLeast(1)

        var sumR = 0L
        var sumG = 0L
        var sumB = 0L
        var sumLum = 0L
        var minLum = 255
        var maxLum = 0
        var samples = 0
        var colorfulPixels = 0

        val rList = mutableListOf<Int>()
        val gList = mutableListOf<Int>()
        val bList = mutableListOf<Int>()

        for (y in (h * 0.15f).toInt() until (h * 0.85f).toInt() step sampleStepY) {
            for (x in (w * 0.15f).toInt() until (w * 0.85f).toInt() step sampleStepX) {
                val pixel = bitmap.getPixel(x, y)
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)

                rList.add(r)
                gList.add(g)
                bList.add(b)

                sumR += r
                sumG += g
                sumB += b

                val maxC = max(r, max(g, b))
                val minC = min(r, min(g, b))
                val sat = if (maxC > 0) (maxC - minC).toFloat() / maxC.toFloat() else 0f
                if (sat > 0.18f && maxC > 35) {
                    colorfulPixels++
                }

                val lum = (r * 299 + g * 587 + b * 114) / 1000
                sumLum += lum
                if (lum < minLum) minLum = lum
                if (lum > maxLum) maxLum = lum
                samples++
            }
        }

        if (samples < 10) return false

        val avgLum = sumLum / samples
        val contrast = maxLum - minLum
        val colorfulRatio = colorfulPixels.toFloat() / samples.toFloat()

        // Calcular desviación estándar de luminosidad (textura visual)
        var sumSqDiff = 0.0
        for (i in 0 until samples) {
            val lum = (rList[i] * 299 + gList[i] * 587 + bList[i] * 114) / 1000
            val diff = lum - avgLum
            sumSqDiff += diff * diff
        }
        val stdDev = sqrt(sumSqDiff / samples)

        // Un slot vacío, ícono de línea o casco gris tiene muy baja varianza de color o contraste plano
        if (avgLum < 18 || contrast < 28 || stdDev < 12.0) {
            return false
        }

        // Si casi no hay píxeles de color (casco gris / icono plano monocromo), no es un campeón
        if (colorfulRatio < 0.12f && stdDev < 20.0) {
            return false
        }

        return true
    }

    /**
     * Identifica el campeón con mayor similitud visual comparando contra las imágenes de avatares disponibles.
     * Retorna el campeón SOLO si supera el umbral de confianza estricto y no es un slot vacío.
     */
    fun matchChampion(
        context: Context,
        avatarCrop: Bitmap,
        candidates: List<Champion>,
        excludedChampionIds: Set<String> = emptySet(),
        minConfidenceThreshold: Float = 0.50f
    ): VisualMatchResult? {
        if (avatarCrop.isRecycled || avatarCrop.width < 12 || avatarCrop.height < 12) return null

        // Comprobación anti-falsos positivos: ¿Es realmente un retrato de campeón?
        if (!isRealChampionPortrait(avatarCrop)) {
            return null
        }

        val targetSignature = computeSignature(avatarCrop, isCroppedAvatar = true) ?: return null

        var bestChamp: Champion? = null
        var bestScore = -1.0f
        var secondBestScore = -1.0f

        val eligibleCandidates = candidates.filter { it.id !in excludedChampionIds }
        if (eligibleCandidates.isEmpty()) return null

        for (champ in eligibleCandidates) {
            val candidateSig = signatureCache[champ.id] ?: loadSignature(context, champ.id)
            if (candidateSig == null) continue

            // 1. Similitud de Histograma (Intersección normalizada de 64 bins)
            var histSim = 0.0f
            for (i in 0 until 64) {
                histSim += min(targetSignature.histogram[i], candidateSig.histogram[i])
            }

            // 2. Similitud Espacial 4x4 (16 zonas)
            var zoneDiffSum = 0.0f
            for (z in 0 until 16) {
                val dr = targetSignature.zoneColors[z * 3] - candidateSig.zoneColors[z * 3]
                val dg = targetSignature.zoneColors[z * 3 + 1] - candidateSig.zoneColors[z * 3 + 1]
                val db = targetSignature.zoneColors[z * 3 + 2] - candidateSig.zoneColors[z * 3 + 2]
                zoneDiffSum += sqrt((dr * dr + dg * dg + db * db) / 3.0f)
            }
            val avgZoneDiff = zoneDiffSum / 16.0f
            val zoneSim = (1.0f - avgZoneDiff).coerceIn(0.0f, 1.0f)

            // 3. Similitud de Color Global Promedio
            val drAvg = targetSignature.avgR - candidateSig.avgR
            val dgAvg = targetSignature.avgG - candidateSig.avgG
            val dbAvg = targetSignature.avgB - candidateSig.avgB
            val dominantColorSim = (1.0f - sqrt((drAvg * drAvg + dgAvg * dgAvg + dbAvg * dbAvg) / 3.0f)).coerceIn(0.0f, 1.0f)

            // Puntuación combinada de similitud relativa (45% Histograma + 45% Estructura Espacial + 10% Tono Global)
            val combinedScore = 0.45f * histSim + 0.45f * zoneSim + 0.10f * dominantColorSim

            if (combinedScore > bestScore) {
                secondBestScore = bestScore
                bestScore = combinedScore
                bestChamp = champ
            } else if (combinedScore > secondBestScore) {
                secondBestScore = combinedScore
            }
        }

        // Se requiere superar el umbral mínimo estricto para evitar emparejamientos espurios
        if (bestChamp != null && bestScore >= minConfidenceThreshold) {
            val margin = bestScore - secondBestScore
            val isConfident = bestScore >= 0.58f && (secondBestScore < 0 || margin >= 0.03f)
            AppLogger.d(TAG, "Similitud visual detectada: ${bestChamp.name} (Puntuación: ${(bestScore * 100).toInt()}%, Margen: ${(margin * 100).toInt()}%)")
            return VisualMatchResult(
                champion = bestChamp,
                confidence = bestScore,
                isConfident = isConfident
            )
        }

        return null
    }
}
