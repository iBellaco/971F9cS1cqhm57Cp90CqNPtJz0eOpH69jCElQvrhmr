import re

with open('app/src/main/java/com/example/ui/screens/OnboardingScreen.kt', 'r') as f:
    content = f.read()

bad_str = """            highlights = listOf(
                tr("Historial de drafts y estadísticas de Win Rate personal"),
                tr("Sistema para añadir campeones a tu lista de favoritos"),
                tr("Cambio de temas visuales exclusivos (Noxus, Jonia, Vacío)"),
                tr("Selección y desbloqueo de Avatares de Runaterra"),
                tr("Análisis táctico y Coaching Challenger avanzado")
            )"""

good_str = """            highlights = listOf(
                tr("Historial de drafts y estadísticas de Win Rate personal"),
                tr("Sistema para añadir campeones a tu lista de favoritos"),
                tr("Cambio de temas visuales exclusivos (Noxus, Jonia, Vacío)"),
                tr("Selección y desbloqueo de Avatares de Runaterra")
            )"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/ui/screens/OnboardingScreen.kt', 'w') as f:
    f.write(content)
