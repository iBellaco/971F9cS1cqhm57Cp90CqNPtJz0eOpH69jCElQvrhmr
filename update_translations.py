import re

with open("app/src/main/java/com/example/util/Translator.kt", "r") as f:
    content = f.read()

# For pt
pt_additions = """
        "¡Bienvenidos!" to "Bem-vindos!",
        "Notas del Nuevo Parche" to "Notas do Novo Patch",
        "• Sincronización automática con: " to "• Sincronização automática com: ",
        "• Análisis táctico actualizado con las últimas estadísticas del meta.\n" to "• Análise tática atualizada com as últimas estatísticas do meta.\n",
        "• Ajustes y corrección de íconos rúnicos." to "• Ajustes e correção de ícones rúnicos.",
        "Organización: Coach de Élite (Wild Rift Drafting)" to "Organização: Coach de Elite (Wild Rift Drafting)",
        "Entendido" to "Entendido",
        "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad." to "O inimigo tem alta iniciação de CC com Sett e Vi. É aconselhável desengage, escudos antimagia ou tenacidade.",
        "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank." to "Caitlyn inimiga tem vantagem de alcance na Rota do Dragão. Priorize anulação com Viego ou puxão com Nautilus/Blitzcrank.",
        "Peligro de asesinos de burst" to "Perigo de assassinos de burst",
        "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus)." to "Imprescindível Zhonya/Estase e CC garantido (Lulu, Malzahar, Nautilus).",
        "Composición rival pesada" to "Composição inimiga pesada",
        "Requiere daño verdadero y % vida máxima." to "Requer dano verdadeiro e % de vida máxima.",
        "Morgana o Janna" to "Morgana ou Janna",
        "Nautilus o Viego" to "Nautilus ou Viego",
        "Lulu, Nautilus o Zhonya" to "Lulu, Nautilus ou Zhonya",
        "Vayne, Sett, Gwen o Liandry" to "Vayne, Sett, Gwen ou Liandry",
        "Conquistador (Precisión)" to "Conquistador (Precisão)",
        "Táctico" to "Tático",
        "Daño Físico" to "Dano Físico",
        "Daño Mágico" to "Dano Mágico",
        "Daño Verdadero" to "Dano Verdadeiro",
        "Tanque" to "Tanque",
        "Mago" to "Mago",
        "Luchador" to "Lutador",
        "Asesino" to "Assassino",
        "Tirador" to "Atirador",
        "Soporte" to "Suporte",
        "Top" to "Topo",
        "Jungla" to "Caçador",
        "Medio" to "Meio",
        "Dúo" to "Atirador",
        "Línea de Barón" to "Rota do Barão",
        "Línea de Medio" to "Rota do Meio",
        "Línea de Dragón" to "Rota do Dragão",
        "💡 Si tu dispositivo muestra 'conflicto con un paquete', desinstala la versión anterior de tu teléfono una sola vez e instala la nueva APK (ocurre por cambio de firma de depurado a producción)." to "💡 Se o seu dispositivo mostrar 'conflito de pacote', desinstale a versão anterior do seu telefone uma vez e instale o novo APK (ocorre devido à alteração da assinatura de depuração para produção)."
"""

# For en
en_additions = """
        "¡Bienvenidos!" to "Welcome!",
        "Notas del Nuevo Parche" to "New Patch Notes",
        "• Sincronización automática con: " to "• Automatic synchronization with: ",
        "• Análisis táctico actualizado con las últimas estadísticas del meta.\n" to "• Tactical analysis updated with the latest meta stats.\n",
        "• Ajustes y corrección de íconos rúnicos." to "• Rune icon adjustments and fixes.",
        "Organización: Coach de Élite (Wild Rift Drafting)" to "Organization: Elite Coach (Wild Rift Drafting)",
        "Entendido" to "Got it",
        "El rival tiene alta iniciación de CC con Sett y Vi. Se aconseja desengage, escudos antimagia o tenacidad." to "The enemy has high CC engage with Sett and Vi. Recommend disengage, anti-magic shields or tenacity.",
        "Caitlyn rival tiene ventaja de rango en carril de Dragón. Prioriza anulación con Viego o agarre con Nautilus/Blitzcrank." to "Enemy Caitlyn has range advantage in Dragon Lane. Prioritize shutdown with Viego or hooks with Nautilus/Blitzcrank.",
        "Peligro de asesinos de burst" to "Danger of burst assassins",
        "Imprescindible Zhonya/Estasis y CC garantizado (Lulu, Malzahar, Nautilus)." to "Essential Zhonya/Stasis and reliable CC (Lulu, Malzahar, Nautilus).",
        "Composición rival pesada" to "Heavy enemy comp",
        "Requiere daño verdadero y % vida máxima." to "Requires true damage and % max health.",
        "Morgana o Janna" to "Morgana or Janna",
        "Nautilus o Viego" to "Nautilus or Viego",
        "Lulu, Nautilus o Zhonya" to "Lulu, Nautilus or Zhonya",
        "Vayne, Sett, Gwen o Liandry" to "Vayne, Sett, Gwen or Liandry",
        "Conquistador (Precisión)" to "Conqueror (Precision)",
        "Táctico" to "Tactical",
        "Daño Físico" to "Physical Damage",
        "Daño Mágico" to "Magic Damage",
        "Daño Verdadero" to "True Damage",
        "Tanque" to "Tank",
        "Mago" to "Mage",
        "Luchador" to "Fighter",
        "Asesino" to "Assassin",
        "Tirador" to "Marksman",
        "Soporte" to "Support",
        "Top" to "Top",
        "Jungla" to "Jungle",
        "Medio" to "Mid",
        "Dúo" to "Duo",
        "Línea de Barón" to "Baron Lane",
        "Línea de Medio" to "Mid Lane",
        "Línea de Dragón" to "Dragon Lane",
        "💡 Si tu dispositivo muestra 'conflicto con un paquete', desinstala la versión anterior de tu teléfono una sola vez e instala la nueva APK (ocurre por cambio de firma de depurado a producción)." to "💡 If your device shows 'package conflict', uninstall the previous version from your phone once and install the new APK (occurs due to signature change from debug to production)."
"""

content = re.sub(r'("pt" to mapOf\()', r'\1' + pt_additions + ',', content)
content = re.sub(r'("en" to mapOf\()', r'\1' + en_additions + ',', content)

with open("app/src/main/java/com/example/util/Translator.kt", "w") as f:
    f.write(content)
