import re

with open('app/src/main/java/com/example/ui/components/NoticeMediaViewer.kt', 'r') as f:
    content = f.read()

# Verify how LocalGalleryVideoPlayer is implemented
print(re.findall(r'AndroidView\(\s*factory = \{ ctx ->.*?(?=modifier =)', content, re.DOTALL))
