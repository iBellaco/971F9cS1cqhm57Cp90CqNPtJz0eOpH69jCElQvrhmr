import sys

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "r") as f:
    content = f.read()

target = """        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,"""

replacement = """        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,"""

content = content.replace(target, replacement)

target_end = """                            )
                        }
                    }
                }
                
                // --- HARDWARE & DEVICES SECTION ---"""

replacement_end = """                            )
                        }
                    }
                } // Close Row
                
                // --- HARDWARE & DEVICES SECTION ---"""

content = content.replace(target_end, replacement_end)

with open("app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt", "w") as f:
    f.write(content)
