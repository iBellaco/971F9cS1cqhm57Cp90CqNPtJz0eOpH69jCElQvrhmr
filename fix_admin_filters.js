const fs = require('fs');
let content = fs.readFileSync('app/src/main/java/com/example/ui/components/AdminGiftAvatarDialog.kt', 'utf8');

content = content.replace('val regions = listOf("Todos", "Jonia", "Zaun / Piltóver", "Demacia / Noxus", "Freljord / Islas", "Mascotas / Épicos")',
'val regions = listOf("Todos", "Jonia", "Zaun / Piltóver", "Demacia / Noxus", "Freljord / Shurima", "Runaterra / Varios")');

content = content.replace(
    /"Freljord \/ Islas" -> avatar.region.contains\("Freljord", ignoreCase = true\) \|\| avatar.region.contains\("Islas", ignoreCase = true\) \|\| avatar.region.contains\("Oscuros", ignoreCase = true\) \|\| avatar.region.contains\("Vacío", ignoreCase = true\)/,
    `"Freljord / Shurima" -> avatar.region.contains("Freljord", ignoreCase = true) || avatar.region.contains("Shurima", ignoreCase = true)`
);

content = content.replace(
    /"Mascotas \/ Épicos" -> avatar.region.contains\("Grieta", ignoreCase = true\) \|\| avatar.region.contains\("Bandle", ignoreCase = true\) \|\| avatar.region.contains\("Arcade", ignoreCase = true\) \|\| avatar.isDefault/,
    `"Runaterra / Varios" -> avatar.region.contains("Runaterra", ignoreCase = true) || avatar.region.contains("Islas", ignoreCase = true) || avatar.region.contains("Targon", ignoreCase = true) || avatar.region.contains("Aguas", ignoreCase = true) || avatar.region.contains("Vacío", ignoreCase = true) || avatar.region.contains("Oscuros", ignoreCase = true) || avatar.region.contains("Bandle", ignoreCase = true) || avatar.isDefault`
);

fs.writeFileSync('app/src/main/java/com/example/ui/components/AdminGiftAvatarDialog.kt', content);
console.log("Updated AdminGiftAvatarDialog");
