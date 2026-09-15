import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('db.collection("support_reports").document(id).update(', 'try { db.collection("support_reports").document(id).update(')
new_content = new_content.replace('.continueWithTask {\n            db.collection("support_reports").document(id).delete()\n        }', '.continueWithTask {\n            db.collection("support_reports").document(id).delete()\n        } } catch (e: Exception) {}')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
