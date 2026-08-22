import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

# --- RUNES TAB PATCH ---
# 1. Insert banner in RunesTab
runes_target = r'(private fun RunesTab\(\) \{[\s\S]*?Column\([\s\S]*?Spacer\(modifier = Modifier\.height\(10\.dp\)\)\n)'
runes_banner = """        // WR-Meta Database Status Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF10B981), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "wr-meta.com / runes",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "${filteredRunes.size} ${tr(\"Runas\")}",
                color = HextechCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
"""
content = re.sub(runes_target, r'\1' + runes_banner, content, count=1)

# 2. Remove the old item block in RunesTab
old_runes_item_regex = r'            item \{\s+Row\(\s+modifier = Modifier\s+\.fillMaxWidth\(\)\s+\.padding\(vertical = 4\.dp\),\s+verticalAlignment = Alignment\.CenterVertically,\s+horizontalArrangement = Arrangement\.SpaceBetween\s+\) \{\s+Row\(verticalAlignment = Alignment\.CenterVertically\) \{\s+Box\(\s+modifier = Modifier\s+\.size\(8\.dp\)\s+\.background\(Color\(0xFF10B981\), CircleShape\)\s+\)\s+Spacer\(modifier = Modifier\.width\(6\.dp\)\)\s+Text\(\s+text = "wr-meta\.com / runes",\s+color = HextechGold,\s+fontSize = 11\.sp,\s+fontWeight = FontWeight\.Bold\s+\)\s+\}\s+Text\(\s+"\$?\{?filteredRunes\.size\}? \$\{tr\("Runas"\)\} • \$\{tr\(WildRiftRepository\.CURRENT_PATCH_VERSION\)\}",\s+color = HextechCyan,\s+fontSize = 11\.sp,\s+fontWeight = FontWeight\.SemiBold\s+\)\s+\}\s+\}\n'
content = re.sub(old_runes_item_regex, '', content)

# --- SPELLS TAB PATCH ---
# 1. Insert banner in SpellsTab
spells_target = r'(private fun SpellsTab\(\) \{[\s\S]*?Column\([\s\S]*?Spacer\(modifier = Modifier\.height\(10\.dp\)\)\n)'
spells_banner = """        // WR-Meta Database Status Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))
                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF10B981), CircleShape)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "wr-meta.com / spells",
                    color = HextechGold,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "${filteredSpells.size} ${tr(\"Hechizos\")}",
                color = HextechCyan,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
"""
content = re.sub(spells_target, r'\1' + spells_banner, content, count=1)

# 2. Remove the old item block in SpellsTab
old_spells_item_regex = r'            item \{\s+Row\(\s+modifier = Modifier\s+\.fillMaxWidth\(\)\s+\.padding\(vertical = 4\.dp\),\s+verticalAlignment = Alignment\.CenterVertically,\s+horizontalArrangement = Arrangement\.SpaceBetween\s+\) \{\s+Row\(verticalAlignment = Alignment\.CenterVertically\) \{\s+Box\(\s+modifier = Modifier\s+\.size\(8\.dp\)\s+\.background\(Color\(0xFF10B981\), CircleShape\)\s+\)\s+Spacer\(modifier = Modifier\.width\(6\.dp\)\)\s+Text\(\s+text = "wr-meta\.com / spells",\s+color = HextechGold,\s+fontSize = 11\.sp,\s+fontWeight = FontWeight\.Bold\s+\)\s+\}\s+Text\(\s+"\$?\{?filteredSpells\.size\}? \$\{tr\("Hechizos"\)\} • \$\{tr\("CDs Oficiales WR"\)\}",\s+color = HextechCyan,\s+fontSize = 11\.sp,\s+fontWeight = FontWeight\.SemiBold\s+\)\s+\}\s+\}\n'
content = re.sub(old_spells_item_regex, '', content)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
