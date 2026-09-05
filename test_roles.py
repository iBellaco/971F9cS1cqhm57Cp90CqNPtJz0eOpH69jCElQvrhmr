from PIL import Image

def ahash(image):
    image = image.resize((8, 8), Image.Resampling.LANCZOS).convert('L')
    pixels = list(image.getdata())
    avg = sum(pixels) / len(pixels)
    hash_val = 0
    for i, p in enumerate(pixels):
        if p >= avg:
            hash_val |= (1 << (63 - i))
    return hash_val
