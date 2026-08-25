import re

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'r', encoding='utf-8') as f:
    content = f.read()

imports = """
import kotlinx.coroutines.launch
import com.example.util.ImagePrefetcher
import com.example.ui.components.DownloadProgressDialog
import androidx.compose.material.icons.filled.Download
"""
if "import com.example.util.ImagePrefetcher" not in content:
    content = content.replace("import com.example.util.tr", imports + "\nimport com.example.util.tr")

actions_block = r'''actions = \{'''
actions_new = r'''actions = {
                        val coroutineScope = rememberCoroutineScope()
                        val context = LocalContext.current
                        
                        IconButton(
                            onClick = { coroutineScope.launch { ImagePrefetcher.prefetchAllImages(context) } },
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clip(CircleShape)
                                .background(HextechSurface)
                                .border(1.dp, HextechCyan.copy(alpha = 0.6f), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = tr("Descargar Recursos"),
                                tint = HextechCyan,
                                modifier = Modifier.size(20.dp)
                            )
                        }
'''
content = re.sub(actions_block, actions_new, content)

# Insert DownloadProgressDialog
content = re.sub(
    r'(\n    \}\n\})$',
    r'\n        DownloadProgressDialog()\1',
    content
)

with open('app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt', 'w', encoding='utf-8') as f:
    f.write(content)

