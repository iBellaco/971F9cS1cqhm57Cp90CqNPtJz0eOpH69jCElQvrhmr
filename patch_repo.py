import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r', encoding='utf-8') as f:
    content = f.read()

old_offrole = r'reasonParts\.add\("Este campeón no es idóneo para esta línea\. Jugarlo aquí es considerado atípico o desventajoso para el equipo\."\)'
new_offrole = r'''val msgEs = "Llevar a ${champ.name} a ${com.example.util.trStr(lang, myRole.displayName)} es una selección atípica (off-meta). Sus habilidades no están diseñadas para ganar esta línea. ${champ.tacticalAdvice}"
            val msgPt = "Levar ${champ.name} para ${com.example.util.trStr(lang, myRole.displayName)} é uma escolha atípica (off-meta). Suas habilidades não são projetadas para esta rota. ${champ.tacticalAdvice}"
            val msgEn = "Taking ${champ.name} to ${com.example.util.trStr(lang, myRole.displayName)} is an off-meta pick. Their kit isn't designed for this lane. ${champ.tacticalAdvice}"
            reasonParts.add(t(lang, msgEn, msgPt, msgEs))'''

content = re.sub(old_offrole, new_offrole, content)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w', encoding='utf-8') as f:
    f.write(content)

