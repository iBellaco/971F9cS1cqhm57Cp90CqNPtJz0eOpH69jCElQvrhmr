const fs = require('fs');

const data = JSON.parse(fs.readFileSync('wr_meta_data.json', 'utf-8'));
let ktFile = fs.readFileSync('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'utf-8');

let matchCount = 0;

data.forEach(item => {
    // Escape string for regex
    // We look for:
    // add(WildRiftItem("...", "...", ItemCategory.PHYSICAL, 2700, "...", "...", "..."))
    // where the id in kotlin might be item.id + "_physical" or item.id + "_magic" etc.
    
    // Instead of regex on the whole line, let's use a simpler regex
    // find add(WildRiftItem("ITEM_ID_any", ...
    let searchId = item.id;
    // Handle specific mappings if needed (eg. blade_of_the_ruined_king -> blade_of_the_ruined_king_physical)
    
    // Regex: add\(WildRiftItem\("(.*?)", "(.*?)", (ItemCategory\.[A-Z_]+), ([0-9]+), "(.*?)", "(.*?)", "(.*?)"\)\)
    const regex = new RegExp(`add\\(WildRiftItem\\("(${searchId}_[a-z]+)", "(.*?)", (ItemCategory\\.[A-Z_]+), ([0-9]+), "(.*?)", "(.*?)", "(.*?)"\\)\\)`, 'g');
    
    ktFile = ktFile.replace(regex, (match, p1, p2, p3, p4, p5, p6, p7) => {
        matchCount++;
        let cleanDesc = item.description.replace(/"/g, '\\"').replace(/\n/g, ' ');
        return `add(WildRiftItem("${p1}", "${p2}", ${p3}, ${p4}, "${p5}", "${cleanDesc}", "${item.iconUrl}"))`;
    });
});

fs.writeFileSync('app/src/main/java/com/example/data/WildRiftItemsData.kt', ktFile);
console.log(`Updated ${matchCount} items in WildRiftItemsData.kt`);

