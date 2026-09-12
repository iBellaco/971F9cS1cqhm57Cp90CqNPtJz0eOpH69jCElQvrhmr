with open('gradle/libs.versions.toml', 'r') as f:
    content = f.read()

if "firebase-storage =" not in content:
    content = content.replace(
        'firebase-messaging = { group = "com.google.firebase", name = "firebase-messaging" }',
        'firebase-messaging = { group = "com.google.firebase", name = "firebase-messaging" }\nfirebase-storage = { group = "com.google.firebase", name = "firebase-storage" }'
    )
    with open('gradle/libs.versions.toml', 'w') as f:
        f.write(content)
