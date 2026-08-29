import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Fix Guardar Draft button
save_draft_old = """            Button(
                onClick = {
                    showSaveDraftDialog = true
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .testTag("save_draft_button"),"""
save_draft_new = """            Button(
                onClick = {
                    if (isPremium) {
                        showSaveDraftDialog = true
                    } else {
                        android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .testTag("save_draft_button"),"""
text = text.replace(save_draft_old, save_draft_new)

# Fix Historial button
hist_old = """            Button(
                onClick = onOpenHistory,
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .testTag("open_draft_history_button"),"""
hist_new = """            Button(
                onClick = {
                    if (isPremium) {
                        onOpenHistory()
                    } else {
                        android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .testTag("open_draft_history_button"),"""
text = text.replace(hist_old, hist_new)

# Fix Add to favorites in MetaAndDraftScreen
fav_old1 = """                                        onClick = { FavoriteChampionsManager.toggleFavorite(context, champion.id) },"""
fav_new1 = """                                        onClick = { 
                                            if (isPremium) {
                                                FavoriteChampionsManager.toggleFavorite(context, champion.id) 
                                            } else {
                                                android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()
                                            }
                                        },"""
text = text.replace(fav_old1, fav_new1)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    text2 = f.read()

fav_old2 = """                    IconButton(
                        onClick = { FavoriteChampionsManager.toggleFavorite(context, champion.id) },
                        modifier = Modifier.testTag("detail_fav_button")
                    ) {"""
fav_new2 = """                    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()
                    IconButton(
                        onClick = { 
                            if (isPremium) {
                                FavoriteChampionsManager.toggleFavorite(context, champion.id) 
                            } else {
                                android.widget.Toast.makeText(context, "Requiere suscripción Premium", android.widget.Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.testTag("detail_fav_button")
                    ) {"""
text2 = text2.replace(fav_old2, fav_new2)

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
    f.write(text2)
