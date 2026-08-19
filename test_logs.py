with open('app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt', 'r') as f:
    c = f.read()
if "val logs by com.example.util.AppLogger.logs.collectAsState()" in c:
    print("Logs state reading correctly configured.")
