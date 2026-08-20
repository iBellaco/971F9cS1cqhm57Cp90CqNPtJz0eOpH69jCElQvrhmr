with open("app/src/main/java/com/example/util/AppUpdateManager.kt", "r") as f:
    content = f.read()

content = content.replace("import kotlinx.coroutines.tasks.await\n", "")

with open("app/src/main/java/com/example/util/AppUpdateManager.kt", "w") as f:
    f.write(content)
