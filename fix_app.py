with open("app/src/main/java/com/example/WildRiftApplication.kt", "r") as f:
    content = f.read()

import re
content = re.sub(r'try \{Log\.d\("WildRiftApplication", "FirebaseApp successfully initialized in Application class\."\)\n\s*\} catch \(e: Exception\) \{\n\s*Log\.e\("WildRiftApplication", "Failed to initialize FirebaseApp", e\)\n\s*\}', '', content)

with open("app/src/main/java/com/example/WildRiftApplication.kt", "w") as f:
    f.write(content)
