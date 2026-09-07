import sys

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    lines = f.readlines()

for i, line in enumerate(lines):
    if "auditList.add(\"Posición de slot:" in line:
        for j in range(i, i+15):
            print(f"{j+1}: {lines[j].rstrip()}")
        break
