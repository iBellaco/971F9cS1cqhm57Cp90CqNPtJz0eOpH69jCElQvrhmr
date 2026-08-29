import re
import os

# 1. Update libs.versions.toml
with open('gradle/libs.versions.toml', 'r') as f:
    toml = f.read()

if 'firebase-messaging' not in toml:
    toml = toml.replace(
        'firebase-auth = { group = "com.google.firebase", name = "firebase-auth" }',
        'firebase-auth = { group = "com.google.firebase", name = "firebase-auth" }\nfirebase-messaging = { group = "com.google.firebase", name = "firebase-messaging" }'
    )
    with open('gradle/libs.versions.toml', 'w') as f:
        f.write(toml)

# 2. Update app/build.gradle.kts
with open('app/build.gradle.kts', 'r') as f:
    build = f.read()

if 'libs.firebase.messaging' not in build:
    build = build.replace(
        'implementation(libs.firebase.auth)',
        'implementation(libs.firebase.auth)\n    implementation(libs.firebase.messaging)'
    )
    with open('app/build.gradle.kts', 'w') as f:
        f.write(build)

# 3. Update AndroidManifest.xml
with open('app/src/main/AndroidManifest.xml', 'r') as f:
    manifest = f.read()

if '.service.FCMService' not in manifest:
    service_decl = """        <!-- Firebase Cloud Messaging Service -->
        <service
            android:name=".service.FCMService"
            android:exported="true">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>"""
    
    manifest = manifest.replace('</application>', service_decl + '\n    </application>')
    with open('app/src/main/AndroidManifest.xml', 'w') as f:
        f.write(manifest)

