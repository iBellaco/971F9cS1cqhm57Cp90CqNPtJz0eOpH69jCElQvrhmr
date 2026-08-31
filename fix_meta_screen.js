const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'utf8');

code = code.replace(/"teleport" -> "Potente para campeones de carril de Barón para mantener presión dividida y unirse inmediatamente a peleas de objetivos."/g, '"heal" -> "Ideal para el Tirador (ADC) en la línea de Dragón para sobrevivir al burst y salvar al soporte en 2vs2."');

fs.writeFileSync('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', code);
