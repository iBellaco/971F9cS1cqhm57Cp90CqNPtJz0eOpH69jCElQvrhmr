import re

file_path = 'app/src/main/java/com/example/ui/components/WildRiftVersionBanner.kt'

with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

# Force isCompleted to always be true
content = content.replace(
    'val isCompleted = remember(context, downloadState) {\n        OfflineResourceManager.isCompleted(context) || downloadState == com.example.data.sync.DownloadState.COMPLETED\n    }',
    'val isCompleted = true // Force to true since resources are local'
)

# And if it is written slightly differently:
content = content.replace(
    'val isCompleted = remember(context) { OfflineResourceManager.isCompleted(context) }',
    'val isCompleted = true'
)

# Actually let's just search for the definition of isCompleted in that file.
