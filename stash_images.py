import os, shutil

src = 'app/src/main/assets/offline_images'
stash = '/tmp/offline_images_stash'
os.makedirs(stash, exist_ok=True)

files = sorted(os.listdir(src))
if len(files) > 0:
    to_move = files[:len(files)//2]
    for f in to_move:
        shutil.move(os.path.join(src, f), os.path.join(stash, f))
    print(f"Stashed {len(to_move)} files out of {len(files)}.")
else:
    print("No files found or already stashed.")
