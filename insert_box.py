import sys

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {"""

replacement = """                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        ) {"""

content = content.replace(target, replacement)

target_end = """                        }
                    }
                }
            }
        }
    }"""

replacement_end = """                        }
                    }
                    
                    if (selectedChampionDetail != null) {
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

content = content.replace(target_end, replacement_end)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
