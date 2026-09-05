import re

with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'r') as f:
    text = f.read()

target = """    private fun getClosestSlotIndex(yRatio: Float): Int {
        return when {
            yRatio < 0.20f -> 0
            yRatio < 0.38f -> 1
            yRatio < 0.58f -> 2
            yRatio < 0.78f -> 3
            else -> 4
        }
    }"""
    
replacement = """    private fun getClosestSlotIndex(yRatio: Float): Int {
        // En landscape real de WildRift, las proporciones suelen ser un poco más apretadas 
        // debido a los bordes negros o el recorte de cámara. 
        // 0=Top, 1=Jg, 2=Mid, 3=Adc, 4=Sup
        return when {
            yRatio < 0.25f -> 0 // TOP
            yRatio < 0.42f -> 1 // JUG
            yRatio < 0.60f -> 2 // MID
            yRatio < 0.78f -> 3 // DUO
            else -> 4           // SUP
        }
    }"""

text = text.replace(target, replacement)
with open('app/src/main/java/com/example/service/screen/DraftVisionScanner.kt', 'w') as f:
    f.write(text)

