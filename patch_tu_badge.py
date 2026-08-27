import re

file_path = "app/src/main/java/com/example/ui/components/DraftTeamPositionCard.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

# Pattern to find and remove the block
pattern = r'\s*// Badge de "TÚ" si es la posición activa del jugador\s*if \(isMyRole\) \{\s*Box\(\s*modifier = Modifier\s*\.align\(Alignment\.BottomCenter\)\s*\.fillMaxWidth\(\)\s*\.background\(HextechCyan\.copy\(alpha = 0\.9f\)\)\s*\.padding\(vertical = 1\.dp\),\s*contentAlignment = Alignment\.Center\s*\) \{\s*Text\(\s*text = tr\("TÚ"\),\s*color = Color\.Black,\s*fontSize = 7\.5\.sp,\s*fontWeight = FontWeight\.Black\s*\)\s*\}\s*\}'

# Check if pattern matches
if re.search(pattern, content):
    content = re.sub(pattern, '', content)
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content)
    print("Badge removed successfully.")
else:
    print("Pattern not found!")
