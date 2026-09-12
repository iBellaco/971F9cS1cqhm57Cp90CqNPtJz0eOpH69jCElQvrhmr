from PIL import Image
im = Image.open('new_icon.png').convert('RGB')
print(im.getpixel((0,0)))
