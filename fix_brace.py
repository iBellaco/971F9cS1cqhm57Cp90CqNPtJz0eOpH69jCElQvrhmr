with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    text = f.read()

opens = text.count('{')
closes = text.count('}')
print(f"opens: {opens}, closes: {closes}")
if closes > opens:
    # remove the last (closes - opens) braces
    excess = closes - opens
    print(f"Removing {excess} extra braces from the end")
    
    lines = text.split('\n')
    while excess > 0:
        for i in range(len(lines)-1, -1, -1):
            if '}' in lines[i]:
                lines[i] = lines[i].replace('}', '', 1)
                excess -= 1
                break
    with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
        f.write('\n'.join(lines))
    print("Fixed.")
