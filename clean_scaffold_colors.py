import re

files = [
    'app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt',
    'app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt',
    'app/src/main/java/com/example/ui/screens/InfoScreen.kt'
]

for file in files:
    with open(file, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Remove all "containerColor = HextechDarkBg" lines inside Scaffold calls.
    # Note: we need to make sure we don't remove TopAppBar containerColor.
    # TopAppBar uses `containerColor = HextechDarkBg` inside TopAppBarDefaults.
    # We only want to remove `containerColor = HextechDarkBg` that is on its own line for Scaffold.
    
    content = re.sub(r'^\s*containerColor\s*=\s*HextechDarkBg\s*,?\s*\n', '', content, flags=re.MULTILINE)
    
    with open(file, 'w', encoding='utf-8') as f:
        f.write(content)

