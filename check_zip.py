import json

with open('.aistudio/artifacts/brain/0d5fc0e7-b1ba-496e-8218-98d049fad73b/.system_generated/logs/transcript.jsonl', 'r') as f:
    lines = f.readlines()

last_user_msg = ""
for line in reversed(lines):
    data = json.loads(line)
    if data.get('role') == 'user':
        last_user_msg = data['parts'][0]['text']
        break

print("Length of last message:", len(last_user_msg))
print("Contains U+FFFD (corrupted binary)?", "\ufffd" in last_user_msg)
