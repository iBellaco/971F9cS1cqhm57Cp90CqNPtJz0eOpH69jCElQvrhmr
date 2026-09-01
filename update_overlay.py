import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Add OverlayMode enum
enum_mode = "    enum class OverlayMode { DRAFT, TIER_LIST }\n"
if "enum class OverlayMode" not in content:
    content = content.replace("class FloatingAssistantService :", enum_mode + "\nclass FloatingAssistantService :")

# Inside FloatingOverlayContent
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

# We need to collect collectAsStateWithLifecycle if not imported.
imports = "import androidx.lifecycle.compose.collectAsStateWithLifecycle\nimport androidx.compose.ui.platform.LocalContext\nimport com.example.data.repository.DraftHistoryRepository"
if "collectAsStateWithLifecycle" not in content:
    content = content.replace("import kotlinx.coroutines.Dispatchers", imports + "\nimport kotlinx.coroutines.Dispatchers")

# Fix button Intent "Ver Tier List Completa"
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
                                    colors = ButtonDefaults.buttonColors(containerColor = if (isSavedRecently) androidx.compose.ui.graphics.Color(0xFF81C784) else HextechGold),
                                    contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                                ) {
                                    Text(if (isSavedRecently) "¡Guardado!" else "Guardar Draft", color = HextechDarkBg, fontSize = 10.sp, fontWeight = FontWeight.Bold)
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

# Now, handle the OverlayMode switching
old_content_box = """                                Box(modifier = Modifier.weight(1f)) {
                                    // Pestañas (Simuladas con botones pequeños en el header o similar, aquí todo junto)
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .verticalScroll(rememberScrollState())
                                    ) {"""

new_content_box = """                                Box(modifier = Modifier.weight(1f)) {
                                    if (overlayMode == OverlayMode.TIER_LIST) {
                                        Column(modifier = Modifier.fillMaxSize()) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                Button(
                                                    onClick = { overlayMode = OverlayMode.DRAFT },
                                                    modifier = Modifier.height(30.dp),
                                                    colors = ButtonDefaults.buttonColors(containerColor = HextechSurfaceVariant),
                                                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                                                ) {
                                                    Icon(androidx.compose.material.icons.Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(14.dp), tint = HextechGold)
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text("Volver a Draft", color = HextechGold, fontSize = 10.sp)
                                                }
                                            }
                                            com.example.ui.screens.TierListTab(
                                                onSelectChampion = { champ -> 
                                                    // No-op for now in Tier List overlay 
                                                },
                                                isPremium = isPremium
                                            )
                                        }
                                    } else {
                                    // Pestañas (Simuladas con botones pequeños en el header o similar, aquí todo junto)
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .verticalScroll(rememberScrollState())
                                    ) {"""

content = content.replace(old_content_box, new_content_box)
# Close the else block properly...
# Wait, the closing brace for else is tricky to find.
# Instead of replacing the whole block, let's just find the closing bracket of the Column.

