with open('app/src/main/java/com/example/ui/components/SaveDraftDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace(
'''                        ) {
                            Text(text = "💔", fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tr("Victoria"),''',
'''                        ) {
                            Text(text = "👑", fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = tr("Victoria"),''')

with open('app/src/main/java/com/example/ui/components/SaveDraftDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)
