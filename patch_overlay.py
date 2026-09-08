import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """            val cWidth = if (isExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
            val cHeight = if (isExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
            val margin = (16 * density).toInt()
            DraftVisionScanner.overlayRect = android.graphics.Rect(params.x - margin, params.y - margin, params.x + cWidth + margin, params.y + cHeight + margin)"""

replacement = """            val cWidth = floatingComposeView?.width?.takeIf { it > 0 } ?: if (isExpanded) ((if (isLandscape) 560 else 330) * density).toInt() else (46 * density).toInt()
            val cHeight = floatingComposeView?.height?.takeIf { it > 0 } ?: if (isExpanded) ((if (isLandscape) 390 else 520) * density).toInt() else (46 * density).toInt()
            
            // Adjust coordinates to absolute screen pixels to match MediaProjection bitmap
            val loc = IntArray(2)
            floatingComposeView?.getLocationOnScreen(loc)
            val absoluteX = if (loc[0] != 0) loc[0] else params.x
            val absoluteY = if (loc[1] != 0) loc[1] else params.y
            
            val margin = (32 * density).toInt() // Incremented margin to be safe
            DraftVisionScanner.overlayRect = android.graphics.Rect(absoluteX - margin, absoluteY - margin, absoluteX + cWidth + margin, absoluteY + cHeight + margin)"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(content)
    print("Patched updateOverlayRect")
else:
    print("Could not find overlay target")

