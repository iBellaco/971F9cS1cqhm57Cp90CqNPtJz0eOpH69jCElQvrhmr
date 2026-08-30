const fs = require('fs');

function inferBuildFromUrl(champRaw, url) {
    let role = "";
    if (url.includes('-jungle-')) role = "Jungla";
    else if (url.includes('-top-')) role = "Barón (Top)";
    else if (url.includes('-mid-')) role = "Central (Mid)";
    else if (url.includes('-adc-')) role = "Dúo (ADC)";
    else if (url.includes('-support-')) role = "Soporte";
    
    let reason = "Estándar";
    if (url.includes('est-ndar')) reason = "Estándar";
    else if (url.includes('da-o-explosivo') || url.includes('eplosivo')) reason = "Daño Explosivo";
    else if (url.includes('da-o-y-aguante') || url.includes('da-o-con-aguante')) reason = "Daño y Aguante";
    else if (url.includes('cr-ticos')) reason = "Críticos";
    else if (url.includes('tanque')) reason = "Tanque";
    else if (url.includes('da-o-continuo') || url.includes('da-o-sostenido')) reason = "Daño Continuo";
    else if (url.includes('letalidad')) reason = "Letalidad";
    else if (url.includes('ap')) reason = "Poder de Habilidad (AP)";
    
    const title = role ? "Build " + reason + " (" + role + ")" : "Build " + reason;
    return {
        title: title,
        url: url
    };
}

function main() {
    const urlsText = fs.readFileSync('urls.txt', 'utf8').trim().split('\n');
    const part1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
    const part2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
    
    const champBuildsMap = {};

    for (let line of urlsText) {
        if (!line.trim()) continue;
        const parts = line.split('|').map(s => s.trim());
        const champRaw = parts[0];
        const url = parts[1];
        if (!url) continue;

        const urlMatch = url.match(/\/builds\/\d+-([a-z0-9-]+)/);
        if (!urlMatch) continue;
        
        let urlChampPart = urlMatch[1].split('-jungle')[0].split('-top')[0].split('-mid')[0].split('-adc')[0].split('-support')[0];
        
        const cleanName = champRaw.toLowerCase().replace(/[^a-z0-9]/g, '');
        
        let matchedChamp = [...part1, ...part2].find(c => {
            const id = c.id.toLowerCase();
            return id.replace(/[^a-z0-9]/g, '') === cleanName || id.includes(urlChampPart) || urlChampPart.includes(id);
        });
        
        if (!matchedChamp) continue;

        const buildInfo = inferBuildFromUrl(champRaw, url);
        
        if (!champBuildsMap[matchedChamp.id]) {
            champBuildsMap[matchedChamp.id] = [];
        }
        
        champBuildsMap[matchedChamp.id].push({
            title: buildInfo.title,
            items: matchedChamp.coreItems.concat(matchedChamp.situationalItems).slice(0, 8),
            runes: matchedChamp.recommendedRunes,
            spells: matchedChamp.recommendedSpells
        });
    }

    const updateChamp = (c) => {
        if (champBuildsMap[c.id]) {
            c.builds = champBuildsMap[c.id];
        }
        return c;
    };

    const newPart1 = part1.map(updateChamp);
    const newPart2 = part2.map(updateChamp);

    fs.writeFileSync('app/src/main/res/raw/champions_part1.json', JSON.stringify(newPart1, null, 2));
    fs.writeFileSync('app/src/main/res/raw/champions_part2.json', JSON.stringify(newPart2, null, 2));
    
    console.log("Successfully extracted static builds without fetching.");
}

main();
