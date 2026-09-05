with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

import re

# Find the main Column in FloatingDraftCoachView
old_column_start = """    Column(
        modifier = Modifier.fillMaxSize()
    ) {"""

new_column_start = """    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {"""

text = text.replace(old_column_start, new_column_start)

# Now fix the isLandscapeMode block
old_landscape = """        if (isLandscapeMode) {
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f).fillMaxHeight().verticalScroll(rememberScrollState())) {
                    com.example.ui.components.DraftTeamPositionCard("""

new_landscape = """        if (isLandscapeMode) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    com.example.ui.components.DraftTeamPositionCard("""
text = text.replace(old_landscape, new_landscape)

old_landscape_enemy = """                Box(modifier = Modifier.weight(1f).fillMaxHeight().verticalScroll(rememberScrollState())) {
                    com.example.ui.components.DraftTeamPositionCard(
                        isOverlay = true,
                        title = tr("Equipo Rival"),"""

new_landscape_enemy = """                Box(modifier = Modifier.weight(1f)) {
                    com.example.ui.components.DraftTeamPositionCard(
                        isOverlay = true,
                        title = tr("Equipo Rival"),"""
text = text.replace(old_landscape_enemy, new_landscape_enemy)

old_portrait = """        } else {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f).verticalScroll(rememberScrollState())
            ) {"""

new_portrait = """        } else {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {"""
text = text.replace(old_portrait, new_portrait)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
