import re

with open('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'r') as f:
    content = f.read()

# Fix annual plan "2 meses gratis (Ahorro del 16%)" to "1 mes gratis (Ahorro del 8%)"
content = content.replace(
    'FeatureItem("2 meses gratis (Ahorro del 16%)", true, isHighlight = true, icon = Icons.Default.Star)',
    'FeatureItem("1 mes gratis (Ahorro del 8%)", true, isHighlight = true, icon = Icons.Default.Star)'
)

# Remove "Diseños de Bordes Exclusivos" lines
content = re.sub(r'\s*FeatureItem\("Diseños de Bordes Exclusivos"[^\n]+', '', content)

with open('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'w') as f:
    f.write(content)
