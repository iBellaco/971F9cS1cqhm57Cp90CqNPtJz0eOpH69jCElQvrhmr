const fs = require('fs');

const part1 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part1.json', 'utf8'));
const part2 = JSON.parse(fs.readFileSync('app/src/main/res/raw/champions_part2.json', 'utf8'));
const allChamps = [...part1, ...part2];

let code = ``;

for (let champ of allChamps) {
    let region = "Runaterra";
    // basic region guessing or just set them to their primaryRole or something
    // wait, we can just use "Runaterra" or keep it simple.
    // Or we can extract it if we have it? We don't have region in the json. We can just use "Runaterra"
    // But wait, the user wants 141 champions. So I will generate the AvatarItem code for each.
    code += `        AvatarItem(\n`;
    code += `            id = "${champ.id}",\n`;
    code += `            name = "${champ.name}",\n`;
    code += `            title = "${champ.title}",\n`;
    code += `            region = "Runaterra",\n`;
    code += `            rarity = "Épico",\n`;
    code += `            imageUrl = "${champ.avatarUrl}",\n`;
    code += `            borderHex = "#C8AA6E",\n`;
    code += `            description = "${champ.summary.replace(/"/g, "'").substring(0, 100)}..."\n`;
    code += `        ),\n`;
}

fs.writeFileSync('generated_avatars.txt', code);
console.log("Done. Total: " + allChamps.length);
