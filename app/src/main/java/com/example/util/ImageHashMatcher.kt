package com.example.util

import android.graphics.Bitmap
import android.graphics.Color
import com.example.model.Champion
import com.example.model.LaneRole

data class RoleMatchResult(
    val role: LaneRole,
    val distance: Int,
    val confidence: Float
)

data class MatchResult(
    val champion: Champion,
    val distance: Int,
    val confidencePercent: Int
)

object ImageHashMatcher {
    
    // Calcula el aHash (Average Hash) de 64 bits para un Bitmap de recorte
    fun calculateHash(bitmap: Bitmap): Long {
        val scaled = Bitmap.createScaledBitmap(bitmap, 8, 8, true)
        val pixels = IntArray(64)
        scaled.getPixels(pixels, 0, 8, 0, 0, 8, 8)
        
        var totalLuminance = 0L
        val luminances = IntArray(64)
        
        for (i in 0 until 64) {
            val color = pixels[i]
            val r = Color.red(color)
            val g = Color.green(color)
            val b = Color.blue(color)
            // Luminancia aproximada estándar
            val luminance = (r * 0.299 + g * 0.587 + b * 0.114).toInt()
            luminances[i] = luminance
            totalLuminance += luminance
        }
        
        val avgLuminance = totalLuminance / 64
        var hash = 0L
        
        for (i in 0 until 64) {
            if (luminances[i] >= avgLuminance) {
                hash = hash or (1L shl (63 - i))
            }
        }
        
        scaled.recycle()
        return hash
    }
    
    // Calcula el hash y busca la mejor coincidencia para un icono de rol con métricas de confianza
    fun findRoleMatchDetailed(bitmap: Bitmap, maxDistance: Int = 26): RoleMatchResult? {
        val targetHash = calculateHash(bitmap)
        var bestRole: LaneRole? = null
        var minDistance = maxDistance
        
        RoleHashes.map.forEach { (role, hashList) ->
            hashList.forEach { hash ->
                val dist = hammingDistance(targetHash, hash)
                if (dist < minDistance) {
                    minDistance = dist
                    bestRole = role
                }
            }
        }
        
        return bestRole?.let {
            val confidence = (64 - minDistance) / 64.0f
            RoleMatchResult(role = it, distance = minDistance, confidence = confidence)
        }
    }

    // Retorna el LaneRole directamente
    fun findRoleMatch(bitmap: Bitmap): LaneRole? {
        return findRoleMatchDetailed(bitmap)?.role
    }

    // Distancia de Hamming
    fun hammingDistance(hash1: Long, hash2: Long): Int {
        return java.lang.Long.bitCount(hash1 xor hash2)
    }

    // Calcula el dHash (Difference Hash) de 64 bits para un Bitmap de recorte
    fun calculateDHash(bitmap: Bitmap): Long {
        val scaled = Bitmap.createScaledBitmap(bitmap, 9, 8, true)
        val pixels = IntArray(72)
        scaled.getPixels(pixels, 0, 9, 0, 0, 9, 8)
        
        var hash = 0L
        var bitIndex = 0
        for (y in 0 until 8) {
            for (x in 0 until 8) {
                val left = pixels[y * 9 + x]
                val right = pixels[y * 9 + (x + 1)]
                val leftLum = (Color.red(left) * 299 + Color.green(left) * 587 + Color.blue(left) * 114) / 1000
                val rightLum = (Color.red(right) * 299 + Color.green(right) * 587 + Color.blue(right) * 114) / 1000
                if (leftLum > rightLum) {
                    hash = hash or (1L shl (63 - bitIndex))
                }
                bitIndex++
            }
        }
        scaled.recycle()
        return hash
    }

    // Recorta un porcentaje central del avatar para eliminar marcos decorativos o anillos de selección
    fun getInnerCrop(bitmap: Bitmap, scale: Float = 0.75f): Bitmap {
        val w = bitmap.width
        val h = bitmap.height
        val targetW = (w * scale).toInt().coerceAtLeast(8)
        val targetH = (h * scale).toInt().coerceAtLeast(8)
        val startX = ((w - targetW) / 2).coerceIn(0, w - targetW)
        val startY = ((h - targetH) / 2).coerceIn(0, h - targetH)
        return Bitmap.createBitmap(bitmap, startX, startY, targetW, targetH)
    }

