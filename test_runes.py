import re

text1 = "Reverberacción (Valor)"
text2 = "Valor: Fuente de Vida • Condicionamiento • Sobrecrecimiento • Golpe de Escudo"

keystone = text1.split(" (")[0].strip()
runes = [r.strip() for r in text2.split(":")[-1].split("•")]

print(f"Keystone: {keystone}")
print(f"Runes: {runes}")
