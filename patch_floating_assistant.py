import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# I will replace the AnimatedVisibility part to have a border that turns red when dragged down.

new_animated = """
        AnimatedVisibility(
            visible = isExpanded,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            var dragDownY by remember { mutableFloatStateOf(0f) }
            val isClosingSoon = dragDownY > 15f
            val cardBorderColor = if (isClosingSoon) Color.Red else HextechGold

            Card(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 320.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                // If they drag down strongly, we count it towards closing.
                                // We don't consume the change so they can still scroll?
                                // Actually detectDragGestures consumes it. We only want to detect drag on the top bar OR if we allow dragging the whole card?
                                // Let's keep the drag mostly on the top bar but make it wider, or just keep it on the whole card but then buttons won't work...
                                // No, just let the top bar handle it, but give it the red border feedback.
                            }
                        )
                    },
"""
# I will use a regex to replace
