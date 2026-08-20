with open("gradle/libs.versions.toml", "r") as f:
    content = f.read()

if "kotlinxSerializationJson =" not in content:
    content = content.replace("[versions]\n", "[versions]\nkotlinxSerializationJson = \"1.6.3\"\n")
if "kotlinx-serialization-json = " not in content:
    content = content.replace("[libraries]\n", "[libraries]\nkotlinx-serialization-json = { group = \"org.jetbrains.kotlinx\", name = \"kotlinx-serialization-json\", version.ref = \"kotlinxSerializationJson\" }\n")
if "kotlin-serialization = " not in content:
    content = content.replace("[plugins]\n", "[plugins]\nkotlin-serialization = { id = \"org.jetbrains.kotlin.plugin.serialization\", version.ref = \"kotlin\" }\n")

with open("gradle/libs.versions.toml", "w") as f:
    f.write(content)

with open("app/build.gradle.kts", "r") as f:
    gradle = f.read()

if "alias(libs.plugins.kotlin.serialization)" not in gradle:
    gradle = gradle.replace("plugins {", "plugins {\n  alias(libs.plugins.kotlin.serialization)")
if "implementation(libs.kotlinx.serialization.json)" not in gradle:
    gradle = gradle.replace("dependencies {", "dependencies {\n  implementation(libs.kotlinx.serialization.json)")

with open("app/build.gradle.kts", "w") as f:
    f.write(gradle)

with open("build.gradle.kts", "r") as f:
    root_gradle = f.read()
if "alias(libs.plugins.kotlin.serialization)" not in root_gradle:
    root_gradle = root_gradle.replace("plugins {", "plugins {\n  alias(libs.plugins.kotlin.serialization) apply false")
with open("build.gradle.kts", "w") as f:
    f.write(root_gradle)
