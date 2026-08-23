import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Add context and coroutineScope
text = text.replace(
    '    val syncState by ChineseMetaSyncService.syncState.collectAsStateWithLifecycle()',
    '    val context = androidx.compose.ui.platform.LocalContext.current\n    val coroutineScope = rememberCoroutineScope()\n    val syncState by ChineseMetaSyncService.syncState.collectAsStateWithLifecycle()'
)

# In ChampionsCatalogTab, replace the "Total count" Row with TierSelectionPanel
# First find the block
regex = r'// Total count\s*Row\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.background\(Color\(0xFF0F1522\), RoundedCornerShape\(8\.dp\)\)\s*\.border\(0\.5\.dp, HextechGold\.copy\(alpha = 0\.4f\), RoundedCornerShape\(8\.dp\)\)\s*\.padding\(horizontal = 10\.dp, vertical = 5\.dp\),\s*verticalAlignment = Alignment\.CenterVertically,\s*horizontalArrangement = Arrangement\.SpaceBetween\s*\)\s*\{\s*Text\(\s*text = "\$\{filteredChampions\.size\} " \+ tr\("Campeones"\),\s*color = HextechCyan,\s*fontSize = 11\.sp,\s*fontWeight = FontWeight\.SemiBold\s*\)\s*Text\(\s*text = "\$\{tr\("Rango Activo:"\)\} \$\{tr\(currentTier\.displayName\)\}",\s*color = HextechGoldLight,\s*fontSize = 10\.sp\s*\)\s*\}'

replacement = """// Total count
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${filteredChampions.size} " + tr("Campeones"),
                color = HextechCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        TierSelectionPanel(currentTier, syncState, context, coroutineScope)"""

text = re.sub(regex, replacement, text, flags=re.DOTALL)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
