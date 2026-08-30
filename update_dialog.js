const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

code = code.replace(
    'val canEquip = isPremium || userRole == "admin" || avatar.isDefault || isGifted',
    'val canEquip = isPremium || userRole == "admin" || avatar.isDefault || isGifted || avatar.rarity.equals("común", true) || avatar.rarity.equals("comun", true)'
);

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', code);
console.log("Dialog replaced!");
