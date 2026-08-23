const fs = require('fs');

const data = JSON.parse(fs.readFileSync('wr_meta_data.json', 'utf-8'));
let ktFile = fs.readFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', 'utf-8');

let matchCount = 0;

data.forEach(item => {
    let searchId = item.id;
    if (searchId === 'grasp_of_undying') searchId = 'grasp_undying';
    if (searchId === 'ice_overlord') searchId = 'glacial_augment';
    
    const regex = new RegExp(`(id\\s*=\\s*"${searchId}"\\s*,\\s*\\n\\s*name\\s*=\\s*".*?"\\s*,\\s*\\n\\s*category\\s*=\\s*".*?"\\s*,\\s*\\n\\s*iconUrl\\s*=\\s*)".*?"(\\s*,\\s*\\n\\s*description\\s*=\\s*)".*?"`, 's');
    
    if (regex.test(ktFile)) {
        let cleanDesc = item.description.replace(/"/g, '\\"').replace(/\n/g, ' ');
        ktFile = ktFile.replace(regex, `$1"${item.iconUrl}"$2"${cleanDesc}"`);
        matchCount++;
    }
});

fs.writeFileSync('app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt', ktFile);
console.log(`Updated ${matchCount} runes/spells in WildRiftSpellsAndRunes.kt`);

