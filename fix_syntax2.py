with open('app/src/main/java/com/example/MainActivity.kt', 'r') as f:
    c = f.read()

# I had one extra closing brace on line 171
# Let's count them
c = c.rstrip()
if c.endswith('}'):
    c = c[:-1]

with open('app/src/main/java/com/example/MainActivity.kt', 'w') as f:
    f.write(c)
