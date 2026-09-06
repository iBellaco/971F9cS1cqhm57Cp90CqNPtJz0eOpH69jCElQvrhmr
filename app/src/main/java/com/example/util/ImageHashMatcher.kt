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

    // Recorta el 75% central del avatar para eliminar marcos dorados decorativos
    fun getInnerCrop(bitmap: Bitmap, scale: Float = 0.75f): Bitmap {
        val w = bitmap.width
        val h = bitmap.height
        val targetW = (w * scale).toInt().coerceAtLeast(16)
        val targetH = (h * scale).toInt().coerceAtLeast(16)
        val startX = ((w - targetW) / 2).coerceIn(0, w - targetW)
        val startY = ((h - targetH) / 2).coerceIn(0, h - targetH)
        return Bitmap.createBitmap(bitmap, startX, startY, targetW, targetH)
    }

    // Busca el campeón más similar retornando detalles y porcentaje de confianza con umbral de tolerancia y prioridad por rol
    fun findBestMatchDetailed(
        bitmap: Bitmap,
        allChampions: List<Champion>,
        maxDistance: Int = 22,
        preferredRole: LaneRole? = null
    ): MatchResult? {
        val innerCrop = try { getInnerCrop(bitmap) } catch (e: Exception) { bitmap }
        val targetHash = calculateHash(innerCrop)
        if (innerCrop != bitmap) {
            try { innerCrop.recycle() } catch (ignored: Exception) {}
        }

        var bestMatch: Champion? = null
        var minDistance = maxDistance
        
        allChampions.forEach { champ ->
            val hashes = ChampionHashes.map.filter { it.key == champ.id || it.key.startsWith("${champ.id}_") }.values
            for (champHash in hashes) {
                var dist = hammingDistance(targetHash, champHash)
                
                // Si el slot tiene un rol preferido explícito (ej: TOP para Urgot), aplicar bonificación de distancia
                if (preferredRole != null) {
                    if (champ.primaryRole == preferredRole) {
                        dist -= 4 // Bonificación de rol primario
                    } else if (champ.secondaryRoles.contains(preferredRole)) {
                        dist -= 2 // Bonificación de rol secundario
                    }
                }

                if (dist <= minDistance) {
                    minDistance = dist
                    bestMatch = champ
                }
            }
        }
        
        return bestMatch?.let {
            val confidence = (((64 - minDistance.coerceAtLeast(0)).toFloat() / 64.0f) * 100).toInt().coerceIn(70, 99)
            MatchResult(champion = it, distance = minDistance, confidencePercent = confidence)
        }
    }

    // Busca el campeón más similar
    fun findBestMatch(bitmap: Bitmap, allChampions: List<Champion>, preferredRole: LaneRole? = null): Champion? {
        return findBestMatchDetailed(bitmap, allChampions, preferredRole = preferredRole)?.champion
    }
}

