import sys

with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'r') as f:
    lines = f.readlines()

replaced = False
for i, line in enumerate(lines):
    if "Spacer(modifier = Modifier.height(30.dp))" in line:
        print(f"Found Spacer at line {i+1}")
        print(f"Line {i+2} is {lines[i+1].strip()}")
        print(f"Line {i+3} is {lines[i+2].strip()}")
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
            contentAlignment = androidx.compose.ui.Alignment.BottomCenter
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
            replaced = True
            break

if replaced:
    print("Replaced!")
    with open('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', 'w') as f:
        f.writelines(lines)
else:
    print("Not replaced!")
