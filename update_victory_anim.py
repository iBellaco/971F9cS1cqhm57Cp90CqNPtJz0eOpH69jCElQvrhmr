with open('app/src/main/java/com/example/ui/components/SaveDraftDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Add animation imports if not present
if "animateFloatAsState" not in content:
    content = content.replace("import androidx.compose.ui.Modifier", "import androidx.compose.ui.Modifier\nimport androidx.compose.animation.core.*\nimport androidx.compose.ui.draw.scale")

# Add animated scale to victory/defeat cards
old_opt1 = '''                    // Option 1: Victoria
                    val isVictorySelected = selectedResult == "VICTORY"
                    Card(
                        modifier = Modifier
                            .weight(1f)'''

new_opt1 = '''                    // Option 1: Victoria
                    val isVictorySelected = selectedResult == "VICTORY"
                    val victoryScale by animateFloatAsState(
                        targetValue = if (isVictorySelected) 1.04f else 1.0f,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
                        label = "victory_scale"
                    )
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .scale(victoryScale)'''

content = content.replace(old_opt1, new_opt1)

with open('app/src/main/java/com/example/ui/components/SaveDraftDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)
