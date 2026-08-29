import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# 1. Import haptic feedback
if 'import androidx.compose.ui.hapticfeedback.HapticFeedbackType' not in text:
    text = text.replace('import androidx.compose.ui.Alignment', 'import androidx.compose.ui.Alignment\nimport androidx.compose.ui.hapticfeedback.HapticFeedbackType\nimport androidx.compose.ui.platform.LocalHapticFeedback')

# 2. Inside DraftAnalysisTab add haptic
draft_tab_old = """    val tabContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()"""

draft_tab_new = """    val tabContext = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()
    val haptic = LocalHapticFeedback.current"""
text = text.replace(draft_tab_old, draft_tab_new)

# Add haptic to save_draft_button
save_btn_old = """                onClick = {
                    if (isPremium) {
                        showSaveDraftDialog = true"""
save_btn_new = """                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    if (isPremium) {
                        showSaveDraftDialog = true"""
text = text.replace(save_btn_old, save_btn_new)

# Add haptic to open_draft_history_button
hist_btn_old = """                onClick = {
                    if (isPremium) {
                        onOpenHistory()"""
hist_btn_new = """                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    if (isPremium) {
                        onOpenHistory()"""
text = text.replace(hist_btn_old, hist_btn_new)


# 3. Inside ChampionsCatalogTab add haptic
cat_tab_old = """    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()"""

cat_tab_new = """    val context = androidx.compose.ui.platform.LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()
    val haptic = LocalHapticFeedback.current"""
text = text.replace(cat_tab_old, cat_tab_new)

# Add haptic to Add to Favorites button
# Need to find the favorite button. Let's grep for FavoriteChampionsManager first
with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
