import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

bad_replacement = """                    if (selectedChampionDetail != null) {
                        com.example.ui.screens.ChampionDetailSheet(
                            isOverlay = true,
                            champion = selectedChampionDetail,
                            onDismiss = { selectedChampionDetail = null }
                        )
                    }
                } // close Box
                }
            }
        }
    }"""

original_end = """                }
            }
        }
    }"""

# Replace all occurrences EXCEPT the first one back to original_end
parts = content.split(bad_replacement)
if len(parts) > 1:
    new_content = parts[0]
    for i in range(1, len(parts)):
        if i == 1:
            new_content += bad_replacement + parts[i]
        else:
            new_content += original_end + parts[i]

    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write(new_content)

