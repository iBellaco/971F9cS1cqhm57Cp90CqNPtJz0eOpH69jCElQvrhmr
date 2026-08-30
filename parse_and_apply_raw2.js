const fs = require('fs');

const raw = JSON.parse(fs.readFileSync('bbwr_builds_raw.json', 'utf8'));
const c1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const c2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const allChamps = [...c1, ...c2];

allChamps.forEach(c => c.builds = []);

let appliedCount = 0;
for (const [url, build] of Object.entries(raw)) {
    if (!build || !build.champion || !build.champion.name) continue;
    const champName = build.champion.name.toUpperCase();
    const targetChamp = allChamps.find(c => c.name.toUpperCase() === champName);
    
    if (targetChamp) {
        const id = build.slug.replace(/[^a-zA-Z0-9]/g, '_');
        const role = (build.roles && build.roles.length > 0) ? build.roles[0] : 'mid';
        
        let coreItems = [];
        let situationalItems = [];
        let runes = { primary: "Conquistador", secondary: [] };
        
        if (build.display) {
            if (build.display.keystone && build.display.keystone.name) {
                runes.primary = build.display.keystone.name;
            }
            if (build.display.runeSubgroups) {
                runes.secondary = build.display.runeSubgroups.map(r => r.name);
            }
        } else if (build.items) {
            const validItems = build.items.filter(i => i && i.imageUrl);
            coreItems = validItems.slice(0, 3).map(i => {
                const parts = i.imageUrl.split('/');
                return parts[parts.length - 1].split('.')[0].replace(/_hanu_bbwr|_hanu/g, '').replace(/_/g, ' ');
            });
            situationalItems = validItems.slice(3).map(i => {
                const parts = i.imageUrl.split('/');
                return parts[parts.length - 1].split('.')[0].replace(/_hanu_bbwr|_hanu/g, '').replace(/_/g, ' ');
            });
            if (build.keystone && build.keystone.imageUrl) {
                const parts = build.keystone.imageUrl.split('/');
                runes.primary = parts[parts.length - 1].split('.')[0].replace(/_hanu_bbwr|_hanu/g, '').replace(/_/g, ' ');
            }
        }
        
        if (coreItems.length === 0) {
             const t = build.slug.toLowerCase();
             let c = ["Fuerza De Trinidad", "Cuchilla Oscura", "Baile De La Muerte"];
             let s = ["Ángel Guardián", "Rencor De Serylda"];
             
             if (t.includes('cr-tico') || t.includes('velocidad-de-ataque')) {
                 c = ["Filo Infinito", "Huracán De Runaan", "Cañón De Fuego Rápido"];
                 s = ["Recordatorio Mortal", "Ángel Guardián"];
             } else if (t.includes('tanque') || t.includes('vida')) {
                 c = ["Égida De Fuego Solar", "Malla De Espinas", "Apariencia Espiritual"];
                 s = ["Presagio De Randuin", "Fuerza De La Naturaleza"];
             } else if (t.includes('explosivo') || t.includes('ap')) {
                 c = ["Eco De Luden", "Sombrero Mortal De Rabadon", "Bastón Del Vacío"];
                 s = ["Orbe Infinito", "Morellonomicón"];
             }
             coreItems = c;
             situationalItems = s;
        }
        
        let spells = ["Ignite", "Flash"];
        if (role === 'jungle') spells = ["Smite", "Flash"];
        else if (role === 'adc') spells = ["Heal", "Flash"];
        else if (role === 'support') spells = ["Exhaust", "Flash"];

        let finalTitle = build.short_title || build.name || "Build";
        // Handle weird title outputs
        if (finalTitle === 'Estándar' && build.slug.includes('est-ndar')) finalTitle = "Estándar";
        else finalTitle = finalTitle.charAt(0).toUpperCase() + finalTitle.slice(1).replace(/-/g, ' ');

        targetChamp.builds.push({
            id: id,
            title: finalTitle,
            role: role,
            coreItems: coreItems,
            situationalItems: situationalItems,
            runes: runes,
            spells: spells
        });
        appliedCount++;
    }
}

const part1 = allChamps.slice(0, Math.ceil(allChamps.length / 2));
const part2 = allChamps.slice(Math.ceil(allChamps.length / 2));
fs.writeFileSync('app/src/main/res/raw/champions_part1.json', JSON.stringify(part1, null, 2));
fs.writeFileSync('app/src/main/res/raw/champions_part2.json', JSON.stringify(part2, null, 2));
console.log("Success! Applied: " + appliedCount);
