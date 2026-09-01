import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# Make composables public
content = content.replace("private fun TierListTab(", "fun TierListTab(")
content = content.replace("private fun TierSectionCard(", "fun TierSectionCard(")
content = content.replace("private fun TierChampionItem(", "fun TierChampionItem(")
content = content.replace("private fun TierSelectionPanel(", "fun TierSelectionPanel(")

# Modify TierListTab signature to include isPremium
content = content.replace(
    "fun TierListTab(\n    onSelectChampion: (Champion) -> Unit\n)",
    "fun TierListTab(\n    onSelectChampion: (Champion) -> Unit,\n    isPremium: Boolean = false\n)"
)

# Add showFavoritesOnly state and read favorites flow
favorites_imports = "    val favorites by FavoriteChampionsManager.favoritesFlow.collectAsStateWithLifecycle()\n    var showFavoritesOnly by remember { mutableStateOf(false) }"
content = content.replace(
    "    var selectedLane by remember { mutableStateOf<LaneRole?>(null) }",
    favorites_imports + "\n    var selectedLane by remember { mutableStateOf<LaneRole?>(null) }"
)

# Modify rawChampionsToDisplay logic
old_raw_logic = """    val rawChampionsToDisplay = remember(selectedLane, syncState, WildRiftRepository.champions.toList()) {
        if (selectedLane == null) WildRiftRepository.champions
        else WildRiftRepository.getChampionsByRole(selectedLane!!)
    }"""

new_raw_logic = """    val rawChampionsToDisplay = remember(selectedLane, showFavoritesOnly, favorites, syncState, WildRiftRepository.champions.toList()) {
        val champs = if (selectedLane == null) WildRiftRepository.champions
        else WildRiftRepository.getChampionsByRole(selectedLane!!)
        if (showFavoritesOnly) champs.filter { it.id in favorites } else champs
    }"""
content = content.replace(old_raw_logic, new_raw_logic)

# Add B, C, D tiers
old_tiers = """    val tierSPlus = championsToDisplay.filter { it.tier == "S+" }
    val tierS = championsToDisplay.filter { it.tier == "S" }
    val tierA = championsToDisplay.filter { it.tier == "A+" || it.tier == "A" }"""

new_tiers = """    val tierSPlus = championsToDisplay.filter { it.tier == "S+" }
    val tierS = championsToDisplay.filter { it.tier == "S" }
    val tierA = championsToDisplay.filter { it.tier == "A+" || it.tier == "A" }
    val tierB = championsToDisplay.filter { it.tier == "B" || it.tier == "B+" }
    val tierC = championsToDisplay.filter { it.tier == "C" || it.tier == "C+" }
    val tierD = championsToDisplay.filter { it.tier != "S+" && it.tier != "S" && it.tier != "A+" && it.tier != "A" && it.tier != "B" && it.tier != "B+" && it.tier != "C" && it.tier != "C+" }"""
content = content.replace(old_tiers, new_tiers)

# Add "Favoritos" chip
old_chip = """        // Role Filter
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {"""

new_chip = """        // Role Filter
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            FilterChip(
                selected = showFavoritesOnly,
                onClick = { 
                    if (isPremium) {
                        showFavoritesOnly = !showFavoritesOnly 
                    } else {
                        Toast.makeText(context, "Requiere Premium", Toast.LENGTH_SHORT).show()
                    }
                },
                label = { Text(tr("Favoritos"), fontSize = 11.5.sp) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = HextechCyan,
                    selectedLabelColor = HextechDarkBg
                ),
                leadingIcon = {
                    if (showFavoritesOnly) {
                        Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp))
                    } else {
                        Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(16.dp), tint = if (isPremium) TextPrimary else TextMuted)
                    }
                }
            )"""
content = content.replace(old_chip, new_chip)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
