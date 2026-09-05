import re
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# The error is that we have an extra `} } }` before CoachContent call, 
# and we didn't remove the original Coach content from FloatingDraftCoachView.

# Wait, since I don't have git, I can just fix the file by finding the exact syntax errors and fixing them.
# Let's see where the extra CoachContent call is:
malformed_call = """        }
    }
}
                CoachContent(
                    activeRole = activeRole,
                    onActiveRoleChange = onActiveRoleChange,
                    isFirstPick = isFirstPick,
                    onFirstPickToggle = onFirstPickToggle,
                    analysis = analysis,
                    explicitEnemyOpponent = explicitEnemyOpponent,
                    onSelectChampion = onSelectChampion,
                    onSaveDraftClick = onSaveDraftClick,
                    isSavedRecently = isSavedRecently,
                    onClearAll = onClearAll,
                    onGoToTierList = onGoToTierList,
                    isPremium = isPremium
                )
    }
}"""
text = text.replace(malformed_call, "        }\n    }\n}\n")

# Now I need to remove the duplicate body from FloatingDraftCoachView and replace it with a single call to CoachContent.
# Where is the duplicate body?
start_marker = "        Spacer(modifier = Modifier.height(6.dp))\n        // 2. SELECTOR DE MI ROL / LÍNEA"
start_idx = text.find(start_marker)

# We want to replace everything from start_marker until the end of FloatingDraftCoachView.
# How to find the end of FloatingDraftCoachView?
# We know it is followed by @Composable\nprivate fun CoachContent(
end_idx = text.find("@Composable\nprivate fun CoachContent(")

if start_idx != -1 and end_idx != -1:
    # We replace from start_idx to end_idx with just the call to CoachContent and closing braces.
    proper_call = """                CoachContent(
                    activeRole = activeRole,
                    onActiveRoleChange = onActiveRoleChange,
                    isFirstPick = isFirstPick,
                    onFirstPickToggle = onFirstPickToggle,
                    analysis = analysis,
                    explicitEnemyOpponent = explicitEnemyOpponent,
                    onSelectChampion = onSelectChampion,
                    onSaveDraftClick = onSaveDraftClick,
                    isSavedRecently = isSavedRecently,
                    onClearAll = onClearAll,
                    onGoToTierList = onGoToTierList,
                    isPremium = isPremium
                )
    }
}
"""
    text = text[:start_idx] + proper_call + text[end_idx:]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
