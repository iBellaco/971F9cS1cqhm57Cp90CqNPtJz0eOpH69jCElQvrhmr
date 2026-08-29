with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'r') as f:
    lines = f.readlines()

out = []
for idx, line in enumerate(lines):
    if idx == 73 and "}" in line:
        continue  # skip the extra closing brace
    out.append(line)

with open('app/src/main/java/com/example/util/SubscriptionManager.kt', 'w') as f:
    f.writelines(out)
