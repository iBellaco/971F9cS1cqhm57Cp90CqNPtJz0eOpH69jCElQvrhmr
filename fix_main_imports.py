import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

if "import androidx.compose.runtime.getValue" not in code:
    code = code.replace("import androidx.compose.runtime.*", "import androidx.compose.runtime.*\nimport androidx.compose.runtime.getValue")
    
if "import com.example.data.sync.DownloadState" not in code:
    code = code.replace("import androidx.compose.runtime.*", "import androidx.compose.runtime.*\nimport com.example.data.sync.DownloadState\nimport com.example.data.sync.OfflineResourceManager\nimport androidx.compose.material.icons.filled.CloudDownload\nimport androidx.compose.material3.LinearProgressIndicator\nimport com.example.ui.theme.HextechRed")
    
with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

