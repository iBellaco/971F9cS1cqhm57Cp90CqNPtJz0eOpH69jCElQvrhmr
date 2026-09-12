#!/bin/bash
sed -n '1,447p' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt > temp.kt

cat << 'INNER_EOF' >> temp.kt

            // -----------------------------------------------------------------------------------------
            // DETECCION VISUAL AUTONOMA DEL USUARIO (Rastreo de la barra dorada / marco brillante del slot activo)
            // -----------------------------------------------------------------------------------------
            var autonomousUserSlot = -1
            try {
                // Exploramos el borde izquierdo y el area del avatar (aprox 12% del ancho) buscando pixeles dorados de Wild Rift
                val searchMarginX = (width * 0.12f).toInt().coerceAtLeast(1)
                
                // Mantenemos un conteo de pixeles dorados por slot
                val goldCounts = IntArray(5)
                
                for (i in 0..4) {
                    val yCenter = (calib.allySlotYRatios[i] * height).toInt()
                    val yTop = (yCenter - height * 0.05f).toInt().coerceAtLeast(0)
                    val yBottom = (yCenter + height * 0.05f).toInt().coerceAtMost(height - 1)
                    
                    var yellowPixels = 0
                    val step = 3
                    for (y in yTop..yBottom step step) {
                        for (x in 0..searchMarginX step step) {
                            val px = bitmap.getPixel(x, y)
                            val r = android.graphics.Color.red(px)
                            val g = android.graphics.Color.green(px)
                            val b = android.graphics.Color.blue(px)
                            // Tolerancia estricta para el Dorado de la UI de Wild Rift (Rojo/Verde altos, Azul bajo)
                            if (r > 160 && g > 130 && b < 100 && r > b * 1.5f && g > b * 1.2f) {
                                yellowPixels++
                            }
                        }
                    }
                    goldCounts[i] = yellowPixels
                }
                
                // El slot con mayor cantidad de pixeles dorados (y que supere un umbral minimo) es el nuestro
                var maxGold = 0
                var maxGoldIndex = -1
                for (i in 0..4) {
                    if (goldCounts[i] > maxGold) {
                        maxGold = goldCounts[i]
                        maxGoldIndex = i
                    }
                }
                
                if (maxGold > 15) { // Umbral minimo de pixeles de marco dorado encontrados
                    autonomousUserSlot = maxGoldIndex
                    userSlotIndex = autonomousUserSlot
                    userExplicitlyConfirmed = true
                    AppLogger.d(TAG, "Slot del usuario detectado AUTONOMAMENTE en Slot Aliado $autonomousUserSlot (Gold Score: $maxGold)")
                }
            } catch (e: Exception) {
                AppLogger.e(TAG, "Error en deteccion visual autonoma", e)
            }

INNER_EOF

sed -n '448,$p' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt >> temp.kt
mv temp.kt app/src/main/java/com/example/service/screen/DraftVisionScanner.kt
