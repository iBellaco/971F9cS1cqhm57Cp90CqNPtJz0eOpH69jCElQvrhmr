import sys

with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

target = """        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(navBg)
            ) {"""

replacement = """        bottomBar = {
            Column {
                com.example.ui.components.AdmobBanner()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(navBg)
                ) {"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
        f.write(content)
    print("Patched bottomBar in MainActivity.kt")
else:
    print("Target not found in MainActivity.kt")

