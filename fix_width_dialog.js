const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

const targetStr = `                            .border(
                                width = if (isEquipped) 2.5.dp else 1.5.dp,
                                brush = getRarityBorderBrush(avatar.rarity),
                                shape = RoundedCornerShape(12.dp)
                            ),`;

const replaceStr = `                            .border(
                                width = when {
                                    isEquipped -> 3.dp
                                    avatar.rarity.lowercase().contains("mítico") || avatar.rarity.lowercase().contains("mitico") -> 2.5.dp
                                    avatar.rarity.lowercase().contains("legendario") -> 2.dp
                                    avatar.rarity.lowercase().contains("épico") || avatar.rarity.lowercase().contains("epico") -> 1.8.dp
                                    avatar.rarity.lowercase().contains("raro") -> 1.5.dp
                                    else -> 1.dp
                                },
                                brush = getRarityBorderBrush(avatar.rarity),
                                shape = RoundedCornerShape(12.dp)
                            ),`;
code = code.replace(targetStr, replaceStr);
fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', code);
