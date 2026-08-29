import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

fav_old = """                                    IconButton(
                                        onClick = { 
                                            if (isPremium) {"""

fav_new = """                                    IconButton(
                                        onClick = { 
                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            if (isPremium) {"""
text = text.replace(fav_old, fav_new)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
