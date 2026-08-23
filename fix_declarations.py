import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Fix conflicting declarations
text = text.replace(
    '''    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val syncState by ChineseMetaSyncService.syncState.collectAsStateWithLifecycle()''',
    '''    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val syncState by ChineseMetaSyncService.syncState.collectAsStateWithLifecycle()'''
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
