import re

with open("app/src/main/java/com/example/MainActivity.kt", "r") as f:
    content = f.read()

content = re.sub(r'import com\.google\.firebase\.FirebaseApp\n', '', content)
firebase_init_block = r'''        try {
            FirebaseApp\.initializeApp\(this\)
            com\.example\.util\.AppLogger\.d\("APP", "Firebase initialized in MainActivity"\)
        } catch \(e: Exception\) {
            com\.example\.util\.AppLogger\.e\("APP", "Firebase init failed in MainActivity", e\)
        }'''
content = re.sub(firebase_init_block, '', content)

with open("app/src/main/java/com/example/MainActivity.kt", "w") as f:
    f.write(content)
