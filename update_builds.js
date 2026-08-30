const fs = require('fs');
const https = require('https');

// Helper to capitalize strings properly according to catalog
function formatItemName(name) {
    if (!name) return "";
    name = name.toLowerCase().trim();
    if (name === "fuerza de trinidad") return "Fuerza de trinidad";
    if (name === "espada del rey arruinado") return "Espada del rey arruinado";
    if (name === "corazón de acero") return "Corazón de acero";
    // General capitalization rule
    return name.charAt(0).toUpperCase() + name.slice(1);
}

function fetchUrl(url) {
    return new Promise((resolve, reject) => {
        // use native fetch if available
        fetch(url).then(res => {
            if (!res.ok) throw new Error("Status " + res.status);
            return res.text();
        }).then(resolve).catch(reject);
    });
}

async function scrapeBuild(url) {
    try {
        const html = await fetchUrl(url);
        const match = html.match(/<script id="__NEXT_DATA__" type="application\/json">([\s\S]*?)<\/script>/);
        if (!match) return null;
        const data = JSON.parse(match[1]);
        const build = data.props.pageProps.build;
        if (!build) return null;

        // items
        const items = [];
        if (build.items && Array.isArray(build.items)) {
            build.items.forEach(i => {
                if (i.item && i.item.name_es) items.push(formatItemName(i.item.name_es));
            });
        }
        
        // boots upgrade (could be active item)
        if (build.active_item && build.active_item.name_es) {
            // usually boot enchant
            items.push(formatItemName(build.active_item.name_es));
        }

        // runes
        const runesList = [];
        if (build.runes && Array.isArray(build.runes)) {
            build.runes.forEach(r => {
                if (r.rune && r.rune.name_es) runesList.push(formatItemName(r.rune.name_es));
            });
        }
        const runesStr = runesList.join(", ");

        // spells
        let spells = [];
        if (build.spells && Array.isArray(build.spells)) {
            build.spells.forEach(s => {
                if (s.spell && s.spell.name_es) spells.push(formatItemName(s.spell.name_es));
            });
        }
        
        // Remove duplicate spells
        spells = [...new Set(spells)];

        // Title parsing
        let title = build.name_es || build.name_en || "Build";
        // Let's capitalize the title
        title = title.split(' ').map(w => w.charAt(0).toUpperCase() + w.slice(1).toLowerCase()).join(' ');

        return {
            title: title,
            items: items.slice(0, 8), // Ensure max 8 items
            runes: runesStr,
            spells: spells
        };
    } catch (e) {
        console.error("Error fetching", url, e.message);
        return null;
    }
}

async function main() {
    const urlsText = fs.readFileSync('urls.txt', 'utf8').trim().split('\n');
    const part1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
    const part2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
    
    // Build a map of champId -> [builds]
    const champBuildsMap = {};

    for (let line of urlsText) {
        if (!line.trim()) continue;
        const [champRaw, url] = line.split('|').map(s => s.trim());
        if (!url) continue;

        // Parse champ id from the URL e.g. 143-aatrox-jungle-est-ndar
        // Usually it's after the number: 143-aatrox-
        const urlMatch = url.match(/\/builds\/\d+-([a-z0-9-]+)/);
        if (!urlMatch) {
            console.log("Could not parse URL:", url);
            continue;
        }
        
        let urlChampPart = urlMatch[1].split('-jungle')[0].split('-top')[0].split('-mid')[0].split('-adc')[0].split('-support')[0];
        
        // Let's try matching the champ name exactly from the json
        const cleanName = champRaw.toLowerCase().replace(/[^a-z0-9]/g, '');
        
        // find champ in part1 or part2
        let matchedChamp = [...part1, ...part2].find(c => {
            const id = c.id.toLowerCase();
            return id.replace(/[^a-z0-9]/g, '') === cleanName || id.includes(urlChampPart) || urlChampPart.includes(id);
        });
        
        if (!matchedChamp) {
            console.log("Not matched:", champRaw, url);
            continue;
        }

        console.log("Fetching", url, "for", matchedChamp.id);
        const buildData = await scrapeBuild(url);
        if (buildData) {
            if (!champBuildsMap[matchedChamp.id]) {
                champBuildsMap[matchedChamp.id] = [];
            }
            champBuildsMap[matchedChamp.id].push(buildData);
        }
        // Small delay
        await new Promise(r => setTimeout(r, 200));
    }

    // Apply the builds to the champions
    const updateChamp = (c) => {
        if (champBuildsMap[c.id]) {
            c.builds = champBuildsMap[c.id];
            
            // Also override the default ones so it looks good when not using the adapter directly (fallback)
            if (c.builds.length > 0) {
                c.coreItems = c.builds[0].items.slice(0, 6);
                c.situationalItems = c.builds[0].items.slice(6, 8);
                c.recommendedRunes = c.builds[0].runes;
                c.recommendedSpells = c.builds[0].spells;
            }
        }
        return c;
    };

    const newPart1 = part1.map(updateChamp);
    const newPart2 = part2.map(updateChamp);

    fs.writeFileSync('app/src/main/res/raw/champions_part1.json', JSON.stringify(newPart1, null, 2));
    fs.writeFileSync('app/src/main/res/raw/champions_part2.json', JSON.stringify(newPart2, null, 2));
    
    console.log("Successfully updated builds.");
}

main();
