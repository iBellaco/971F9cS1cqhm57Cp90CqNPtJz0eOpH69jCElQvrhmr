with open("gradle/libs.versions.toml", "r") as f:
    content = f.read()

if "kotlinxCoroutinesPlayServices" not in content:
    content = content.replace("[versions]\n", "[versions]\nkotlinxCoroutinesPlayServices = \"1.10.2\"\n")
if "kotlinx-coroutines-play-services" not in content:
    content = content.replace("[libraries]\n", "[libraries]\nkotlinx-coroutines-play-services = { group = \"org.jetbrains.kotlinx\", name = \"kotlinx-coroutines-play-services\", version.ref = \"kotlinxCoroutinesPlayServices\" }\n")

with open("gradle/libs.versions.toml", "w") as f:
    f.write(content)

with open("app/build.gradle.kts", "r") as f:
    gradle = f.read()

if "implementation(libs.kotlinx.coroutines.play.services)" not in gradle:
    gradle = gradle.replace("implementation(libs.kotlinx.coroutines.core)", "implementation(libs.kotlinx.coroutines.core)\n  implementation(libs.kotlinx.coroutines.play.services)")

with open("app/build.gradle.kts", "w") as f:
    f.write(gradle)
