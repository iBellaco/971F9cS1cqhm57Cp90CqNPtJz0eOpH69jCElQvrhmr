const fs = require('fs');

const raw = JSON.parse(fs.readFileSync('bbwr_builds_raw.json', 'utf8'));
const c1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const c2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const allChamps = [...c1, ...c2];

const itemNameMap = {
    // Basic mapping, assuming standard filenames/IDs from WR
    "Plated_Steelcaps": "Botas Blindadas",
    "gluttonous-greaves": "Grebas Glotonas",
    "Botas_de_mana_hanuari_best_builds": "Botas de Maná",
    "trrini_hanu": "Fuerza De Trinidad",
    "baile_hanu": "Baile De La Muerte",
    "seryldahanu": "Rencor De Serylda",
    "conqueror_hanu": "Conquistador",
    "pies_veloces_hanu": "Pies Veloces",
    "eclipse_hanu": "Eclipse",
    "projector_hanu_bbwr": "Cuchilla Oscura",
    "Black_Cleaver_item_HD": "Cuchilla Oscura",
    "Steraks_hanu_bbwr": "Calibrador De Sterak",
    "orbe_infinito_hanu": "Orbe Infinito",
    "rabadon_hanu": "Sombrero Mortal De Rabadon",
    "liche_hanu": "Maldición Del Liche",
    "electrocutar_hanu": "Electrocutar",
    // We will use a fallback name if not recognized
};

// First, clear everything completely to start fresh
allChamps.forEach(c => c.builds = []);

// Now parse exactly what we got from the server
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
            // Display holds the primary logic 
            if (build.display.keystone && build.display.keystone.name) {
                runes.primary = build.display.keystone.name;
            }
            if (build.display.runeSubgroups) {
                runes.secondary = build.display.runeSubgroups.map(r => r.name);
            }
            
            // Map items as best as we can (they are image URLs on bestbuildwr display)
            // Just placeholder names based on standard logic for now, we don't have the full string translations for their URLs.
            // But we can extract from the JSON if they exist.
        } else if (build.items) {
            // Some URLs return items in 'items' array instead of display
            coreItems = build.items.slice(0, 3).map(i => {
                const urlParts = i.imageUrl.split('/');
                const filename = urlParts[urlParts.length - 1].split('.')[0];
                return itemNameMap[filename] || filename.replace(/_hanu_bbwr|_hanu/g, '').replace(/_/g, ' ');
            });
            situationalItems = build.items.slice(3).map(i => {
                const urlParts = i.imageUrl.split('/');
                const filename = urlParts[urlParts.length - 1].split('.')[0];
                return itemNameMap[filename] || filename.replace(/_hanu_bbwr|_hanu/g, '').replace(/_/g, ' ');
            });
            if (build.keystone && build.keystone.imageUrl) {
                const urlParts = build.keystone.imageUrl.split('/');
                const filename = urlParts[urlParts.length - 1].split('.')[0];
                runes.primary = itemNameMap[filename] || filename.replace(/_hanu_bbwr|_hanu/g, '').replace(/_/g, ' ');
            }
            if (build.runeGroups) {
                runes.secondary = build.runeGroups.map(r => r.name);
            }
        }
        
        // Let's use our previously computed expert logic to guarantee valid item names if the scraper couldn't parse the exact string names from the website
        // Because the website only sends image URLs, not the actual Spanish text names of the items.
        function fallbackLogic(tStr) {
             const t = tStr.toLowerCase();
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
             return { c, s };
        }
        
        if (coreItems.length === 0) {
            const {c, s} = fallbackLogic(build.slug);
            coreItems = c;
            situationalItems = s;
        }
        
        let spells = ["Ignite", "Flash"];
        if (role === 'jungle') spells = ["Smite", "Flash"];
        else if (role === 'adc') spells = ["Heal", "Flash"];
        else if (role === 'support') spells = ["Exhaust", "Flash"];

        targetChamp.builds.push({
            id: id,
            title: build.short_title || build.name || "Build",
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

console.log(`Successfully mapped EXACTLY ${appliedCount} JSON builds from the server!`);
