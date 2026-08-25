import re

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace(
    'analysis = DraftAnalysisResult(emptyList(), emptyList(), emptyList())',
    'analysis = DraftAnalysisResult(physicalDamagePercent=0, magicDamagePercent=0, trueDamagePercent=0, frontlineStatus="", directMatchupWarning=null, directCounterBestPick=null, recommendations=emptyList())'
)

# Add OverlayObjectivesTabContent
content = content.replace(
    'fun FloatingAssistantOverlay(',
    '@Composable\nprivate fun OverlayObjectivesTabContent() { Column(modifier=Modifier.fillMaxSize()) { Text("Objectives") } }\n\n@Composable\nfun FloatingAssistantOverlay('
)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w', encoding='utf-8') as f:
    f.write(content)

