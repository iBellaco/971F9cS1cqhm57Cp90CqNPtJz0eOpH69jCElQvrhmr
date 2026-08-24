import re

with open("app/src/main/java/com/example/ui/components/admin/AdminRunesSpellsEditor.kt", "r") as f:
    code = f.read()

# Let's count braces to see where the issue is.
open_b = code.count('{')
close_b = code.count('}')
print(f"Open: {open_b}, Close: {close_b}")

