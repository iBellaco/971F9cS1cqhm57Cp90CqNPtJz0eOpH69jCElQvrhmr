import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Let's find:
target = """                                .clickable { isExpanded = false }
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }"""
replacement = """                                .clickable { isExpanded = false }
                                .padding(4.dp)
                        )
                    }
                    } // End Scrollable Column
                }
            }
        }
    }"""
content = content.replace(target, replacement)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
