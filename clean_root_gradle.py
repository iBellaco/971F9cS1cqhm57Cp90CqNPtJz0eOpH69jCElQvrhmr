with open("build.gradle.kts", "r") as f:
    content = f.read()

content = content.replace("  alias(libs.plugins.google.services) apply false\n", "")

with open("build.gradle.kts", "w") as f:
    f.write(content)
