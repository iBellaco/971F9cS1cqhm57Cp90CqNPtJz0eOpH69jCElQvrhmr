import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

content = content.replace(
    'import com.example.model.WomboComboSynergyDetector',
    'import com.example.ui.components.WomboComboSynergyDetector\nimport androidx.compose.material3.Button\nimport androidx.compose.material3.ButtonDefaults\nimport androidx.compose.ui.platform.LocalContext'
)

content = content.replace(
    '''                                    // TODO: Emit intent to open Tier List in main app or show simple modal
                                    val intent = android.content.Intent(this@FloatingAssistantService, com.example.MainActivity::class.java).apply {
                                        flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                                        putExtra("OPEN_TIER_LIST", true)
                                    }
                                    startActivity(intent)''',
    '''                                    val intent = android.content.Intent(context, com.example.MainActivity::class.java).apply {
                                        flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK
                                        putExtra("OPEN_TIER_LIST", true)
                                    }
                                    context.startActivity(intent)'''
)

# And inject `val context = LocalContext.current` inside the LazyColumn or where it's used
content = content.replace(
    '                            val allyWombos = remember(allies.toList()) { WomboComboSynergyDetector.detectWombos(allies.toList()) }',
    '                            val context = LocalContext.current\n                            val allyWombos = remember(allies.toList()) { WomboComboSynergyDetector.detectWombos(allies.toList()) }'
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
