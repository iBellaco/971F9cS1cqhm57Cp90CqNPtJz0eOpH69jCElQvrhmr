import re

with open('gradle/libs.versions.toml', 'r') as f:
    content = f.read()

if 'workRuntimeKtx' not in content:
    content = content.replace('[versions]', '[versions]\nworkRuntimeKtx = "2.9.1"\njsoup = "1.18.1"')

if 'androidx-work-runtime-ktx' not in content:
    content = content.replace('[libraries]', '[libraries]\nandroidx-work-runtime-ktx = { group = "androidx.work", name = "work-runtime-ktx", version.ref = "workRuntimeKtx" }\njsoup = { group = "org.jsoup", name = "jsoup", version.ref = "jsoup" }')

with open('gradle/libs.versions.toml', 'w') as f:
    f.write(content)
