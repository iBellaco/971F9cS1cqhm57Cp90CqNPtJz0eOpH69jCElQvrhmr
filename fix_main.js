const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/MainActivity.kt', 'utf8');

code = code.replace(/\/\/\s*Ambient Runic Particles Floating across Bottom Navigation Bar(?: in background)?\n\s*RunicNavBarParticleAnimation\(\n\s*particleCount = 1[46],\n\s*accentColor = .*?\n\s*\)/g, 
function(match) {
    return 'if (com.example.ui.theme.AppThemeManager.isParticlesEnabled) {\n                ' + match + '\n                }';
});

fs.writeFileSync('app/src/main/java/com/example/MainActivity.kt', code);
console.log("MainActivity replaced");
