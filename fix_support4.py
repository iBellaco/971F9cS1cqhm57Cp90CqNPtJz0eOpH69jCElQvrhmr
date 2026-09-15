import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('deletedIds = deletedIds + id', 'deletedIds = deletedIds + id\n        deletedRefreshTrigger++')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
