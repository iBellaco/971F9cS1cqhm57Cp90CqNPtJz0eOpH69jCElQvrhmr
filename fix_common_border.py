import re

with open('app/src/main/java/com/example/ui/components/UserAvatarView.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'val actualShowBorder = showBorder && !isCommon',
    'val actualShowBorder = showBorder'
)

with open('app/src/main/java/com/example/ui/components/UserAvatarView.kt', 'w') as f:
    f.write(content)
