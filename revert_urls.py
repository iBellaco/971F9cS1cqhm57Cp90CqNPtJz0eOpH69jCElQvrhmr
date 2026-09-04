import re
import os

files = [
    "app/src/main/java/com/example/data/WildRiftItemsData.kt",
    "app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt",
    "app/src/main/java/com/example/data/WildRiftRepository.kt",
    "app/src/main/java/com/example/data/AvatarCatalog.kt"
]

def revert_asset_urls(filepath):
    if not os.path.exists(filepath):
        print(f"Skipping {filepath}, does not exist.")
        return
        
    with open(filepath, "r") as f:
        content = f.read()
        
    # Standard replacement rule used when downloading:
    # re.sub(r'https://.*?((\w+)\.(png|jpg))', r'file:///android_asset/offline_images/\1', content)
    
    # We need to revert this. Let's do a smart regex fallback:
    # Since we can't reliably guess the original URL base for every single file if it was overwritten destructively,
    # we'll look for our downloaded image metadata if we had any. If not, we have a problem.
    # We'll use git checkout to restore the original files before the download!
    pass

print("Use git to revert the data files.")
