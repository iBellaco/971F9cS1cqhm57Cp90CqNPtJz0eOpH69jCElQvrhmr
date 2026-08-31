const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/data/WildRiftRepository.kt', 'utf8');

code = code.replace(/Minuto 4:00/g, 'Minuto 5:00');
code = code.replace(/Reaparece cada 4:00/g, 'Reaparece cada 5:00');
code = code.replace(/Minuto 1:25/g, 'Minuto 1:15'); // Scuttle in WR is 1:15 I think, but let's keep 1:25 if unsure

fs.writeFileSync('app/src/main/java/com/example/data/WildRiftRepository.kt', code);
