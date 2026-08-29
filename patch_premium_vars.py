import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Add isPremium to DraftAnalysisTab
draft_old = """    val tabContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()"""
draft_new = """    val tabContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()"""
text = text.replace(draft_old, draft_new)

# Add isPremium to ChampionsCatalogTab
catalog_old = """    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = rememberCoroutineScope()"""
catalog_new = """    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()"""
text = text.replace(catalog_old, catalog_new)

# Replace 'context' with 'tabContext' in DraftAnalysisTab
toast_old = 'android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()'
toast_new = 'android.widget.Toast.makeText(tabContext, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()'
# But wait, this would also replace in ChampionsCatalogTab, which uses 'context'.
# So let's only replace the ones near showSaveDraftDialog and onOpenHistory.

text = text.replace(
    '                        android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()\n                    }\n                },\n                modifier = Modifier\n                    .weight(1f)\n                    .height(40.dp)\n                    .testTag("save_draft_button")',
    '                        android.widget.Toast.makeText(tabContext, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()\n                    }\n                },\n                modifier = Modifier\n                    .weight(1f)\n                    .height(40.dp)\n                    .testTag("save_draft_button")'
)

text = text.replace(
    '                        android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()\n                    }\n                },\n                modifier = Modifier\n                    .weight(1f)\n                    .height(40.dp)\n                    .testTag("open_draft_history_button")',
    '                        android.widget.Toast.makeText(tabContext, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()\n                    }\n                },\n                modifier = Modifier\n                    .weight(1f)\n                    .height(40.dp)\n                    .testTag("open_draft_history_button")'
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
