with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

imports = """
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.data.sync.OfflineResourceManager
import com.example.data.sync.DownloadState
import com.example.ui.theme.HextechRed
"""
code = code.replace("import androidx.compose.material3.Card", "import androidx.compose.material3.Card\n" + imports)

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)
