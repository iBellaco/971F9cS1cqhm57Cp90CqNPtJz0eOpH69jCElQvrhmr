import os, shutil

src = '/tmp/offline_images_stash'
dest = 'app/src/main/assets/offline_images'

os.makedirs(dest, exist_ok=True)

if os.path.exists(src):
    files = os.listdir(src)
    for f in files:
        shutil.move(os.path.join(src, f), os.path.join(dest, f))
    print(f"Restored {len(files)} files.")
else:
    print("Stash directory not found.")
