import re

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "r") as f:
    code = f.read()

# Remove duplicate imports and fix imports
import_block = """import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.data.sync.OfflineResourceManager
import com.example.data.sync.DownloadState
import com.example.ui.theme.DangerRed
"""

# Find the exact line where imports end (just before @Composable)
composable_idx = code.find("@Composable")

imports_area = code[:composable_idx]
code_area = code[composable_idx:]

# Let's clean up imports_area
lines = imports_area.split("\n")
clean_lines = []
for line in lines:
    if "OfflineResourceManager" in line or "DownloadState" in line or "HextechRed" in line or "LinearProgressIndicator" in line or "CloudDownload" in line or "import androidx.compose.runtime.getValue" in line or "import androidx.compose.runtime.collectAsState" in line or "import androidx.compose.material3.CardDefaults" in line:
        continue
    if "import androidx.compose.material3.Card" in line:
        continue
    clean_lines.append(line)

new_imports_area = "\n".join(clean_lines) + "\n" + import_block

# Replace HextechRed with DangerRed
code_area = code_area.replace("HextechRed", "DangerRed")

code = new_imports_area + code_area

with open("app/src/main/java/com/example/ui/screens/MainDraftingScreen.kt", "w") as f:
    f.write(code)

