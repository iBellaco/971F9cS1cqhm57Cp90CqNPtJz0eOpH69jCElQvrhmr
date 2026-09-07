from PIL import Image
img = Image.open('Screenshot_20260907_190754.jpg')
width, height = img.size
print(f"Image 1: {width}x{height}")
