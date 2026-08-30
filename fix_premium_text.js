const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'utf8');

const newPremiumFeatures = `                features = listOf(
                    FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Colección de Avatares Exclusiva", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Diseños de Bordes Exclusivos", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y Favoritos", true, isHighlight = true, icon = Icons.Default.Star)
                ),`;

const oldPremiumFeaturesPattern = /features = listOf\([\s\S]*?Avatares Exclusivos[\s\S]*?Guardar Draft y Favoritos.*?,\n?\s*\),/;
code = code.replace(oldPremiumFeaturesPattern, newPremiumFeatures);

// Fallback in case regex missed
if (!code.includes("Colección de Avatares Exclusiva")) {
    const oldPremiumFeaturesBackup = `                features = listOf(
                    FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Avatares Exclusivos (Épicos, Legendarios)", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y Favoritos", true, isHighlight = true, icon = Icons.Default.Star)
                ),`;
    code = code.replace(oldPremiumFeaturesBackup, newPremiumFeatures);
}

const newFreeFeatures = `                features = listOf(
                    FeatureItem("Acceso básico al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Avatares Comunes Gratuitos", true),
                    FeatureItem("Colección de Avatares Premium", false),
                    FeatureItem("Diseños de Bordes Exclusivos", false),
                    FeatureItem("Temas Visuales por Región", false),
                    FeatureItem("Partículas Mágicas", false),
                    FeatureItem("Guardar Draft y Favoritos", false)
                )`;

const oldFreeFeaturesBackup = `                features = listOf(
                    FeatureItem("Acceso básico al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Avatares Comunes Gratuitos", true),
                    FeatureItem("Avatares Exclusivos (Épicos, Legendarios)", false),
                    FeatureItem("Temas Visuales por Región", false),
                    FeatureItem("Partículas Mágicas", false),
                    FeatureItem("Guardar Draft y Favoritos", false)
                )`;

code = code.replace(oldFreeFeaturesBackup, newFreeFeatures);

fs.writeFileSync('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', code);
console.log("Updated Premium texts");
