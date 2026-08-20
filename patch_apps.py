import os
import re

for filename in ["WildRiftApp.kt", "WildRiftApplication.kt"]:
    filepath = os.path.join("app/src/main/java/com/example", filename)
    if os.path.exists(filepath):
        with open(filepath, "r") as f:
            content = f.read()
        
        content = re.sub(r'import com\.google\.firebase\.FirebaseApp\n', '', content)
        firebase_init_block = r'''        // Initialize Firebase if not already initialized
        try \{
            FirebaseApp\.initializeApp\(this\)
            AppLogger\.d\("APP", "Firebase initialized successfully in Application class"\)
        \} catch \(e: Exception\) \{
            AppLogger\.e\("APP", "Failed to initialize Firebase", e\)
        \}'''
        content = re.sub(firebase_init_block, '', content)
        
        # also general catch all
        content = re.sub(r'\s*FirebaseApp\.initializeApp\(this\)\s*', '', content)
        
        with open(filepath, "w") as f:
            f.write(content)
