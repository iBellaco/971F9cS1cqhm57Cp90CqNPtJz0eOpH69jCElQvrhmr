with open("app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt", "r") as f:
    content = f.read()

content = content.replace("fun DatabaseTestScreen()", "fun DatabaseTestScreen(onContinue: () -> Unit = {})")
content = content.replace("Text(\"Test Supabase Connection\")\n        }", "Text(\"Test Supabase Connection\")\n        }\n        Spacer(modifier = Modifier.height(16.dp))\n        Button(onClick = onContinue) { Text(\"Continuar a la App\") }")

with open("app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt", "w") as f:
    f.write(content)
