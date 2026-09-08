import sys

with open("app/build.gradle.kts", "r") as f:
    content = f.read()

content = content.replace('dependencies {', 'dependencies {\n  implementation(libs.play.services.ads)')

with open("app/build.gradle.kts", "w") as f:
    f.write(content)
print("Updated app/build.gradle.kts")
