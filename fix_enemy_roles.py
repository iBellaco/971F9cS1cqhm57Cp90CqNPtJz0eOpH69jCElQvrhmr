import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# Replace the guessed role text logic
# "Text(tr("*Línea estimada (oculta en juego)"), color = DangerRed.copy(alpha = 0.7f), fontSize = 7.sp, fontWeight = FontWeight.Medium)"
content = content.replace(
    'Text(tr("*Línea estimada (oculta en juego)"), color = DangerRed.copy(alpha = 0.7f), fontSize = 7.sp, fontWeight = FontWeight.Medium)',
    'Text(tr("(Oculto en Draft)"), color = TextMuted, fontSize = 7.sp, fontWeight = FontWeight.Medium)'
)

# And inside DraftSlotItem, we must ensure it doesn't show the role for enemies!
# We can do this by modifying DraftSlotItem:
content = content.replace(
    '''            val roleText = explicitRoleName ?: champion.primaryRole.shortName''',
    '''            val roleText = explicitRoleName ?: if (isAlly) champion.primaryRole.shortName else "?"'''
)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
