import re

def process_file(filepath, replacements):
    with open(filepath, 'r') as f:
        content = f.read()
    for old, new in replacements:
        content = content.replace(old, new)
    with open(filepath, 'w') as f:
        f.write(content)

# ChampionDetailSheet.kt
replacements_champ = [
    ('text = skill.description', 'text = tr(skill.description)'),
    ('text = champion.title', 'text = tr(champion.title)'),
    ('text = champion.tacticalAdvice', 'text = tr(champion.tacticalAdvice)'),
    ('text = champion.summary', 'text = tr(champion.summary)'),
    ('text = swap.reasonTitle', 'text = tr(swap.reasonTitle)'),
    ('text = swap.reasonDesc', 'text = tr(swap.reasonDesc)'),
    ('text = swap.againstWho', 'text = tr(swap.againstWho)')
]
process_file('app/src/main/java/com/example/ui/screens/ChampionDetailSheet.kt', replacements_champ)

# FloatingAssistantOverlay.kt
replacements_overlay = [
    ('Text(item.stats, color = HextechCyan', 'Text(tr(item.stats), color = HextechCyan'),
    ('Text(item.passive, color = TextPrimary', 'Text(tr(item.passive), color = TextPrimary'),
    ('text = champ.title', 'text = tr(champ.title)'),
    ('text = champ.tacticalAdvice', 'text = tr(champ.tacticalAdvice)'),
    ('text = champ.summary', 'text = tr(champ.summary)')
]
process_file('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', replacements_overlay)

