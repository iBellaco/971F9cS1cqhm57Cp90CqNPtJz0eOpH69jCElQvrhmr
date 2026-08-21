import os
import subprocess
import urllib.request

ICON_URL = "https://i.postimg.cc/ZKm74vvC/icon.png"
TMP_PATH = "/tmp/user_icon.png"

def sync_icon():
    print(f"Downloading custom icon from {ICON_URL}...")
    req = urllib.request.Request(ICON_URL, headers={"User-Agent": "Mozilla/5.0"})
    with urllib.request.urlopen(req) as resp, open(TMP_PATH, "wb") as f:
        f.write(resp.read())

    # Generate drawables
    os.makedirs("app/src/main/res/drawable", exist_ok=True)
    os.makedirs("app/src/main/res/drawable-xxxhdpi", exist_ok=True)
    subprocess.run(["convert", TMP_PATH, "-resize", "512x512", "app/src/main/res/drawable/app_icon_custom_foreground.png"], check=True)
    subprocess.run(["convert", TMP_PATH, "-resize", "512x512", "app/src/main/res/drawable-xxxhdpi/app_icon_custom_foreground.png"], check=True)

    densities = {
        "mipmap-mdpi": 48,
        "mipmap-hdpi": 72,
        "mipmap-xhdpi": 96,
        "mipmap-xxhdpi": 144,
        "mipmap-xxxhdpi": 192,
    }

    for folder, size in densities.items():
        dir_path = f"app/src/main/res/{folder}"
        os.makedirs(dir_path, exist_ok=True)
        # Square
        subprocess.run([
            "convert", TMP_PATH,
            "-resize", f"{size}x{size}",
            "-gravity", "center",
            "-extent", f"{size}x{size}",
            f"{dir_path}/ic_launcher.png"
        ], check=True)
        # Round
        subprocess.run([
            "convert", TMP_PATH,
            "-resize", f"{size}x{size}^",
            "-gravity", "center",
            "-extent", f"{size}x{size}",
            "(", "+clone", "-alpha", "extract",
            "-draw", f"fill black polygon 0,0 0,{size} {size},{size} {size},0 fill white circle {size/2},{size/2} {size/2},0",
            "(", "+clone", "-flip", ")", "-compose", "Multiply", "-composite",
            "(", "+clone", "-flop", ")", "-compose", "Multiply", "-composite",
            ")", "-alpha", "off", "-compose", "CopyOpacity", "-composite",
            f"{dir_path}/ic_launcher_round.png"
        ], check=True)

    print("Successfully synchronized custom launcher icons for all density buckets!")

if __name__ == "__main__":
    sync_icon()
