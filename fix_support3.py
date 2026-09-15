import re

content = open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'r').read()

new_content = content.replace('var deletedIds by remember { mutableStateOf<Set<String>>(emptySet()) }', 'var deletedIds by remember { mutableStateOf<Set<String>>(emptySet()) }\n    var deletedRefreshTrigger by remember { mutableStateOf(0) }')
new_content = new_content.replace('val messages = remember(subcollectionMessages, arrayMessages, supportReportMessages, deletedIds) {', 'val messages = remember(subcollectionMessages, arrayMessages, supportReportMessages, deletedIds, deletedRefreshTrigger) {')

open('app/src/main/java/com/example/ui/components/UserInboxDialog.kt', 'w').write(new_content)
