import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    lines = f.readlines()

# Replace lines 856-861 with Box + if + else Column
for i, line in enumerate(lines):
    if "// Contenido Scrollable del Drafting" in line:
        start_idx = i
        break

lines[start_idx:start_idx+7] = [
    "                        // Contenido Scrollable del Drafting\n",
    "                        Box(modifier = Modifier.weight(1f, fill = false).fillMaxWidth()) {\n",
    "                            if (overlayMode == OverlayMode.TIER_LIST) {\n",
    "                                Column(modifier = Modifier.fillMaxSize()) {\n",
    "                                    Row(\n",
    "                                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),\n",
    "                                        horizontalArrangement = Arrangement.Start\n",
    "                                    ) {\n",
    "                                        Button(\n",
    "                                            onClick = { overlayMode = OverlayMode.DRAFT },\n",
    "                                            modifier = Modifier.height(30.dp),\n",
    "                                            colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),\n",
    "                                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 0.dp)\n",
    "                                        ) {\n",
    "                                            Icon(androidx.compose.material.icons.Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(14.dp), tint = HextechGold)\n",
    "                                            Spacer(modifier = Modifier.width(4.dp))\n",
    "                                            Text(\"Volver a Draft\", color = HextechGold, fontSize = 10.sp)\n",
    "                                        }\n",
    "                                    }\n",
    "                                    com.example.ui.screens.TierListTab(\n",
    "                                        onSelectChampion = { },\n",
    "                                        isPremium = isPremium\n",
    "                                    )\n",
    "                                }\n",
    "                            } else {\n",
    "                                Column(\n",
    "                                    modifier = Modifier\n",
    "                                        .fillMaxSize()\n",
    "                                        .verticalScroll(rememberScrollState())\n",
    "                                ) {\n",
    "                                    // 1. TABLERO DE DRAFT (5 ALIADOS VS 5 ENEMIGOS)\n"
]

# Find the end of the scrollable column to close the Box and else block
for i in range(start_idx, len(lines)):
    if "Spacer(modifier = Modifier.height(6.dp))" in lines[i] and "// Footer / Desactivar alerta" in lines[i+2]:
        end_idx = i - 1
        break

lines.insert(end_idx, "                            }\n                        }\n")

# Now handle the rest: OverlayMode enum, SaveDraftDialog, etc.
content = "".join(lines)

enum_mode = "    enum class OverlayMode { DRAFT, TIER_LIST }\n"
if "enum class OverlayMode" not in content:
    content = content.replace("class FloatingAssistantService :", enum_mode + "\nclass FloatingAssistantService :")

old_vars = """    val coroutineScope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(false) }
    var activeRole by remember { mutableStateOf(LaneRole.MID) }"""
new_vars = """    val coroutineScope = rememberCoroutineScope()
    var isExpanded by remember { mutableStateOf(false) }
    var overlayMode by remember { mutableStateOf(OverlayMode.DRAFT) }
    var showSaveDraftDialog by remember { mutableStateOf(false) }
    var isSavedRecently by remember { mutableStateOf(false) }
    val isPremium by com.example.util.SubscriptionManager.isPremium.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var activeRole by remember { mutableStateOf(LaneRole.MID) }"""
content = content.replace(old_vars, new_vars)

imports = "import androidx.lifecycle.compose.collectAsStateWithLifecycle\nimport androidx.compose.ui.platform.LocalContext\nimport com.example.data.repository.DraftHistoryRepository"
if "collectAsStateWithLifecycle" not in content:
    content = content.replace("import kotlinx.coroutines.Dispatchers", imports + "\nimport kotlinx.coroutines.Dispatchers")

old_tier_btn = """                            Button(
                                onClick = {
                                    val intent = android.content.Intent(context, com.example.MainActivity::class.java).apply {
                                        flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                                        putExtra("OPEN_TIER_LIST", true)
                                    }
                                    context.startActivity(intent)
                                },
                                modifier = Modifier.fillMaxWidth().height(28.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HextechGold),
                                contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                            ) {
                                Text("Ver Tier List Completa", color = HextechDarkBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                            }"""
new_action_btns = """                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { 
                                        if (isPremium) showSaveDraftDialog = true 
                                        else android.widget.Toast.makeText(context, "Requiere Premium", android.widget.Toast.LENGTH_SHORT).show()
                                    },
                                    modifier = Modifier.weight(1f).height(28.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = if (isSavedRecently) androidx.compose.ui.graphics.Color(0xFF81C784) else HextechGold.copy(alpha=0.15f)),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, if(isSavedRecently) androidx.compose.ui.graphics.Color(0xFF81C784) else HextechGold),
                                    contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                                ) {
                                    Text(if (isSavedRecently) "¡Guardado!" else "Guardar Draft", color = if(isSavedRecently) androidx.compose.ui.graphics.Color.White else HextechGold, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                                Button(
                                    onClick = { overlayMode = OverlayMode.TIER_LIST },
                                    modifier = Modifier.weight(1f).height(28.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),
                                    contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                                ) {
                                    Text("Tier List Completa", color = HextechDarkBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }"""
content = content.replace(old_tier_btn, new_action_btns)

# Now, we need to inject the SaveDraftDialog just inside the FloatingOverlayContent, for example at the very beginning of the Surface or Column.
# Let's put it right after the Box of the overlay
old_box = "    Box(modifier = Modifier.fillMaxSize()) {"
new_box = """    Box(modifier = Modifier.fillMaxSize()) {
        if (showSaveDraftDialog) {
            com.example.ui.components.SaveDraftDialog(
                myChampion = null,
                enemyLaneOpponent = null,
                userRole = activeRole,
                estimatedWinrate = analysis.bestOverallPick?.estimatedWinrate ?: 50.0,
                onDismiss = { showSaveDraftDialog = false },
                onSave = { result, notes ->
                    coroutineScope.launch {
                        DraftHistoryRepository.saveDraft(
                            context = context,
                            myRole = activeRole,
                            isFirstPick = isFirstPick,
                            allies = allies,
                            enemies = enemies,
                            analysis = analysis,
                            notes = notes,
                            matchResult = result
                        )
                        isSavedRecently = true
                        showSaveDraftDialog = false
                        android.widget.Toast.makeText(context, "Draft Guardado", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }"""
content = content.replace(old_box, new_box)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)

