const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/screens/OnboardingScreen.kt', 'utf8');

const regex = /premiumFeatures = listOf\([\s\S]*?\)/m;
const replacement = `premiumFeatures = listOf(
                "Guardado de Drafts e Historial de Partidas",
                "Repertorio de Campeones Favoritos",
                "Personalización de Temas Visuales",
                "Colección de Avatares Exclusiva",
                "Diseños de Bordes Exclusivos",
                "Partículas Mágicas (Barra de Navegación)"
            )`;
code = code.replace(regex, replacement);

fs.writeFileSync('app/src/main/java/com/example/ui/screens/OnboardingScreen.kt', code);
