import re
with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'r') as f:
    text = f.read()

pattern = re.compile(r'\s*// Scraper Button.*?Text\("Ejecutar Scraper \(Actualizar Meta\)", color = TextPrimary\)\s*\}\s*', re.DOTALL)
text = pattern.sub('', text)

with open('app/src/main/java/com/example/ui/components/AdminDashboardDialog.kt', 'w') as f:
    f.write(text)
print("Removed scraper button completely.")
