import re

with open('app/src/main/java/com/example/WildRiftApp.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Fix double okHttpClient block
content = re.sub(r'\.okHttpClient \{\s*OkHttpClient\.Builder\(\)[\s\S]*?\.build\(\)\s*\}\s*\.okHttpClient \{', '.okHttpClient {', content)

with open('app/src/main/java/com/example/WildRiftApp.kt', 'w', encoding='utf-8') as f:
    f.write(content)
