with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Fix 1: Change com.example.ui.components.DraftSlot to com.example.model.DraftSlot
text = text.replace("com.example.ui.components.DraftSlot", "com.example.model.DraftSlot")

# Fix 2: Remove the extra } at the end of CoachContent.
# Currently it looks like:
#     Column(modifier = Modifier.fillMaxWidth()) {
# ... all the code
#     }
# }
# }
# Let's find `    }\n}\n}` and replace with `    }\n}`
text = text.replace("    }\n}\n}", "    }\n}")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

