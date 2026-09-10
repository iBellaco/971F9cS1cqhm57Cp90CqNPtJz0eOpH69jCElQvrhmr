with open("app/src/main/java/com/example/ui/components/ScannerDebugOverlay.kt", "r") as f:
    content = f.read()

content = content.replace("import androidx.compose.ui.platform.LocalDensity", "import androidx.compose.ui.platform.LocalDensity\nimport androidx.compose.ui.unit.sp")

with open("app/src/main/java/com/example/ui/components/ScannerDebugOverlay.kt", "w") as f:
    f.write(content)
