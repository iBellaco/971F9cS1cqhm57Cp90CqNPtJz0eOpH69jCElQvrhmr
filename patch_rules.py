import sys

file_path = "firestore.rules"
with open(file_path, "r") as f:
    content = f.read()

old_update = """      allow update: if request.auth != null && request.auth.uid == userId
        && !request.resource.data.diff(resource.data).affectedKeys()
             .hasAny(['role','premiumUntil','banned','registeredDevices']);"""
new_update = """      allow update: if request.auth != null && request.auth.uid == userId
        && !request.resource.data.diff(resource.data).affectedKeys().hasAny(['role','premiumUntil','banned'])
        && (
             !request.resource.data.diff(resource.data).affectedKeys().hasAny(['registeredDevices'])
             || request.resource.data.registeredDevices.size() <= 2
             || request.auth.token.admin == true
        );"""
content = content.replace(old_update, new_update)

with open(file_path, "w") as f:
    f.write(content)
