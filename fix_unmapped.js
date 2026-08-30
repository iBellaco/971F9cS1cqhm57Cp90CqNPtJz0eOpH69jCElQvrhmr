const fs = require('fs');

const urls = fs.readFileSync('urls.txt', 'utf8').trim().split('\n').filter(Boolean);
const c1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const c2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const all = [...c1, ...c2];

const mappedSlugs = new Set();
all.forEach(c => c.builds.forEach(b => mappedSlugs.add(b.id)));

const unmapped = [];
urls.forEach(u => {
    const slug = u.split('/').pop().replace(/[^a-zA-Z0-9]/g, '_');
    if (!mappedSlugs.has(slug)) {
        unmapped.push(u);
    }
});

console.log("Unmapped count:", unmapped.length);
unmapped.forEach(u => console.log(u));
