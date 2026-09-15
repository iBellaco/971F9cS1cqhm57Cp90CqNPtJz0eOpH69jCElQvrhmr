import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('deletedIds = deletedIds + change.document.id', 'deletedIds = deletedIds + change.document.id\n                                    deletedRefreshTrigger++')
new_content = new_content.replace('deletedIds = deletedIds + doc.id', 'deletedIds = deletedIds + doc.id\n                                    deletedRefreshTrigger++')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