    // Busca el campeón más similar retornando detalles y porcentaje de confianza con umbral de tolerancia y prioridad por rol
    fun findBestMatchDetailed(
        bitmap: Bitmap,
        allChampions: List<Champion>,
        maxDistance: Int = 26,
        preferredRole: LaneRole? = null
    ): MatchResult? {
        val candidateHashes = mutableListOf<Long>()
        
        // 1. Hash de la imagen completa
        candidateHashes.add(calculateHash(bitmap))
        
        // 2. Hash con normalización de iluminación (Auto-levels / Min-Max stretching)
        try {
            val normBitmap = ImagePreprocessor.normalizeLighting(bitmap)
            candidateHashes.add(calculateHash(normBitmap))
            if (normBitmap != bitmap) normBitmap.recycle()
        } catch (ignored: Exception) {}

        // 3. Hash con ecualización de histograma (resuelve fondos oscuros / resplandores intensos)
        try {
            val eqBitmap = ImagePreprocessor.equalizeHistogram(bitmap)
            candidateHashes.add(calculateHash(eqBitmap))
            if (eqBitmap != bitmap) eqBitmap.recycle()
        } catch (ignored: Exception) {}

        // 4. Hash con recorte al 85% (elimina bordes mínimos)
        try {
            val crop85 = getInnerCrop(bitmap, 0.85f)
            candidateHashes.add(calculateHash(crop85))
            val normCrop85 = ImagePreprocessor.normalizeLighting(crop85)
            candidateHashes.add(calculateHash(normCrop85))
            if (normCrop85 != crop85) normCrop85.recycle()
            if (crop85 != bitmap) crop85.recycle()
        } catch (ignored: Exception) {}

        // 5. Hash con recorte al 70% (elimina anillos dorados o auras rojas de selección activa)
        try {
            val crop70 = getInnerCrop(bitmap, 0.70f)
            candidateHashes.add(calculateHash(crop70))
            val normCrop70 = ImagePreprocessor.normalizeLighting(crop70)
            candidateHashes.add(calculateHash(normCrop70))
            if (normCrop70 != crop70) normCrop70.recycle()
            if (crop70 != bitmap) crop70.recycle()
        } catch (ignored: Exception) {}

        // 6. Hash con recorte al 55% (enfoque directo al rostro / elemento central)
        try {
            val crop55 = getInnerCrop(bitmap, 0.55f)
            candidateHashes.add(calculateHash(crop55))
            val normCrop55 = ImagePreprocessor.normalizeLighting(crop55)
            candidateHashes.add(calculateHash(normCrop55))
            if (normCrop55 != crop55) normCrop55.recycle()
            if (crop55 != bitmap) crop55.recycle()
        } catch (ignored: Exception) {}

        var bestMatch: Champion? = null
        var minEffectiveDistance = maxDistance
        var bestRawDistance = 64
        
        allChampions.forEach { champ ->
            val hashes = ChampionHashes.map.filter { it.key == champ.id || it.key.startsWith("${champ.id}_") }.values
            
            for (champHash in hashes) {
                for (targetHash in candidateHashes) {
                    val rawDist = hammingDistance(targetHash, champHash)
                    var effectiveDist = rawDist
                    
                    // Si el slot tiene un rol preferido explícito (ej: TOP para Urgot, SUP para Lux), aplicar bonificación de distancia
                    if (preferredRole != null) {
                        if (champ.primaryRole == preferredRole) {
                            effectiveDist -= 5 // Bonificación por rol primario
                        } else if (champ.secondaryRoles.contains(preferredRole)) {
                            effectiveDist -= 3 // Bonificación por rol secundario
                        }
                    }

                    if (effectiveDist < minEffectiveDistance) {
                        minEffectiveDistance = effectiveDist
                        bestRawDistance = rawDist
                        bestMatch = champ
                    }
                }
            }
        }
        
        return bestMatch?.let {
            val confidence = (((64 - bestRawDistance.coerceAtLeast(0)).toFloat() / 64.0f) * 100).toInt().coerceIn(65, 99)
            MatchResult(champion = it, distance = minEffectiveDistance, confidencePercent = confidence)
        }
    }

    // Busca el campeón más similar
    fun findBestMatch(bitmap: Bitmap, allChampions: List<Champion>, preferredRole: LaneRole? = null): Champion? {
        return findBestMatchDetailed(bitmap, allChampions, preferredRole = preferredRole)?.champion
    }
}

