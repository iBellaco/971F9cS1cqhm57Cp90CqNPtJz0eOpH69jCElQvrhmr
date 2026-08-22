import re

# 1. CooldownTrackerPanel.kt fixes
with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r') as f:
    content = f.read()

content = content.replace("import com.example.ui.screens.ChampionPickerSheet\n", "")
content = content.replace("@OptIn(ExperimentalMaterial3Api::class)\n@OptIn(ExperimentalMaterial3Api::class)", "@OptIn(ExperimentalMaterial3Api::class)")

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w') as f:
    f.write(content)


# 2. FloatingAssistantOverlay.kt fixes
with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r') as f:
    content = f.read()

content = content.replace("Icons.Default.Timer", "Icons.Default.Refresh")
content = content.replace("Icons.Default.Calculate", "Icons.Default.Info")

# Check if there are missing branches in `when (selectedTab)`
when_title_target = """                                    OverlayTab.SPELLS -> Text(tr("Hechizos recomendados"), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                }"""
when_title_replacement = """                                    OverlayTab.SPELLS -> Text(tr("Hechizos recomendados"), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    OverlayTab.CD_TRACKER -> Text(tr("⏱️ CD Tracker"), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    OverlayTab.DAMAGE_MATH -> Text(tr("🛡️ Math Daño"), color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                }"""
content = content.replace(when_title_target, when_title_replacement)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w') as f:
    f.write(content)


# 3. MetaAndDraftScreen.kt fixes
with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# Add Button imports
if 'import androidx.compose.material3.Button' not in content:
    content = content.replace('import androidx.compose.material3.Text', 'import androidx.compose.material3.Text\nimport androidx.compose.material3.Button\nimport androidx.compose.material3.ButtonDefaults')

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
