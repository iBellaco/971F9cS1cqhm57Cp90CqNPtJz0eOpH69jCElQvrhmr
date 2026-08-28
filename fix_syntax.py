import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

# First, strip out all the imports that are floating in the middle
def strip_in_middle(text):
    lines = text.split('\n')
    out = []
    in_code = False
    for line in lines:
        if "@Composable" in line or "class" in line or "fun" in line:
            in_code = True
        
        if in_code and line.startswith("import "):
            continue
        
        if line == "import androidx.compose.material3.ButtonDefaultsDefaults":
            line = "import androidx.compose.material3.ButtonDefaults"
        out.append(line)
    return "\n".join(out)

code = strip_in_middle(code)

imports_to_add = """
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.data.sync.OfflineResourceManager
import com.example.data.sync.DownloadState
import com.example.ui.theme.DangerRed
"""

code = code.replace("import androidx.compose.material3.ButtonDefaults\n", "import androidx.compose.material3.ButtonDefaults\n" + imports_to_add)

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

