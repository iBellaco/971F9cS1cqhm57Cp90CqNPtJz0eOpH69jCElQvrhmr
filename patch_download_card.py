import re

file_path = 'app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt'

with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

# Force the card to never show by changing its logic
content = content.replace(
    'val isAlreadyCompleted = remember(context, downloadState) {\n        OfflineResourceManager.isCompleted(context) || downloadState == DownloadState.COMPLETED\n    }',
    'val isAlreadyCompleted = true // Forced to true since resources are now local'
)

with open(file_path, 'w', encoding='utf-8') as f:
    f.write(content)
