const fs = require('fs');
let content = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

// 1. Change title
content = content.replace('text = tr("Galería de Avatares LoL"),', 'text = tr("Avatares"),');

// 2. Filter Regions and remove "Todos"
const oldFilterCode = `    var selectedFilter by remember { mutableStateOf("Todos") }
    var showPremiumRequiredDialog by remember { mutableStateOf<AvatarItem?>(null) }
    var isUpdating by remember { mutableStateOf(false) }

            val filterOptions = remember {
        listOf("Todos") + AvatarCatalog.avatars.map { it.region }.distinct().sorted()
    }

    val groupedAvatars = remember(selectedFilter) {
        val filtered = if (selectedFilter == "Todos") {
            AvatarCatalog.avatars
        } else {
            AvatarCatalog.avatars.filter { it.region.equals(selectedFilter, ignoreCase = true) }
        }
        filtered.groupBy { it.region }.toSortedMap()
    }`;

const newFilterCode = `    val validRegions = remember {
        setOf("Aguas Esturbias", "Ciudad de Bandle", "Demacia", "El Vacío", "Freljord", "Islas de la Sombra", "Jonia", "Ixtal", "Noxus", "Piltóver", "Runaterra", "Shurima", "Targon", "Zaun")
    }
    
    val filterOptions = remember {
        AvatarCatalog.avatars.map { it.region }.filter { validRegions.contains(it) }.distinct().sorted()
    }
    var selectedFilter by remember { mutableStateOf(filterOptions.firstOrNull() ?: "") }
    
    var showPremiumRequiredDialog by remember { mutableStateOf<AvatarItem?>(null) }
    var isUpdating by remember { mutableStateOf(false) }

    val groupedAvatars = remember(selectedFilter) {
        AvatarCatalog.avatars.filter { it.region.equals(selectedFilter, ignoreCase = true) }
            .groupBy { it.region }.toSortedMap()
    }`;

content = content.replace(oldFilterCode, newFilterCode);

// 3. Change Card Border to use rarity color always
const oldCardBorderCode = `                            .border(
                                width = if (isEquipped) 2.dp else 1.dp,
                                color = if (isEquipped) rarityColor else if (canEquip) rarityColor.copy(alpha = 0.8f) else HextechCardBorder.copy(alpha = 0.4f),
                                shape = RoundedCornerShape(12.dp)
                            ),`;

const newCardBorderCode = `                            .border(
                                width = if (isEquipped) 2.dp else 1.dp,
                                color = rarityColor.copy(alpha = if (isEquipped) 1f else 0.8f),
                                shape = RoundedCornerShape(12.dp)
                            ),`;

content = content.replace(oldCardBorderCode, newCardBorderCode);

// 4. Change internal Avatar border to use rarity color
const oldAvatarViewCode = `                                UserAvatarView(
                                    avatarId = avatar.id,
                                    size = 54.dp,
                                    customBorderColor = if (isEquipped) HextechGold else parsedBorder
                                )`;

const newAvatarViewCode = `                                UserAvatarView(
                                    avatarId = avatar.id,
                                    size = 54.dp,
                                    customBorderColor = rarityColor
                                )`;

content = content.replace(oldAvatarViewCode, newAvatarViewCode);

fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', content);
console.log("Replacements done!");
