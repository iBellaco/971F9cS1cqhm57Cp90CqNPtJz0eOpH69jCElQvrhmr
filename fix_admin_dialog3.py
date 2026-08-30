import re
with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

pattern = re.compile(r'\s*// Scraper Button.*?Text\("Ejecutar BestBuildWR Scraper", color = TextPrimary\)\s*\}\s*', re.DOTALL)
text = pattern.sub('', text)

text = re.sub(r'var scraperProgress by remember \{ mutableStateOf\(""\) \}', '', text)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
print("Removed BestBuildWR Scraper button.")
