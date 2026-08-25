with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'r', encoding='utf-8') as f:
    lines = f.readlines()

new_lines = []
skip = False
for i, line in enumerate(lines):
    if line.strip() == "Text(tr(item.stats), color = HextechCyan, fontSize = 10.sp)" and "Spacer" not in lines[i-1]:
        # This is the duplicate block starting
        if not new_lines[-1].strip() == "}":
            continue
        # We want to skip from this line down to the "// =========================" comment
        skip = True
    
    if skip:
        if line.strip().startswith("// ===================================================================="):
            skip = False
        else:
            continue
    
    new_lines.append(line)

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w', encoding='utf-8') as f:
    f.writelines(new_lines)
