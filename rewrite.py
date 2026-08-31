import re

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Fix the var selectedCategory
if "var selectedCategory by remember { mutableStateOf(" not in content:
    content = content.replace("var showPremiumRequiredDialog",
                              'var selectedCategory by remember { mutableStateOf("Avatares") }\n    var showPremiumRequiredDialog')

# Fix TabRow
if "TabRow(" not in content:
    content = content.replace(
'''                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))''',
'''                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextSecondary)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material3.TabRow(
                selectedTabIndex = if (selectedCategory == "Avatares") 0 else 1,
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                contentColor = HextechGold
            ) {
                androidx.compose.material3.Tab(
                    selected = selectedCategory == "Avatares",
                    onClick = { selectedCategory = "Avatares" },
                    text = { Text("Avatares") }
                )
                androidx.compose.material3.Tab(
                    selected = selectedCategory == "Marcos",
                    onClick = { selectedCategory = "Marcos" },
                    text = { Text("Marcos (Premium)") }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))''')

# Fix if (selectedCategory == "Avatares") around the SearchBar and Grid
if "if (selectedCategory == \"Avatares\") {" not in content:
    content = content.replace('// Search Bar', 'if (selectedCategory == "Avatares") {\n            // Search Bar')

# Fix the end of Avatares section and start of Borders section
content = content.replace(
'''                } // End of forEach
            }
                } else {''',
'''                } // End of items
            }
            } else {''')

# Fix the end of the file braces
content = content.replace(
'''                    }
                }

    // Modal when user tries to equip a locked avatar''',
'''                    }
                }
            } // End of else block
        } // End of Column
    } // End of ModalBottomSheet

    // Modal when user tries to equip a locked avatar''')

with open('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'w', encoding='utf-8') as f:
    f.write(content)
