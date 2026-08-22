import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# Fix ItemsCatalogTab
pattern_items = re.compile(r"            Row\(verticalAlignment = Alignment\.CenterVertically\) \{\n                Box\([\s\S]*?                \)\n            \}\n            Row\(verticalAlignment = Alignment\.CenterVertically\) \{\n                Text\(\n                    text = \"\$\{filteredItems\.size\} \$\{tr\(\"Objetos\"\)\}\",")

replacement_items = """            Row(verticalAlignment = Alignment.CenterVertically) {
                // Removed wr-meta text as requested
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${filteredItems.size} ${tr("Ítems")}","""

content = re.sub(pattern_items, replacement_items, content)

# I'll just write a script that does it with string replacement to avoid regex issues.
