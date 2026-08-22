import re

with open('app/build.gradle.kts', 'r') as f:
    content = f.read()

if 'implementation(libs.androidx.work.runtime.ktx)' not in content:
    content = content.replace('implementation(libs.kotlinx.coroutines.android)', 'implementation(libs.androidx.work.runtime.ktx)\n  implementation(libs.jsoup)\n  implementation(libs.kotlinx.coroutines.android)')

# Increment version
content = content.replace('versionCode = 114', 'versionCode = 115')
content = content.replace('versionName = "2.13"', 'versionName = "2.14"')

with open('app/build.gradle.kts', 'w') as f:
    f.write(content)
