import re

file_path = 'app/src/main/java/com/example/ui/components/WildRiftVersionBanner.kt'

with open(file_path, 'r', encoding='utf-8') as f:
    content = f.read()

# Remove the banner completely
pattern = re.compile(r'\s*// Alerta / Recomendación de Descarga Offline en un costado\s*Row\(\s*modifier = Modifier\s*\.fillMaxWidth\(\)\s*\.clip\(RoundedCornerShape\(8\.dp\)\)\s*\.background\(HextechGold\.copy\(alpha = 0\.12f\)\)\s*\.border\(1\.dp, HextechGold\.copy\(alpha = 0\.5f\), RoundedCornerShape\(8\.dp\)\)\s*\.padding\(8\.dp\),\s*verticalAlignment = Alignment\.CenterVertically\s*\) \{\s*Text\("⭐", fontSize = 12\.sp\)\s*Spacer\(modifier = Modifier\.width\(6\.dp\)\)\s*Column\(modifier = Modifier\.weight\(1f\)\) \{\s*Text\(\s*text = tr\("RECOMENDADO: Descarga de Recursos Offline"\),\s*color = HextechGold,\s*fontSize = 11\.sp,\s*fontWeight = FontWeight\.Bold\s*\)\s*Text\(\s*text = tr\("Garantiza velocidad instantánea sin latencia y funcionamiento sin conexión descargando los datos localmente\."\),\s*color = com\.example\.ui\.theme\.TextSecondary,\s*fontSize = 10\.sp,\s*lineHeight = 13\.sp\s*\)\s*\}\s*\}', re.DOTALL)

content = pattern.sub('', content)

with open(file_path, 'w', encoding='utf-8') as f:
    f.write(content)
