import sys
import os

images = [f for f in os.listdir('.') if f.endswith('.jpg') or f.endswith('.png')]
for img in images:
    print(img)
