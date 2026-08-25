with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r', encoding='utf-8') as f:
    content = f.read()

content = content.replace(
    'val shouldChange = myEval.estimatedWinrate < 49.0 || myEval.advantageBadge.contains("PELIGRO")',
    'val shouldChange = myEval.estimatedWinrate < 49.0 || myEval.advantageBadge.contains("PELIGRO") || myEval.advantageBadge.contains("ATÍPICA")'
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w', encoding='utf-8') as f:
    f.write(content)
