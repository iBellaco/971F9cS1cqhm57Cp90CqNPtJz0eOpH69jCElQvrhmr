import os
from PIL import Image

def generate_legacy_icons(source_path):
    # Sizes for standard launcher icons
    sizes = {
        'mdpi': 48,
        'hdpi': 72,
        'xhdpi': 96,
        'xxhdpi': 144,
        'xxxhdpi': 192
    }
    
    if not os.path.exists(source_path):
        print(f"Error: {source_path} not found")
        return
        
    img = Image.open(source_path)
    # Ensure it's a square
    width, height = img.size
    size = min(width, height)
    left = (width - size)/2
    top = (height - size)/2
    right = (width + size)/2
    bottom = (height + size)/2
    img = img.crop((left, top, right, bottom))
    
    for dpi, px in sizes.items():
        folder = f"app/src/main/res/mipmap-{dpi}"
        os.makedirs(folder, exist_ok=True)
        
        resized = img.resize((px, px), Image.Resampling.LANCZOS)
        
        # Save as ic_launcher.png and ic_launcher_round.png
        resized.save(os.path.join(folder, "ic_launcher.png"))
        resized.save(os.path.join(folder, "ic_launcher_round.png"))
        print(f"Generated {dpi} icons")

generate_legacy_icons("app/src/main/res/drawable/ic_launcher_foreground.png")
