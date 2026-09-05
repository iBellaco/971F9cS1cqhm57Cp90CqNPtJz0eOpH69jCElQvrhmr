import re

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

text = re.sub(r'\s*var showTerminalScraper by remember \{ mutableStateOf\(false\) \}', '', text)

text = re.sub(r'\s*if \(showTerminalScraper\) \{\s*AdminTerminalScraperDialog\(\s*onDismiss = \{ showTerminalScraper = false \}\s*\)\s*\}', '', text)

# Remove the Scrappers button
# Need to be careful with regex here
button_regex = r'\s*Button\(\s*onClick = \{ showTerminalScraper = true \},.*?\}\s*\)\s*\}'
text = re.sub(button_regex, '', text, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
