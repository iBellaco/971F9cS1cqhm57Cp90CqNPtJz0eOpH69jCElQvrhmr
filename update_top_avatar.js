const fs = require('fs');

let content = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

const oldTopRarity = `                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(HextechGold.copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = currentAvatar.rarity.uppercase(),
                                    color = HextechGold,`;

const newTopRarity = `                            val currentRarityColor = getRarityColor(currentAvatar.rarity)
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(currentRarityColor.copy(alpha = 0.2f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = currentAvatar.rarity.uppercase(),
                                    color = currentRarityColor,`;

content = content.replace(oldTopRarity, newTopRarity);

const oldTopCard = `            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, HextechGold.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),`;

const newTopCard = `            val currentRarityColorTop = getRarityColor(currentAvatar.rarity)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, currentRarityColorTop.copy(alpha = 0.7f), RoundedCornerShape(12.dp)),`;

content = content.replace(oldTopCard, newTopCard);

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', content);
console.log("Updated top avatar UI");
