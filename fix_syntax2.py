with open('overlay_top.kt', 'r', encoding='utf-8') as f:
    top = f.read()

with open('remaining.kt', 'r', encoding='utf-8') as f:
    rem = f.read()

# find "// ====================================================================" in rem
idx = rem.find("// ====================================================================")
if idx != -1:
    rem = rem[idx:]

with open('app/src/main/java/com/example/ui/components/FloatingAssistantOverlay.kt', 'w', encoding='utf-8') as f:
    f.write(top + "\n" + rem)
