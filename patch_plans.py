import re

with open('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'r') as f:
    text = f.read()

# Make sure we have the imports
if 'import androidx.compose.material.icons.filled.Save' not in text:
    text = text.replace('import androidx.compose.material.icons.filled.Check', 'import androidx.compose.material.icons.filled.Check\nimport androidx.compose.material.icons.filled.Save\nimport androidx.compose.material.icons.filled.Star')

premium_features = """                    FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Tier List y Catálogo actualizados", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Panel de Temas Exclusivo", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Barra de Navegación Personalizable", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y ver Historial", true, isHighlight = true, icon = Icons.Default.Save),
                    FeatureItem("Agregar Campeones a Favoritos", true, isHighlight = true, icon = Icons.Default.Star)"""

free_features = """                    FeatureItem("Acceso básico al Asistente de Draft", true),
                    FeatureItem("Tier List y Catálogo", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Temas y Barra Personalizables", false),
                    FeatureItem("Guardar Draft y ver Historial", false),
                    FeatureItem("Agregar Campeones a Favoritos", false)"""

text = re.sub(
    r'FeatureItem\("Acceso completo al Asistente de Draft", true\),.*?FeatureItem\("Barra de Navegación Personalizable", true, isHighlight = true, icon = Icons.Default.AutoAwesome\)',
    premium_features,
    text,
    flags=re.DOTALL
)

text = re.sub(
    r'FeatureItem\("Acceso básico al Asistente de Draft", true\),.*?FeatureItem\("Panel de Temas Exclusivo", false\),\s*FeatureItem\("Barra de Navegación Personalizable", false\)',
    free_features,
    text,
    flags=re.DOTALL
)

with open('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'w') as f:
    f.write(text)
