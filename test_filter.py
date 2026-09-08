import re

def is_debug_text(t):
    return "[ocr]" in t.lower() or "vis:" in t.lower() or "[visual]" in t.lower()

print(is_debug_text("Annie [OCR] VIS:Annie (0.60)"))
print(is_debug_text("MISS FORTUNE"))
