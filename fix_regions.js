const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', 'utf8');

// Change Poro Guardián from Freljord to Variados
code = code.replace(/id = "default_poro",\s*name = "Poro Guardián",\s*title = "Espíritu de la Grieta",\s*region = "Freljord",/g, 
'id = "default_poro",\n        name = "Poro Guardián",\n        title = "Espíritu de la Grieta",\n        region = "Variados",');

// Change specific regions to Variados
code = code.replace(/region = "Celestial"/g, 'region = "Variados"');
code = code.replace(/region = "Flor Espiritual"/g, 'region = "Variados"');
code = code.replace(/region = "K\/DA"/g, 'region = "Variados"');
code = code.replace(/region = "Los Oscuros"/g, 'region = "Variados"');
code = code.replace(/region = "Monstruos Épicos"/g, 'region = "Variados"');
code = code.replace(/region = "Poros"/g, 'region = "Variados"');

fs.writeFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', code);
