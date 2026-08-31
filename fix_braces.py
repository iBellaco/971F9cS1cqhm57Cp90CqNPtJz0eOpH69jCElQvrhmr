import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace(
'''                } // End of items
            }
            } else {''',
'''                } // End of items
                } // End of forEach
            } // End of LazyVerticalGrid
        } else {''')

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)
