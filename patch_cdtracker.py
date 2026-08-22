import re

with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'r') as f:
    content = f.read()

# 1. Update State Holder
state_target = """object CooldownTrackerStateHolder {
    // Map of role name -> Map of spell id -> expiry timestamp in millis
    val activeTimers = mutableStateMapOf<String, Long>()

    fun startTimer(roleKey: String, spellId: String, durationSeconds: Int) {"""
state_replacement = """object CooldownTrackerStateHolder {
    // Map of role name -> Map of spell id -> expiry timestamp in millis
    val activeTimers = mutableStateMapOf<String, Long>()
    val enemyChampions = mutableStateMapOf<String, com.example.model.Champion>()
    val ultimateRanks = mutableStateMapOf<String, Int>()

    fun startTimer(roleKey: String, spellId: String, durationSeconds: Int) {"""
content = content.replace(state_target, state_replacement)

# 2. Add imports
import_target = """import com.example.util.tr
import kotlinx.coroutines.delay"""
import_replacement = """import com.example.util.tr
import kotlinx.coroutines.delay
import com.example.data.WildRiftRepository
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.ui.screens.ChampionPickerSheet"""
content = content.replace(import_target, import_replacement)

# 3. Add dynamic spell list and picker state
panel_head_target = """fun CooldownTrackerPanel(
    modifier: Modifier = Modifier,
    isCompactOverlay: Boolean = false
) {
    var selectedRole by remember { mutableStateOf(LaneRole.MID) }
    var currentTimeMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }"""
panel_head_replacement = """@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CooldownTrackerPanel(
    modifier: Modifier = Modifier,
    isCompactOverlay: Boolean = false
) {
    var selectedRole by remember { mutableStateOf(LaneRole.MID) }
    var currentTimeMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var showChampionPicker by remember { mutableStateOf(false) }"""
content = content.replace(panel_head_target, panel_head_replacement)

# 4. Modify where we iterate spells to replace the ultimate
spells_target = """        // Grid of Spells
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(DEFAULT_TRACKED_SPELLS) { spell ->"""
spells_replacement = """        // Champion Picker for Ultimate
        val enemyChamp = CooldownTrackerStateHolder.enemyChampions[selectedRole.name]
        val ultRank = CooldownTrackerStateHolder.ultimateRanks[selectedRole.name] ?: 1
        
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (enemyChamp != null) {
                ChampionAvatar(champion = enemyChamp, size = 32.dp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(enemyChamp.name, color = HextechCyan, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Spacer(modifier = Modifier.weight(1f))
                // Rank selector
                Text(tr("Nvl. Ult:"), color = TextMuted, fontSize = 10.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Row(modifier = Modifier.background(HextechSurface, RoundedCornerShape(4.dp))) {
                    (1..3).forEach { rank ->
                        Box(
                            modifier = Modifier
                                .clickable { CooldownTrackerStateHolder.ultimateRanks[selectedRole.name] = rank }
                                .background(if (ultRank == rank) HextechGold else Color.Transparent, RoundedCornerShape(4.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(rank.toString(), color = if (ultRank == rank) HextechDarkBg else TextMuted, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { showChampionPicker = true }, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = HextechGold, modifier = Modifier.size(14.dp))
                }
            } else {
                OutlinedButton(
                    onClick = { showChampionPicker = true },
                    modifier = Modifier.fillMaxWidth().height(36.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = HextechCyan),
                    border = BorderStroke(1.dp, HextechCyan.copy(alpha=0.5f))
                ) {
                    Text(tr("Seleccionar Campeón Enemigo"), fontSize = 12.sp)
                }
            }
        }
        
        val displaySpells = remember(enemyChamp, ultRank) {
            val list = DEFAULT_TRACKED_SPELLS.toMutableList()
            if (enemyChamp != null) {
                val ultSkill = enemyChamp.skills.find { it.slotName.contains("Definitiva") || it.slot == "4" }
                val parts = ultSkill?.cooldown?.replace(Regex("[^0-9/]"), "")?.split("/")?.filter { it.isNotBlank() } ?: listOf("60")
                val baseParts = if (parts.isEmpty()) listOf("60") else parts
                val index = (ultRank - 1).coerceIn(0, baseParts.size - 1)
                val ultBaseCd = baseParts[index].toIntOrNull() ?: 60
                
                val ultIndex = list.indexOfFirst { it.id == "ult" }
                if (ultIndex != -1) {
                    list[ultIndex] = TrackedCooldown(
                        id = "ult",
                        name = tr("Definitiva") + " " + enemyChamp.name,
                        baseCooldownSeconds = ultBaseCd,
                        iconFallback = "R",
                        iconUrl = ultSkill?.iconUrl ?: "",
                        accentColor = TierSPlusColor
                    )
                }
            }
            list
        }

        if (showChampionPicker) {
            // Need a simple picker sheet here
            // But ChampionPickerSheet might be defined elsewhere, let's just use a dialog or something simple.
        }

        // Grid of Spells
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(displaySpells) { spell ->"""
content = content.replace(spells_target, spells_replacement)

# Oh wait, ChampionPickerSheet from where? I imported com.example.ui.screens.ChampionPickerSheet but is it there?
# Let's check where DraftChampionPickerSheet is. It's private in MetaAndDraftScreen!
# I will copy the DraftChampionPickerSheet to a global one or implement a simple picker list in CooldownTrackerPanel.
with open('app/src/main/java/com/example/ui/components/CooldownTrackerPanel.kt', 'w') as f:
    f.write(content)
