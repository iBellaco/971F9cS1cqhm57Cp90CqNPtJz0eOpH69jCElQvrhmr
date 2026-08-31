const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', 'utf8');

code = code.replace(/LaneRole\.TOP -> listOf\("Destello", "Teleportación"\)/g, 'LaneRole.TOP -> listOf("Destello", "Ignición")');
code = code.replace(/LaneRole\.TOP -> if \(unique\.contains\("Destello"\)\) "Teleportación" else "Destello"/g, 'LaneRole.TOP -> if (unique.contains("Destello")) "Ignición" else "Destello"');

fs.writeFileSync('app/src/main/java/com/example/util/ChampionRoleAdapter.kt', code);
