const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', 'utf8');

const regex = /            PremiumPlanCard\(\n\s*title = "Coach Premium",\n\s*price = "\$1\.00",\n\s*period = "\/ mes",\n\s*features = listOf\([\s\S]*?\),\n\s*onSubscribe = \{[\s\S]*?\}\n\s*\)/m;

const replacement = `            PremiumPlanCard(
                title = "Coach Premium (Mensual)",
                price = "$1.00",
                period = "/ mes",
                features = listOf(
                    FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Colección de Avatares Exclusiva", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Diseños de Bordes Exclusivos", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y Favoritos", true, isHighlight = true, icon = Icons.Default.Star)
                ),
                onSubscribe = {
                    Toast.makeText(context, "Compras in-app temporalmente deshabilitadas por seguridad.", Toast.LENGTH_LONG).show()
                    scope.launch {
                        sheetState.hide()
                        onDismiss()
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Premium Anual Card
            PremiumPlanCard(
                title = "Coach Premium (Anual)",
                price = "$11.00",
                period = "/ año (Normalmente $12)",
                features = listOf(
                    FeatureItem("2 meses gratis (Ahorro del 16%)", true, isHighlight = true, icon = Icons.Default.Star),
                    FeatureItem("Acceso completo al Asistente de Draft", true),
                    FeatureItem("Descarga de recursos offline", true),
                    FeatureItem("Colección de Avatares Exclusiva", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Diseños de Bordes Exclusivos", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Temas Visuales Completos por Región", true, isHighlight = true, icon = Icons.Default.Palette),
                    FeatureItem("Partículas Mágicas (Barra de Navegación)", true, isHighlight = true, icon = Icons.Default.AutoAwesome),
                    FeatureItem("Guardar Draft y Favoritos", true, isHighlight = true, icon = Icons.Default.Star)
                ),
                onSubscribe = {
                    Toast.makeText(context, "Compras in-app temporalmente deshabilitadas por seguridad.", Toast.LENGTH_LONG).show()
                    scope.launch {
                        sheetState.hide()
                        onDismiss()
                    }
                }
            )`;

code = code.replace(regex, replacement);
fs.writeFileSync('app/src/main/java/com/example/ui/components/SubscriptionPlansBottomSheet.kt', code);
