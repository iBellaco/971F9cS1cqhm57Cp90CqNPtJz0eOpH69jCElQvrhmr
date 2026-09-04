import re

with open("app/src/main/java/com/example/ui/screens/FAQScreen.kt", "r") as f:
    content = f.read()

start_index = content.find("val faqs = remember {")
end_index = content.find("    val filteredFaqs = remember(faqSearchQuery) {")

if start_index != -1 and end_index != -1:
    new_faqs = """    val faqs = remember {
        listOf(
            Pair(
                "¿Qué hacer si presiono 'Activar' y no aparece el overlay flotante?",
                "Si presionaste el botón para iniciar el asistente y no ves la burbuja flotante, es muy probable que te falte otorgar el permiso de 'Mostrar sobre otras aplicaciones' (System Alert Window). Ve a Configuración de tu dispositivo > Aplicaciones > Wild Rift Drafting y habilita la opción de 'Aparecer encima'. Asegúrate también de que no tengas optimizadores de batería cerrando la aplicación en segundo plano."
            ),
            Pair(
                "¿Cómo funciona y para qué sirve el Panel de Draft?",
                "La herramienta de Draft está diseñada para analizar las composiciones (la tuya y la del rival) en tiempo real. Selecciona tu posición en el mapa y los campeones que ya escogió el equipo enemigo. La app calculará instantáneamente tu condición de victoria, las debilidades del rival (por ejemplo, si necesitan cortacuras o penetración de armadura) y te recomendará los 3 mejores campeones junto con sus builds ideales para contrarrestarlos."
            ),
            Pair(
                "¿Cómo usar el Panel de Usuario y qué funciones tiene?",
                "En el Panel de Usuario (icono de perfil), puedes personalizar toda tu experiencia. Desde allí puedes: cambiar tu avatar (eligiendo de un amplio catálogo oficial de Wild Rift), modificar el tema visual de la aplicación para adaptarlo a las regiones de Runaterra, revisar tu estado de suscripción y, muy importante, consultar el 'Historial de Drafts' guardado localmente para repasar tus estrategias pasadas."
            ),
            Pair(
                "¿Por qué cambian las recomendaciones dependiendo de si soy 1er Pick o último Pick?",
                "El algoritmo evalúa el riesgo. Si vas a seleccionar de los primeros (Blind Pick), el asistente te sugerirá campeones seguros y versátiles (Blind picks) que no son fáciles de contrarrestar. Si eres el último en elegir (Last Pick), la aplicación se enfocará directamente en recomendarte el 'Counter' perfecto para destruir la alineación rival."
            ),
            Pair(
                "¿Cómo funcionan las Tier Lists en la aplicación?",
                "La sección de Tier List muestra el estado actual del Meta de Wild Rift. Los campeones S+ y S son los más dominantes del parche actual por su alto winrate y presencia en el juego profesional. Puedes usar los filtros por línea (Top, Jungla, Mid, ADC, Soporte) para identificar rápidamente qué campeones deberías priorizar o banear en tus rankeds."
            ),
            Pair(
                "¿Qué información detallada me da el asistente durante el juego?",
                "Una vez activado el overlay en la selección de campeones, no solo te recomienda quién jugar. También te entrega la configuración exacta de Runas (principal y secundarias), Hechizos de Invocador, tu objeto inicial, tus picos de poder (Core Items) y las opciones situacionales (ej. cuándo armar Resistencia Mágica o Tenacidad). Por último, te da un tip Macro para el inicio de la partida."
            ),
            Pair(
                "¿Por qué es necesario descargar recursos para usar la aplicación offline?",
                "Wild Rift Drafting cuenta con imágenes de alta calidad (cientos de íconos de hechizos, runas, objetos y campeones). Descargar los recursos en la pantalla principal te permite usarlos sin conexión a internet y hace que la interfaz y el overlay carguen de manera instantánea mientras estás dentro del juego, ahorrándote batería y datos móviles."
            ),
            Pair(
                "La burbuja flotante me estorba durante la partida, ¿cómo la quito?",
                "¡No te preocupes! El overlay está pensado únicamente para la fase de Draft (selección de campeones). Una vez que termine el Draft, puedes cerrar la burbuja desde la 'X' de la interfaz flotante, o entrar a la aplicación y detener el servicio desde el menú principal para jugar cómodamente sin distracciones en tu pantalla."
            ),
            Pair(
                "¿Cómo consulto el catálogo de runas, objetos y hechizos?",
                "En la pantalla principal, dirígete a la sección 'Catálogo Meta'. Allí podrás alternar entre diferentes pestañas (Objetos, Runas, Hechizos). Puedes usar el buscador o cambiar la vista entre Cuadrícula o Detallado para explorar libremente qué hace cada ítem y sus estadísticas sin tener que estar dentro de una partida."
            )
        )
    }

"""
    new_content = content[:start_index] + new_faqs + content[end_index:]
    with open("app/src/main/java/com/example/ui/screens/FAQScreen.kt", "w") as f:
        f.write(new_content)
    print("FAQ updated successfully")
else:
    print("Could not find FAQ boundaries")
