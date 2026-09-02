import sys

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "r") as f:
    content = f.read()

replacement = """        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,"""

target = """        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,"""

content = content.replace(replacement, target)

replacement_end = """                            )
                        }
                    }
                } // Close Row
                
                // --- HARDWARE & DEVICES SECTION ---"""

target_end = """                            )
                        }
                    }
                }
                
                // --- HARDWARE & DEVICES SECTION ---"""

content = content.replace(replacement_end, target_end)

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "w") as f:
    f.write(content)
