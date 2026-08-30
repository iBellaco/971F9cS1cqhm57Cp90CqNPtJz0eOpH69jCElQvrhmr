const fs = require('fs');
const part1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const part2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const allChamps = [...part1, ...part2];

const regionMap = {
    'ahri': 'Jonia', 'akali': 'Jonia', 'irelia': 'Jonia', 'yasuo': 'Jonia', 'yone': 'Jonia', 'zed': 'Jonia', 'jhin': 'Jonia', 'kennen': 'Jonia', 'lee_sin': 'Jonia', 'master_yi': 'Jonia', 'rakan': 'Jonia', 'sett': 'Jonia', 'shen': 'Jonia', 'syndra': 'Jonia', 'varus': 'Jonia', 'xayah': 'Jonia', 'wukong': 'Jonia', 'karma': 'Jonia', 'lillia': 'Jonia',
    'jinx': 'Zaun', 'ekko': 'Zaun', 'dr_mundo': 'Zaun', 'singed': 'Zaun', 'urgot': 'Zaun', 'warwick': 'Zaun', 'ziggs': 'Zaun', 'twitch': 'Zaun',
    'vi': 'Piltóver', 'caitlyn': 'Piltóver', 'camille': 'Piltóver', 'ezreal': 'Piltóver', 'jayce': 'Piltóver', 'orianna': 'Piltóver', 'seraphine': 'Piltóver', 'heimerdinger': 'Piltóver',
    'garen': 'Demacia', 'lux': 'Demacia', 'fiora': 'Demacia', 'galio': 'Demacia', 'jarvan_iv': 'Demacia', 'lucian': 'Demacia', 'morgana': 'Demacia', 'shyvana': 'Demacia', 'sona': 'Demacia', 'sylas': 'Demacia', 'vayne': 'Demacia', 'xin_zhao': 'Demacia',
    'darius': 'Noxus', 'katarina': 'Noxus', 'samira': 'Noxus', 'sion': 'Noxus', 'swain': 'Noxus', 'talon': 'Noxus', 'draven': 'Noxus', 'kled': 'Noxus', 'riven': 'Noxus', 'vladimir': 'Noxus',
    'ashe': 'Freljord', 'volibear': 'Freljord', 'braum': 'Freljord', 'gragas': 'Freljord', 'lissandra': 'Freljord', 'nunu_willump': 'Freljord', 'olaf': 'Freljord', 'ornn': 'Freljord', 'sejuani': 'Freljord', 'tryndamere': 'Freljord',
    'thresh': 'Islas de la Sombra', 'viego': 'Islas de la Sombra', 'hecarim': 'Islas de la Sombra', 'kalista': 'Islas de la Sombra', 'gwen': 'Islas de la Sombra', 'maokai': 'Islas de la Sombra',
    'aatrox': 'Los Oscuros', 'kayn': 'Jonia', // shadow assassin/darkin
    'teemo': 'Ciudad de Bandle', 'corki': 'Ciudad de Bandle', 'lulu': 'Ciudad de Bandle', 'tristana': 'Ciudad de Bandle', 'veigar': 'Ciudad de Bandle', 'yuumi': 'Ciudad de Bandle',
    'kaisa': 'El Vacío', 'kassadin': 'El Vacío', 'kha_zix': 'El Vacío', 'vel_koz': 'El Vacío',
    'aurelion_sol': 'Targon', 'diana': 'Targon', 'leona': 'Targon', 'pantheon': 'Targon', 'soraka': 'Targon', 'zoe': 'Targon',
    'pyke': 'Aguas Esturbias', 'miss_fortune': 'Aguas Esturbias', 'nautilus': 'Aguas Esturbias', 'graves': 'Aguas Esturbias', 'twisted_fate': 'Aguas Esturbias', 'nami': 'Aguas Esturbias', 'fizz': 'Aguas Esturbias',
    'akshan': 'Shurima', 'amumu': 'Shurima', 'rammus': 'Shurima', 'renekton': 'Shurima', 'nasus': 'Shurima', 'sivir': 'Shurima'
};

let code = ``;

for (let champ of allChamps) {
    let region = regionMap[champ.id] || "Runaterra";
    let summary = champ.summary.replace(/"/g, "'").replace(/\n/g, ' ').substring(0, 95) + "...";
    code += `        AvatarItem(\n`;
    code += `            id = "${champ.id}",\n`;
    code += `            name = "${champ.name}",\n`;
    code += `            title = "${champ.title}",\n`;
    code += `            region = "${region}",\n`;
    code += `            rarity = "Épico",\n`;
    code += `            imageUrl = "${champ.avatarUrl}",\n`;
    code += `            borderHex = "#C8AA6E",\n`;
    code += `            description = "${summary}"\n`;
    code += `        ),\n`;
}

fs.writeFileSync('generated_avatars.txt', code);
