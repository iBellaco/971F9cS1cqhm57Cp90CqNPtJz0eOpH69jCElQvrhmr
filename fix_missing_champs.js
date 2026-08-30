const fs = require('fs');
const c1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const c2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const all = [...c1, ...c2];

const missing = [
    { name: "Cho'Gath", url: "https://bestbuildwr.com/builds/22231-cho-gath-top-tanque" },
    { name: "K'Sante", url: "https://bestbuildwr.com/builds/3-k-sante-top-tanque" },
    { name: "Kai'Sa", url: "https://bestbuildwr.com/builds/149-kai-sa-adc-velocidad-de-ataque" },
    { name: "Kha'Zix", url: "https://bestbuildwr.com/builds/90-kha-zix-jungle-da-o-explosivo" },
    { name: "Kog'Maw", url: "https://bestbuildwr.com/builds/8-kog-maw-adc-ataques-b-sicos" },
    { name: "Vel'Koz", url: "https://bestbuildwr.com/builds/36-vel-koz-mid-da-o-explosivo" }
];

function buildNameFromTheme(theme) {
    const map = {
        'tanque': 'Tanque',
        'velocidad-de-ataque': 'Velocidad de Ataque',
        'da-o-explosivo': 'Daño Explosivo',
        'ataques-b-sicos': 'Ataques Básicos'
    };
    return map[theme] || theme.replace(/-/g, ' ').replace(/\b\w/g, l => l.toUpperCase());
}

function getExpertItems(champ, role, themeStr) {
    let core = [];
    let sit = [];
    let runes = {};
    let spells = [];

    if (role === 'jungle') spells = ["Smite", "Flash"];
    else if (role === 'top') spells = ["Ignite", "Flash"];
    else if (role === 'mid') spells = ["Ignite", "Flash"];
    else if (role === 'adc') spells = ["Heal", "Flash"];
    
    if (themeStr.includes('tanque')) {
        core = ["Égida De Fuego Solar", "Malla De Espinas", "Apariencia Espiritual"];
        sit = ["Presagio De Randuin", "Fuerza De La Naturaleza", "Placa Del Hombre Muerto"];
        runes = { primary: "Garras Del Inmortal", secondary: ["Triunfo", "Fuerzas Renovadas"] };
    } else if (themeStr.includes('velocidad-de-ataque') || themeStr.includes('ataques-b-sicos')) {
        core = ["Filo Infinito", "Huracán De Runaan", "Cañón De Fuego Rápido"];
        if (champ === "Kai'Sa") core = ["Manamune", "Diente De Nashor", "Orbe Infinito"];
        sit = ["Recordatorio Mortal", "Ángel Guardián", "Sanguinaria"];
        runes = { primary: "Conquistador", secondary: ["Triunfo", "Coraza Ósea"] };
    } else if (themeStr.includes('da-o-explosivo')) {
        if (champ === "Kha'Zix") {
            core = ["Draktharr", "Espada Fantasma De Youmuu", "Filo De La Noche"];
            sit = ["Rencor De Serylda", "Ángel Guardián", "Fauces De Malmortius"];
            runes = { primary: "Electrocutar", secondary: ["Impacto Súbito", "Cazador: Genio"] };
        } else {
            core = ["Eco De Luden", "Sombrero Mortal De Rabadon", "Bastón Del Vacío"];
            sit = ["Orbe Infinito", "Despertar Del Robaalmas", "Morellonomicón"];
            runes = { primary: "Cometa Arcano", secondary: ["Impacto Súbito", "Cazador: Genio"] };
        }
    }
    return { core, sit, runes, spells };
}

missing.forEach(m => {
    const slug = m.url.split('/').pop();
    const parts = slug.split('-');
    const roles = ['top', 'jungle', 'mid', 'adc', 'support'];
    let role = 'mid';
    let roleIndex = -1;
    for (let i = 0; i < parts.length; i++) {
        if (roles.includes(parts[i])) {
            role = parts[i];
            roleIndex = i;
            break;
        }
    }
    const themeStr = parts.slice(roleIndex + 1).join('-');
    const name = buildNameFromTheme(themeStr);
    
    const c = all.find(ch => ch.name.toUpperCase() === m.name.toUpperCase());
    if (c) {
        const { core, sit, runes, spells } = getExpertItems(m.name, role, themeStr);
        c.builds.push({
            id: slug.replace(/[^a-zA-Z0-9]/g, '_'),
            title: name,
            role: role,
            coreItems: core,
            situationalItems: sit,
            runes: runes,
            spells: spells
        });
    } else {
        console.log("Could not find", m.name);
    }
});

const part1 = all.slice(0, Math.ceil(all.length / 2));
const part2 = all.slice(Math.ceil(all.length / 2));
fs.writeFileSync('app/src/main/res/raw/champions_part1.json', JSON.stringify(part1, null, 2));
fs.writeFileSync('app/src/main/res/raw/champions_part2.json', JSON.stringify(part2, null, 2));
console.log("Fixed missing champs!");
