import re

with open("app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt", "r") as f:
    content = f.read()

content = re.sub(r'import com\.google\.firebase\.firestore\.FirebaseFirestore\n', '', content)
content = content.replace("Firestore", "Supabase")
content = content.replace("FIRESTORE", "SUPABASE")

# Change the Firestore specific code. It probably does FirebaseFirestore.getInstance()...
content = re.sub(r'val db = FirebaseFirestore\.getInstance\(\)', 'val db = com.example.data.supabase.SupabaseClientManager.client', content)

# But wait, postgrest syntax is completely different. Let's just remove the test implementation or replace it.
with open("app/src/main/java/com/example/ui/screens/DatabaseTestScreen.kt", "w") as f:
    f.write(content)
