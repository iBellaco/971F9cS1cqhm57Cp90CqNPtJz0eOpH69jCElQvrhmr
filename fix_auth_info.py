import re

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'r') as f:
    text = f.read()

# Fix the import lines
text = re.sub(r'import androidx\.compose\.material\.icons\.filled\.Lock.*', 'import androidx.compose.material.icons.filled.Info', text)
text = re.sub(r'import androidx\.compose\.material\.icons\.filled\.LockOpen.*', '', text)
text = re.sub(r'import androidx\.compose\.material\.icons\.filled\.LockOpenOff.*', '', text)

# Fix the icon usage
text = re.sub(r'imageVector = if \(isEmailVisible\) .*? else .*?,', 'imageVector = androidx.compose.material.icons.filled.Info,', text)

with open('app/src/main/java/com/example/ui/auth/AuthScreen.kt', 'w') as f:
    f.write(text)
