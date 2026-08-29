import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

replacement = """
                        is ChineseSyncState.Success -> if (currentRegion == "BestBuildWR") "🟢 ${tr("Sincronizado")} ${s.timestamp}" else "🟢 ${tr("En vivo:")} ${s.timestamp} (${tr(s.tier.displayName)})"
"""

text = re.sub(r'is ChineseSyncState\.Success -> "🟢 \$\{tr\("En vivo:"\)\} \$\{s\.timestamp\} \(\$\{tr\(s\.tier\.displayName\)\}\)"', replacement.strip(), text)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
