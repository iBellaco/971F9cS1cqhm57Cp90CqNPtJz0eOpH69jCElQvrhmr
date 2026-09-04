import re

file_paths = ['app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'app/src/main/java/com/example/ui/components/WildRiftVersionBanner.kt']

for file_path in file_paths:
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # We will remove the Offline Resource manager references from MainDraftingScreen
    # It might be complex via regex, so we'll just try to find the blocks that render the download banners and remove them.
    # Alternatively, I can just replace the variable values so it never shows.

