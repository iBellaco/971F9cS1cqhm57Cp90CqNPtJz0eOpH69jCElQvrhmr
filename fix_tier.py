import sys

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "r") as f:
    content = f.read()

target = """            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = tr("Ordenar:"),"""

replacement = """            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = tr("Ordenar:"),
                    modifier = Modifier.align(Alignment.CenterVertically),"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "w") as f:
    f.write(content)
