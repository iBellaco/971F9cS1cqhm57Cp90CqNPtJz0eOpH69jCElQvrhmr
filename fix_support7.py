import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('try { try {', 'try {')
new_content = new_content.replace('} catch (e: Exception) {} } catch (e: Exception) {}', '} catch (e: Exception) {}')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
