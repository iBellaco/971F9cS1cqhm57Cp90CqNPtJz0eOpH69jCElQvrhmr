package com.example.util

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Rect
import com.example.data.WildRiftSpellsAndRunes

/**
 * Detector y Clasificador de Hechizos de Invocador (Summoner Spells) de Wild Rift.
 * Analiza la firma cromática y espectral de los iconos en pantalla
 * para identificar Destello, Prender, Castigo, Curar, Barrera, Extenuación, etc.
 */
object SummonerSpellDetector {

    data class SpellMatch(
        val spellId: String,
        val spellName: String,
        val iconUrl: String,
        val rect: Rect
    )

    fun detectSpell(spellBitmap: Bitmap, rect: Rect): SpellMatch? {
        if (spellBitmap.width < 8 || spellBitmap.height < 8) return null
        val w = spellBitmap.width
        val h = spellBitmap.height
        val pixels = IntArray(w * h)
        spellBitmap.getPixels(pixels, 0, w, 0, 0, w, h)

        var totalValid = 0
        var totalLum = 0L

        var purpleCount = 0     // Smite (Castigo) - Rayo / Daga mágica púrpura/magenta
        var yellowCount = 0     // Flash (Destello) - Amarillo puro eléctrico
        var amberGoldCount = 0  // Barrier (Barrera) - Escudo ámbar / dorado
        var redFireCount = 0    // Ignite (Prender) - Fuego rojo carmesí
        var greenCount = 0      // Heal (Curar) - Verde esmeralda vivo
        var cyanCount = 0       // Ghost (Fantasmal) - Cyan / Azul brillante
        var darkBrownCount = 0  // Exhaust (Extenuación) - Bronce / Marrón apagado

        // Tomar zona central interna (evitar bordes circulares o bordes oscuros)
        val startX = (w * 0.15f).toInt()
        val endX = (w * 0.85f).toInt()
        val startY = (h * 0.15f).toInt()
        val endY = (h * 0.85f).toInt()

        for (y in startY..endY) {
            for (x in startX..endX) {
                val color = pixels[y * w + x]
                val r = Color.red(color)
                val g = Color.green(color)
                val b = Color.blue(color)
                val lum = (r * 299 + g * 587 + b * 114) / 1000
                totalLum += lum
                totalValid++

                // 1. Castigo (Smite): En Wild Rift el Castigo presenta destello y filo púrpura/violeta/magenta
                // característico de la daga mágica (r y b altos, g notablemente menor). Ningún otro hechizo tiene este matiz.
                if ((r > 110 && b > 110 && g < 115 && (r + b) > (2 * g + 40) && kotlin.math.abs(r - b) < 65) ||
                    (b > 125 && r > 95 && g < 95 && b > g + 30)) {
                    purpleCount++
                }
                // 2. Curar (Heal): Verde esmeralda vivo predominante
                else if (g > 125 && g > r + 30 && g > b + 30) {
                    greenCount++
                }
                // 3. Fantasmal (Ghost): Cyan / Azul claro brillante
                else if (b > 135 && g > 115 && r < 125 && (b - r) > 30) {
                    cyanCount++
                }
                // 4. Prender (Ignite): Rojo intenso llameante
                else if (r > 160 && g < 95 && b < 70 && (r - g) > 60) {
                    redFireCount++
                }
                // 5. Destello (Flash): Amarillo eléctrico puro (r y g muy altos y parejos)
                else if (r > 175 && g > 150 && b < 115 && kotlin.math.abs(r - g) < 38) {
                    yellowCount++
                }
                // 6. Barrera (Barrier): Ámbar / Dorado esférico (r claramente superior a g)
                else if (r > 165 && g in 115..190 && b < 100 && (r - g) in 25..80) {
                    amberGoldCount++
                }
                // 7. Extenuación (Exhaust): Marrón / Bronce oscuro
                else if (r in 110..180 && g in 75..135 && b < 75 && (r - g) in 25..65) {
                    darkBrownCount++
                }
            }
        }

        if (totalValid == 0) return null
        val avgLum = totalLum.toFloat() / totalValid.toFloat()
        // Si el área es muy oscura (< 20 lum), se descarta (slot vacío o sin pick)
        if (avgLum < 20f) return null

        val purpleRatio = purpleCount.toFloat() / totalValid.toFloat()
        val greenRatio = greenCount.toFloat() / totalValid.toFloat()
        val cyanRatio = cyanCount.toFloat() / totalValid.toFloat()
        val redRatio = redFireCount.toFloat() / totalValid.toFloat()
        val yellowRatio = yellowCount.toFloat() / totalValid.toFloat()
        val amberRatio = amberGoldCount.toFloat() / totalValid.toFloat()
        val brownRatio = darkBrownCount.toFloat() / totalValid.toFloat()

        val (spellId, name) = when {
            // 1. Castigo (Smite): Único hechizo con energía púrpura/violeta en la daga
            purpleRatio > 0.020f -> "smite" to "Castigo"
            // 2. Curar: Verde vivo
            greenRatio > 0.07f -> "heal" to "Curar"
            // 3. Fantasmal: Cyan vivo
            cyanRatio > 0.07f -> "ghost" to "Fantasmal"
            // 4. Prender: Fuego rojo vivo
            redRatio > 0.08f -> "ignite" to "Prender"
            // 5. Extenuación: Bronce / marrón
            brownRatio > 0.12f -> "exhaust" to "Extenuación"
            // 6. Barrera: Escudo ámbar (r claramente mayor a g)
            amberRatio > 0.12f && yellowRatio < 0.12f -> "barrier" to "Barrera"
            // 7. Destello: Amarillo puro (r y g muy cercanos)
            yellowRatio > 0.06f -> "flash" to "Destello"
            // 8. Barrera fallback si hay tono ámbar
            amberRatio > 0.08f -> "barrier" to "Barrera"
            else -> "flash" to "Destello"
        }

        val item = WildRiftSpellsAndRunes.getSpellByName(spellId)
        val iconUrl = item?.iconUrl ?: "https://i.postimg.cc/6qHRh6Gt/1691694210-flash.webp"
        return SpellMatch(spellId, name, iconUrl, rect)
    }
}
