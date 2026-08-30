const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'utf8');

const oldPremiumFeatures = `                features = listOf(
                                        FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Tier List y Catálogo actualizados", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Panel de Temas Exclusivo", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Barra de Navegación Personalizable", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y ver Historial", true, isHighlight = true, icon = Icons.Default.Save),
                    FeatureItem("Agregar Campeones a Favoritos", true, isHighlight = true, icon = Icons.Default.Star)
                ),`;

const newPremiumFeatures = `                features = listOf(
                    FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Avatares Exclusivos (Épicos, Legendarios)", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y Favoritos", true, isHighlight = true, icon = Icons.Default.Star)
                ),`;

const oldFreeFeatures = `                features = listOf(
                                        FeatureItem("Acceso básico al Asistente de Draft", true),
                    FeatureItem("Tier List y Catálogo", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Temas y Barra Personalizables", false),
                    FeatureItem("Guardar Draft y ver Historial", false),
                    FeatureItem("Agregar Campeones a Favoritos", false)
                )`;

const newFreeFeatures = `                features = listOf(
                    FeatureItem("Acceso básico al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Avatares Comunes Gratuitos", true),
                    FeatureItem("Avatares Exclusivos (Épicos, Legendarios)", false),
                    FeatureItem("Temas Visuales por Región", false),
                    FeatureItem("Partículas Mágicas", false),
                    FeatureItem("Guardar Draft y Favoritos", false)
                )`;

code = code.replace(oldPremiumFeatures, newPremiumFeatures);
code = code.replace(oldFreeFeatures, newFreeFeatures);

fs.writeFileSync('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', code);
console.log("Updated!");
