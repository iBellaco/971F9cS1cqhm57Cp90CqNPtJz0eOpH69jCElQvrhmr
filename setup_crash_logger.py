import re

with open('app/src/main/java/com/example/WildRiftApp.kt', 'r') as f:
    text = f.read()

text = text.replace("super.onCreate()", "super.onCreate()\n        com.example.util.CrashLogger.init(this)")

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w') as f:
    f.write(text)

