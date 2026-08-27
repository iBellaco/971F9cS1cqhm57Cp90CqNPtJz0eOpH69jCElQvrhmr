import re

with open("app/src/main/java/com/example/ui/components/AdminFeedbackPanel.kt") as f:
    content = f.read()

# Look for situational items rendering logic
idx = content.find("Acceso Restringido")
print(content[idx:idx+100])
