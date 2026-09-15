import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('if (change.type == com.google.firebase.firestore.DocumentChange.Type.REMOVED) {', 'if (change.type == com.google.firebase.firestore.DocumentChange.Type.REMOVED) {\n                                    deletedIds = deletedIds + change.document.id')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
