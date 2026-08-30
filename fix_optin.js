const fs = require('fs');
let content = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

content = content.replace('@OptIn(ExperimentalMaterial3Api::class)\nfun getRarityColor', 'fun getRarityColor');
content = content.replace('@Composable\nfun AvatarSelectionBottomSheet', '@OptIn(ExperimentalMaterial3Api::class)\n@Composable\nfun AvatarSelectionBottomSheet');

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', content);
console.log("Fixed OptIn");
