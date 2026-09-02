import sys

with open('app/src/main/java/com/example/ui/components/SaveDraftDialog.kt', 'r') as f:
    content = f.read()

target = """    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {"""

replacement = """    val dialogContent = @Composable {"""

content = content.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/SaveDraftDialog.kt', 'w') as f:
    f.write(content)
