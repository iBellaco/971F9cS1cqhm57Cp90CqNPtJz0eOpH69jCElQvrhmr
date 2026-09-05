import re

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'r') as f:
    text = f.read()
    
# Let's replace the resize block
old_block = """
                        currentVirtualDisplay.surface = newImageReader.surface
                        currentVirtualDisplay.resize(captureWidth, captureHeight, screenDensity)
"""
new_block = """
                        currentVirtualDisplay.surface = null
                        currentVirtualDisplay.resize(captureWidth, captureHeight, screenDensity)
                        currentVirtualDisplay.surface = newImageReader.surface
"""
text = text.replace(old_block, new_block)

with open('app/src/main/java/com/example/service/screen/ScreenCaptureManager.kt', 'w') as f:
    f.write(text)
