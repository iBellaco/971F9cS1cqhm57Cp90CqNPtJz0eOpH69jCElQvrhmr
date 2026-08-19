with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    c = f.read()

# Make sure English actually works by passing a proper composition local.
# The `val LocalLanguage = compositionLocalOf { "es" }` is static unless provided dynamically.
if 'CompositionLocalProvider(LocalLanguage provides selectedLanguage)' not in c:
    c = c.replace('Surface(', 'CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) {\nSurface(')
    c = c.replace('} // End Surface', '} // End Surface\n}') # might be tricky, let's just use regex

import re
c = re.sub(r'(Surface.*?\{)', r'CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) { \1', c, flags=re.DOTALL)
# wait, simpler: replace all `WildRiftAssistantTheme {` with `WildRiftAssistantTheme { CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) {`
c = re.sub(r'(WildRiftAssistantTheme\s*\{)', r'\1\n                    CompositionLocalProvider(com.example.util.LocalLanguage provides selectedLanguage) {', c)

# And add the closing brace
c = re.sub(r'(\}\s*\n\s*\}\s*)$', r'}\n\1', c)

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(c)
