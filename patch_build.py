with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if "libs.firebase.storage" not in content:
    content = content.replace(
        'implementation(libs.firebase.firestore)',
        'implementation(libs.firebase.firestore)\n    implementation(libs.firebase.storage)'
    )
    with open('app/build.gradle.kts', 'w') as f:
        f.write(content)
