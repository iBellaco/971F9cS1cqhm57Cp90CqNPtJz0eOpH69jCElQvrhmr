import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Replace the AnimatedVisibility block
pattern = r"AnimatedVisibility\(\s*visible = isExpanded,[\s\S]*?Box\(\s*modifier = Modifier\s*\.width\(38\.dp\)\s*\.height\(4\.dp\)[\s\S]*?\}[\s\S]*?Row\("

replacement = """AnimatedVisibility(
            visible = isExpanded,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            var dragDownY by remember { mutableFloatStateOf(0f) }
            val isClosingSoon = dragDownY > 10f
            val currentBorderColor = if (isClosingSoon) Color.Red else HextechGold

            Card(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 320.dp)
                    .clip(RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = HextechDarkBg.copy(alpha = 0.98f)),
                border = androidx.compose.foundation.BorderStroke(if (isClosingSoon) 2.dp else 1.5.dp, currentBorderColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    // Barra / Indicador para deslizar hacia abajo y cerrar
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDrag = { change, dragAmount ->
                                        change.consume()
                                        dragDownY += dragAmount.y
                                        if (dragDownY > 30f) {
                                            isExpanded = false
                                            onExpandedChange(false)
                                            dragDownY = 0f
                                        } else {
                                            onDragDelta(dragAmount.x.roundToInt(), dragAmount.y.roundToInt())
                                        }
                                    },
                                    onDragEnd = {
                                        if (dragDownY > 20f) {
                                            isExpanded = false
                                            onExpandedChange(false)
                                        }
                                        dragDownY = 0f
                                    },
                                    onDragCancel = {
                                        dragDownY = 0f
                                    }
                                )
                            }
                            .padding(bottom = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(3.dp)
                                    .clip(CircleShape)
                                    .background(if (isClosingSoon) Color.Red else HextechGold.copy(alpha = 0.7f))
                            )
                            if (isClosingSoon) {
                                Text(
                                    text = tr("Cerrando..."),
                                    color = Color.Red,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }

                    // Header con botón de arrastre y minimizar
                    Row("""

content = re.sub(pattern, replacement, content)

# Remove the old floating "Desliza para cerrar" label above the avatar
label_pattern = r"// Hover text indicating drag down to close[\s\S]*?Box\(\s*modifier = Modifier\s*\.background\(Color\.Black[\s\S]*?\}\s*\}\s*Spacer\(modifier = Modifier\.height\(4\.dp\)\)"
content = re.sub(label_pattern, "}", content)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("Patched FloatingAssistantService.kt")
