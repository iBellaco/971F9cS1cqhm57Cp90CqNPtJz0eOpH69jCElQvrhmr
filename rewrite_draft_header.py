import sys

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

target = """        // Role active pill & First Pick Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Role active pill (Hextech styled)
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { 
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onChangeRole() 
                    }
                    .testTag("draft_active_role_pill"),"""

replacement = """        // Role active pill & First Pick Row
        val roleActivePill = @Composable {
            // Role active pill (Hextech styled)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { 
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onChangeRole() 
                    }
                    .testTag("draft_active_role_pill"),"""

content = content.replace(target, replacement)

target2 = """            // First Pick / Blind Pick Mode Switch (Redesigned with Hextech theme)
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { """

replacement2 = """        }
        
        val firstPickCard = @Composable {
            // First Pick / Blind Pick Mode Switch (Redesigned with Hextech theme)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .clickable { """

content = content.replace(target2, replacement2)

target3 = """                        ),
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))"""

replacement3 = """                        ),
                        modifier = Modifier.size(34.dp)
                    )
                }
            }
        }
        
        if (isOverlay) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                roleActivePill()
                firstPickCard()
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.foundation.layout.Box(modifier = Modifier.weight(1f)) { roleActivePill() }
                androidx.compose.foundation.layout.Box { firstPickCard() }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))"""

content = content.replace(target3, replacement3)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
