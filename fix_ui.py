import re

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "r") as f:
    content = f.read()

content = content.replace(
    'text = "Principal: ${champion.primaryRole.shortName} • ${champion.damageType.displayName}"',
    'text = "Principal: ${com.example.util.tr(champion.primaryRole.shortName)} • ${com.example.util.tr(champion.damageType.displayName)}"'
)
content = content.replace(
    'text = "${champion.primaryRole.displayName} • ${champion.damageType.displayName}"',
    'text = "${com.example.util.tr(champion.primaryRole.displayName)} • ${com.example.util.tr(champion.damageType.displayName)}"'
)
content = content.replace(
    'Text("${champ.primaryRole.shortName} • ${champ.damageType.displayName}"',
    'Text("${com.example.util.tr(champ.primaryRole.shortName)} • ${com.example.util.tr(champ.damageType.displayName)}"'
)
content = content.replace(
    'label = { Text(cat.displayName, fontSize = 11.5.sp) }',
    'label = { Text(com.example.util.tr(cat.displayName), fontSize = 11.5.sp) }'
)
content = content.replace(
    'Text(activeRole.displayName, color = HextechGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)',
    'Text(com.example.util.tr(activeRole.displayName), color = HextechGold, fontSize = 12.5.sp, fontWeight = FontWeight.Bold)'
)
content = content.replace(
    'text = if (isFirstPick) tr("★ Mejor Primer Pick Seguro para") + " ${activeRole.displayName}" else tr("★ Mejor Opción según tu Equipo y el Rival")',
    'text = if (isFirstPick) tr("★ Mejor Primer Pick Seguro para") + " ${com.example.util.tr(activeRole.displayName)}" else tr("★ Mejor Opción según tu Equipo y el Rival")'
)
content = content.replace(
    'text = tr("Otras Opciones Viables para") + " ${activeRole.displayName}:"',
    'text = tr("Otras Opciones Viables para") + " ${com.example.util.tr(activeRole.displayName)}:"'
)
content = content.replace(
    'Text(role.displayName, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)',
    'Text(com.example.util.tr(role.displayName), color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Bold)'
)

# Replace recommended runes string
content = content.replace(
    'Text("🔮 ${tr("Runas recomendadas:")} ${rec.champion.recommendedRunes} • ${tr("Toca para ver build")}",',
    'Text("🔮 ${tr("Runas recomendadas:")} ${com.example.util.tr(rec.champion.recommendedRunes)} • ${tr("Toca para ver build")}",'
)

with open("app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt", "w") as f:
    f.write(content)
