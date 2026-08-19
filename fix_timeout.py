with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    content = f.read()

# Make the catch block more descriptive if it times out
content = content.replace('AppLogger.e("DB_TEST", "Error adding data to Firestore", e)', '''
                            if (e is SecurityException) {
                                AppLogger.e("DB_TEST", "Ignored System SecurityException: ${e.message}")
                            } else {
                                AppLogger.e("DB_TEST", "Error adding data to Firestore", e)
                            }''')

with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'w') as f:
    f.write(content)
