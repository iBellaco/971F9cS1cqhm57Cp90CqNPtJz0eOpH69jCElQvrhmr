with open('app/src/main/java/com/example/WildRiftApp.kt', 'r') as f:
    content = f.read()

content = content.replace('AppLogger.e("CRASH", "Uncaught exception on thread ${thread.name}", exception)', '''
            if (exception is SecurityException && exception.message?.contains("com.google.android.gms") == true) {
                AppLogger.d("SYSTEM_WARNING", "Ignored GMS Emulator SecurityException: ${exception.message}")
            } else {
                AppLogger.e("CRASH", "Uncaught exception on thread ${thread.name}", exception)
            }
''')

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w') as f:
    f.write(content)
