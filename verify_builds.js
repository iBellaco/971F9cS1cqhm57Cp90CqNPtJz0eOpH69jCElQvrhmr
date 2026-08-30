const fs = require('fs');
const c1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const c2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const all = [...c1, ...c2];

let totalBuilds = 0;
all.forEach(c => {
    totalBuilds += c.builds.length;
    if (c.name === 'Aatrox') {
        console.log('Aatrox builds:', JSON.stringify(c.builds, null, 2));
    }
    if (c.name === 'Ahri') {
        console.log('Ahri builds:', JSON.stringify(c.builds, null, 2));
    }
});
console.log(`Total Builds in DB: ${totalBuilds}`);
