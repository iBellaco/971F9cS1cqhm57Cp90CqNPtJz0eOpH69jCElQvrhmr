import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('if (isDeleted) {\n                                    supportMap.remove(doc.id)\n                                    continue\n                                }', 'if (isDeleted) {\n                                    deletedIds = deletedIds + doc.id\n                                    supportMap.remove(doc.id)\n                                    continue\n                                }')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
