import re

with open('app/src/main/java/com/example/util/CoachingGenerator.kt', 'r') as f:
    text = f.read()

def replace_msg(content, type_name, es_msg, pt_msg, en_msg):
    # We will just replace the whole 'when (sourceRole) { ... }' block inside each language if for that type.
    # Actually, it's easier to just replace the whole 'if (isEs) { when(sourceRole) ... }' structure.
    pass

# Let's just rewrite the whole return block for the 'when (type)'.
new_when_block = """        return when (type) {
            "Ventaja" -> {
                if (isEs) "Este campeón tiene una fuerte ventaja sobre $target en la fase de líneas, aprovéchalo."
                else if (isPt) "Este campeão tem uma forte vantagem sobre $target na fase de rotas, aproveite."
                else "This champion has a strong advantage over $target in the laning phase, use it."
            }
            "Debilidad" -> {
                if (isEs) "Este campeón es débil contra $target. Juega con seguridad y espera ayuda de tu equipo."
                else if (isPt) "Este campeão é fraco contra $target. Jogue com segurança e espere ajuda da sua equipe."
                else "This champion is weak against $target. Play safely and wait for team assistance."
            }
            "Situacional" -> {
                if (isEs) "Este es un objeto situacional. Cómpralo contra $target para ganar ventaja."
                else if (isPt) "Este é um item situacional. Compre-o contra $target para ganhar vantagem."
                else "This is a situational item. Buy it against $target to gain an advantage."
            }
            else -> { // Sinergia
                if (isEs) "Excelente sinergia con $target para ganar las peleas de equipo."
                else if (isPt) "Excelente sinergia com $target para vencer as lutas de equipe."
                else "Excellent synergy with $target to win teamfights."
            }
        }"""

pattern = r'return when \(type\) \{.*?^        \}'
text = re.sub(pattern, new_when_block, text, flags=re.MULTILINE | re.DOTALL)

with open('app/src/main/java/com/example/util/CoachingGenerator.kt', 'w') as f:
    f.write(text)
print("Replaced Messages")
