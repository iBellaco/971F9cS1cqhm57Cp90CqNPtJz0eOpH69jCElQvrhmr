import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

if 'import androidx.compose.foundation.verticalScroll' not in content:
    content = content.replace('import androidx.compose.foundation.layout.fillMaxWidth', 
                              'import androidx.compose.foundation.layout.fillMaxWidth\nimport androidx.compose.foundation.verticalScroll\nimport androidx.compose.foundation.rememberScrollState')

# Find where to start the Scrollable Column
# Around line 653:
#                     Spacer(modifier = Modifier.height(8.dp))
#                     // Tab Content
#                     when (selectedTab) {
target = """                    Spacer(modifier = Modifier.height(8.dp))
                    // Tab Content
                    when (selectedTab) {"""
replacement = """                    Spacer(modifier = Modifier.height(8.dp))
                    // Scrollable Content Region
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                            .verticalScroll(rememberScrollState())
                    ) {
                        // Tab Content
                        when (selectedTab) {"""
content = content.replace(target, replacement)

# Now we need to close this Column after the bottom buttons.
# Let's find the bottom buttons end.
target2 = """                        Text(
                            text = tr("Minimizar HUD"),
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { isExpanded = false }
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}"""
replacement2 = """                        Text(
                            text = tr("Minimizar HUD"),
                            color = HextechCyan,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .clickable { isExpanded = false }
                                .padding(4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    } // End Scrollable Column
                }
            }
        }
    }
}"""
content = content.replace(target2, replacement2)

# Add heightIn to Card
target_card = """            Card(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 320.dp)
                    .clip(RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),"""
replacement_card = """            Card(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 320.dp)
                    .heightIn(max = 480.dp)
                    .clip(RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),"""
content = content.replace(target_card, replacement_card)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
