import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

# find the last '}'
last_brace_idx = content.rfind('}')
if last_brace_idx != -1:
    content = content[:last_brace_idx] + content[last_brace_idx+1:]

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
