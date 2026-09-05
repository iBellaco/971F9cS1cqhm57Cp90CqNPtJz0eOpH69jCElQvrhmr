import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

target_block = """            detectedAllyChamps.forEachIndexed { index, pair -> 
                if (index < 5) allySlots[index] = pair.first 
            }
            detectedEnemyChamps.forEachIndexed { index, pair -> 
                if (index < 5) enemySlots[index] = pair.first 
            }
            detectedAllyRoles.forEachIndexed { index, pair -> 
                if (index < 5) allySlotRoles[index] = pair.first 
            }
            detectedEnemyRoles.forEachIndexed { index, pair -> 
                if (index < 5) enemySlotRoles[index] = pair.first 
            }"""

replacement_block = """            fun getClosestSlotIndex(yRatio: Float, centers: FloatArray): Int {
                var minDiff = Float.MAX_VALUE
                var minIndex = -1
                for (i in centers.indices) {
                    val diff = kotlin.math.abs(yRatio - centers[i])
                    if (diff < minDiff) {
                        minDiff = diff
                        minIndex = i
                    }
                }
                return minIndex
            }

            detectedAllyChamps.forEach { pair ->
                val slot = getClosestSlotIndex(pair.second, allySlotYCenters)
                if (slot in 0..4 && allySlots[slot] == null) {
                    allySlots[slot] = pair.first
                }
            }
            detectedEnemyChamps.forEach { pair ->
                val slot = getClosestSlotIndex(pair.second, enemySlotYCenters)
                if (slot in 0..4 && enemySlots[slot] == null) {
                    enemySlots[slot] = pair.first
                }
            }
            detectedAllyRoles.forEach { pair ->
                val slot = getClosestSlotIndex(pair.second, allySlotYCenters)
                if (slot in 0..4 && allySlotRoles[slot] == null) {
                    allySlotRoles[slot] = pair.first
                }
            }
            detectedEnemyRoles.forEach { pair ->
                val slot = getClosestSlotIndex(pair.second, enemySlotYCenters)
                if (slot in 0..4 && enemySlotRoles[slot] == null) {
                    enemySlotRoles[slot] = pair.first
                }
            }"""

if target_block in text:
    print("Found target block")
    text = text.replace(target_block, replacement_block)
else:
    print("Could not find target block")

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)
