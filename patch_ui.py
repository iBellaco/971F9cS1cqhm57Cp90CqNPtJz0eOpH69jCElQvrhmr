import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# Show error in Catalog
old_catalog = """                val catalogTabs = listOf(
                    CatalogTabItem(tr("Campeones"), totalChamps),"""

new_catalog = """                if (WildRiftRepository.lastError != null) {
                    Text("ERROR: ${WildRiftRepository.lastError}", color = androidx.compose.ui.graphics.Color.Red)
                }
                val catalogTabs = listOf(
                    CatalogTabItem(tr("Campeones"), totalChamps),"""

content = content.replace(old_catalog, new_catalog)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
