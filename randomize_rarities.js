const fs = require('fs');

let content = fs.readFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', 'utf8');

// We will use a hash function to assign rarity so it's deterministic
function getRarity(id) {
    if (id === 'default_poro') return 'Clásico';
    const rarities = ['Común', 'Raro', 'Épico', 'Legendario', 'Mítico'];
    const weights = [30, 35, 20, 10, 5]; // Percentages
    
    // Hash string
    let hash = 0;
    for (let i = 0; i < id.length; i++) {
        hash = id.charCodeAt(i) + ((hash << 5) - hash);
    }
    hash = Math.abs(hash) % 100;
    
    let sum = 0;
    for (let i = 0; i < weights.length; i++) {
        sum += weights[i];
        if (hash < sum) return rarities[i];
    }
    return 'Común';
}

// Regex to match and replace rarity
const avatarBlocks = content.split('AvatarItem(');
let newContent = avatarBlocks[0];

for (let i = 1; i < avatarBlocks.length; i++) {
    let block = avatarBlocks[i];
    
    // Extract ID
    const idMatch = block.match(/id\s*=\s*"([^"]+)"/);
    if (idMatch) {
        const id = idMatch[1];
        const newRarity = getRarity(id);
        
        block = block.replace(/rarity\s*=\s*"[^"]+"/, `rarity = "${newRarity}"`);
    }
    
    newContent += 'AvatarItem(' + block;
}

fs.writeFileSync('app/src/main/java/com/example/data/AvatarCatalog.kt', newContent);
console.log("Rarities updated.");
