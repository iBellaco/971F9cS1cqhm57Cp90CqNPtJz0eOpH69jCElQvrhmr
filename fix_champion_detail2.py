import sys

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    lines = f.readlines()

# 1. Replace the missing block around line 1589
for i, line in enumerate(lines):
    if "Spacer(modifier = Modifier.height(30.dp))" in line:
        # We found line 1587. Let's make sure the next lines are `        }\n    }\n`
        if lines[i+1].strip() == "}" and lines[i+2].strip() == "}":
            lines[i+2] = """    }
    }

    if (isOverlay) {
        androidx.compose.foundation.layout.Box(
            modifier = Modifier
                .fillMaxSize()
                .background(androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.6f))
                .clickable(
                    interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            androidx.compose.foundation.layout.Box(modifier = Modifier.clickable(
                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() },
                indication = null,
                onClick = {}
            )) {
                dialogContent()
            }
        }
    } else {
        androidx.compose.ui.window.Dialog(
            onDismissRequest = onDismiss,
            properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false)
        ) {
            dialogContent()
        }
    }
"""
            break

# 2. Remove the last closing brace at the very end of the file.
# The compiler said: e: file:///app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt:2214:2 Syntax error: Expecting '}'.
# It actually means there is an extra '}' or missing something. Wait, "Expecting '}'" usually means it's missing a closing brace?
# Wait! If it says "Expecting '}'", it means we didn't close a block!
