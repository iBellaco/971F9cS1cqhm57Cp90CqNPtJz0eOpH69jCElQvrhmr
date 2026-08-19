import re

file_path = "app/src/main/java/com/example/service/FloatingAssistantService.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Add import for tr if needed
if "import com.example.util.tr" not in content:
    content = content.replace("import com.example.util.LocalLanguage", "import com.example.util.LocalLanguage\nimport com.example.util.tr")

# Fix tabs
content = content.replace(
    """val tabs = listOf("Draft", "Objetivos", "Objetos", "Runas")""",
    """val tabs = listOf(tr("Draft"), tr("Objetivos"), tr("Objetos"), tr("Runas"))"""
)

# Fix "Detener Asistente"
content = content.replace(
    """text = "Detener Asistente",""",
    """text = tr("Detener Asistente"),"""
)

# Fix "Minimizar HUD"
content = content.replace(
    """text = "Minimizar HUD",""",
    """text = tr("Minimizar HUD"),"""
)

# Fix "★ 1er Pick (Seguro)"
content = content.replace(
    """text = if (isFirstPick) "★ 1er Pick (Seguro)" else "★ MEJOR OPCIÓN (${activeRole.shortName})",""",
    """text = if (isFirstPick) "★ " + tr("1er Pick (Seguro)") else "★ " + tr("MEJOR OPCIÓN") + " (${activeRole.shortName})","""
)

# Fix "Blind Pick" / "Counter Pick"
content = content.replace(
    """text = if (isFirstPick) "Blind Pick" else "Counter Pick",""",
    """text = if (isFirstPick) tr("Blind Pick") else tr("Counter Pick"),"""
)

# Fix "Objetos clave para"
content = content.replace(
    """Text("Objetos clave para ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)""",
    """Text(tr("Objetos clave para") + " ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)"""
)

# Fix "Runas de"
content = content.replace(
    """Text("Runas de ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)""",
    """Text(tr("Runas de") + " ${currentChamp.name}:", color = HextechGold, fontSize = 11.5.sp, fontWeight = FontWeight.Bold)"""
)

# Fix "Ver otro"
content = content.replace(
    """Text("Ver otro", color = HextechCyan, fontSize = 10.sp, modifier = Modifier.clickable { selectedTab = 0 })""",
    """Text(tr("Ver otro"), color = HextechCyan, fontSize = 10.sp, modifier = Modifier.clickable { selectedTab = 0 })"""
)

# Fix "Build:"
content = content.replace(
    """text = "Build: ${currentChamp.coreItems.joinToString(" • ")}",""",
    """text = tr("Build") + ": ${currentChamp.coreItems.joinToString(" • ")}","""
)

with open(file_path, "w", encoding="utf-8") as f:
    f.write(content)

print("FloatingAssistantService patched!")
