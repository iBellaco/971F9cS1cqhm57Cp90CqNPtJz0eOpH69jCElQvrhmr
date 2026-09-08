import re

text = "Kimmy Babadei..."
KNOWN = {"annie": "annie", "miss fortune": "miss_fortune"}

def clean(t):
    t = t.lower()
    t = re.sub(r'[^a-z0-9]', '', t)
    return t

print(clean(text))
