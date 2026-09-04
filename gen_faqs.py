import re

faqs = [
    ("¿Qué hacer si presiono 'Activar' y no aparece el overlay flotante?", "Si presionaste el botón para iniciar el asistente y no ves la burbuja flotante, es muy probable que te falte otorgar el permiso de 'Mostrar sobre otras aplicaciones' (System Alert Window). Ve a Configuración de tu dispositivo > Aplicaciones > Wild Rift Drafting y habilita la opción de 'Aparecer encima'. Asegúrate también de que no tengas optimizadores de batería cerrando la aplicación en segundo plano."),
    ("¿Cómo funciona y para qué sirve el Panel de Draft?", "La herramienta de Draft está diseñada para analizar las composiciones (la tuya y la del rival) en tiempo real. Selecciona tu posición en el mapa y los campeones que ya escogió el equipo enemigo. La app calculará instantáneamente tu condición de victoria, las debilidades del rival y te recomendará los 3 mejores campeones."),
    ("¿Cómo usar el Panel de Usuario y qué funciones tiene?", "En el Panel de Usuario (icono de perfil), puedes personalizar toda tu experiencia. Desde allí puedes: cambiar tu avatar, modificar el tema visual de la aplicación para adaptarlo a las regiones de Runaterra, y consultar el 'Historial de Drafts' guardado localmente."),
    ("¿Por qué cambian las recomendaciones dependiendo de si soy 1er Pick o último Pick?", "El algoritmo evalúa el riesgo. Si vas a seleccionar de los primeros (Blind Pick), el asistente te sugerirá campeones seguros y versátiles. Si eres el último en elegir (Last Pick), la aplicación se enfocará directamente en recomendarte el 'Counter' perfecto para destruir la alineación rival."),
    ("¿Cómo funcionan las Tier Lists en la aplicación?", "La sección de Tier List muestra el estado actual del Meta de Wild Rift. Los campeones S+ y S son los más dominantes del parche actual por su alto winrate y presencia. Puedes usar los filtros por línea para identificar rápidamente qué priorizar o banear."),
    ("¿Qué información detallada me da el asistente durante el juego?", "Una vez activado el overlay, no solo te recomienda quién jugar. También te entrega la configuración exacta de Runas (principal y secundarias), Hechizos de Invocador, tu objeto inicial, tus picos de poder (Core Items) y las opciones situacionales (ej. cuándo armar Resistencia Mágica)."),
    ("¿Por qué es necesario descargar recursos para usar la aplicación offline?", "Wild Rift Drafting cuenta con imágenes de alta calidad. Descargar los recursos en la pantalla principal te permite usarlos sin conexión a internet y hace que la interfaz y el overlay carguen de manera instantánea mientras estás dentro del juego, ahorrándote datos móviles."),
    ("La burbuja flotante me estorba durante la partida, ¿cómo la quito?", "¡No te preocupes! El overlay está pensado únicamente para la fase de Draft (selección de campeones). Una vez que termine el Draft, puedes cerrar la burbuja desde la 'X' de la interfaz flotante para jugar cómodamente sin distracciones."),
    ("¿Cómo consulto el catálogo de runas, objetos y hechizos?", "En la pantalla principal, dirígete a la sección 'Catálogo Meta'. Allí podrás alternar entre diferentes pestañas (Objetos, Runas, Hechizos). Puedes usar el buscador o cambiar la vista entre Cuadrícula o Detallado para explorar libremente."),
    ("¿La aplicación juega por mí?", "No, la aplicación es una herramienta analítica de soporte. Te provee la mejor información estadística y táctica en tiempo real, pero el uso de tus dedos en la pantalla sigue siendo 100% tu responsabilidad."),
    ("¿Me pueden banear por usar esta aplicación?", "No. Wild Rift Drafting no inyecta código, no modifica la memoria del juego y no es un hack. Simplemente es una superposición (overlay) en tu pantalla con una base de datos propia, tal como usar Discord o una calculadora."),
    ("¿Por qué el Yasuo de mi equipo siempre termina 0/10?", "Es la ley universal de Yasuo. El Yasuo de tu equipo siempre tendrá un mal día, mientras que el Yasuo enemigo jugará como un profesional en la final mundial. Tómalo con filosofía."),
    ("¿Si me compro una skin (aspecto), pegaré más fuerte?", "No, las skins no dan estadísticas adicionales ni daño en Wild Rift. Sin embargo, psicológicamente te hacen sentir más intimidante, y eso a veces es suficiente para ganar tu línea."),
    ("¿Qué significa Fedear (Feeding)?", "Morir repetidas veces a manos del enemigo, dándoles así una gran cantidad de oro y experiencia, haciéndolos inmensamente más fuertes que tu equipo."),
    ("¿Qué es un KS?", "Significa Kill Steal (Robar el asesinato) o Kill Secure (Asegurarlo), dependiendo a quién le preguntes. Es cuando alguien da el último golpe a un enemigo que otro jugador estuvo a punto de matar."),
    ("¿Esta app me asegura ganar el 100% de las partidas?", "Imposible. Te da la ventaja táctica definitiva desde la fase de selección, pero tus mecánicas, tu puntería y no pelear 1 contra 5 debajo de la torre enemiga son lo que realmente aseguran la victoria."),
    ("¿Cómo puedo subir a Challenger en un solo día?", "Despertando de tu sueño profundo. Subir a Challenger requiere tiempo, paciencia, mejorar tus mecánicas, no enfadarte con tu equipo y mucha dedicación. No hay atajos mágicos."),
    ("¿Puedo jugar a Teemo de Jungla?", "Poder, puedes. Pero tu equipo probablemente te va a reportar, el jungla enemigo te robará todos los campamentos y tendrás pesadillas. La app no lo recomienda."),
    ("¿Por qué el asistente me pide permiso de almacenamiento?", "Únicamente para guardar localmente la base de datos de imágenes, objetos y campeones. Así la app puede funcionar súper rápido y sin consumir internet mientras juegas."),
    ("¿Qué significa 'Macro' y 'Micro' juego?", "'Micro' son tus habilidades mecánicas: cómo esquivas, cómo usas tus combos. 'Macro' es tu inteligencia en el mapa: cuándo hacer el dragón, cuándo empujar torres, cuándo pelear. La app te ayuda enormemente con tu Macro."),
    ("¿Qué es hacer 'Snowball' (Bola de nieve)?", "Cuando sacas una pequeña ventaja al inicio (ej. una muerte) y la usas para conseguir más y más ventajas (oro, dragones) hasta volverte una fuerza imparable. Como una bola de nieve cayendo por una colina."),
    ("Mi equipo no para de morir y pelearse por el chat, ¿qué hago?", "Siléncialos a todos, respira hondo, céntrate en farmear tu oro e intenta carrear. Si la partida está totalmente perdida, úsala para practicar tus mecánicas sin estrés."),
    ("¿Para qué sirven los centinelas o 'Wards'?", "Son dispositivos invisibles que colocas en el mapa para que te den visión. Sirven para ver venir al jungla enemigo antes de que te ataque. Colocarlos salva vidas."),
    ("¿Qué significa 'Gankear'?", "Cuando el jungla u otro aliado abandona su línea y va a la tuya por sorpresa para emboscar a tu oponente y conseguir una muerte."),
    ("¿Por qué el jungla nunca me gankea?", "Tal vez estás empujando demasiado tu línea debajo de la torre enemiga y no hay espacio para atacar. O tal vez el jungla está ocupado haciendo objetivos. Culpar al jungla es el deporte nacional de los MOBAs."),
    ("¿Qué es dar un 'Leash'?", "Es cuando le das un par de golpes básicos al primer monstruo de la jungla al inicio de la partida para ayudar a tu jungla a no perder tanta vida. Es de buena educación."),
    ("¿Qué significa 'Peelar' (Peel)?", "Usar tus habilidades de control de masas o escudos para proteger al miembro más frágil y con más daño de tu equipo (usualmente el Tirador/ADC) para que los enemigos no lo maten."),
    ("¿Cuándo debo hacerme un objeto 'Cortacuras' (Heridas Graves)?", "Cuando el equipo enemigo tiene campeones que se curan demasiado como Soraka, Dr. Mundo, Vladimir, Yuumi o personajes con mucho robo de vida. Cómpralo temprano en la partida."),
    ("¿Qué es el Heraldo de la Grieta?", "El monstruo gigante morado que aparece en la fosa del Barón en los primeros minutos. Al matarlo, te suelta un ojo que puedes invocar para que embista y destruya las torres enemigas con mucha fuerza."),
    ("¿Debo ir por el Dragón o destruir una Torre?", "Depende del contexto (Macro). Si es el Dragón Anciano, su mejora es vital. Si es un dragón normal pero puedes tirar un Inhibidor, la torre suele valer más porque da presión de súper súbditos en el mapa."),
    ("¿Qué es el 'Split Push'?", "Es una estrategia donde un jugador empuja una línea lateral (top o bot) por su cuenta, obligando a uno o varios enemigos a ir a detenerlo, mientras el resto del equipo toma otro objetivo al otro lado del mapa."),
    ("¿Cómo activo el Modo Oscuro o cambio el Tema de la app?", "La app sincroniza el modo oscuro automáticamente con el sistema operativo de tu celular, pero puedes forzar y elegir temas (ej. Tema Jonia, Tema Freljord) desde el Panel de Usuario."),
    ("¿Por qué la app puede consumir algo extra de batería?", "Tener una ventana flotante analizando datos requiere un poco de procesamiento en segundo plano. Te recomendamos cerrar el overlay en cuanto termine la fase de Draft para ahorrar batería."),
    ("¿Qué significa CC?", "Control de Masas (Crowd Control). Son todas aquellas habilidades que limitan los movimientos del enemigo: aturdir, ralentizar, silenciar, derribar, cegar. El CC gana partidas."),
    ("¿Por qué mi Ping (latencia) está en 999 ms?", "Tu conexión a internet está fallando. Asegúrate de estar cerca del router Wi-Fi, de no estar descargando cosas de fondo o usar datos móviles si tu red es inestable. Jugar con lag es horrible."),
    ("¿A quién debo banear siempre en las clasificatorias?", "Al campeón más fuerte (S+) del parche actual según nuestra Tier List, o a ese campeón que personalmente detestas enfrentar (como Master Yi o Yuumi) porque te frustra la experiencia."),
    ("¿Qué es un 'OTP' (One Trick Pony)?", "Un jugador que juega exclusivamente a un único campeón todo el tiempo, ignorando el meta. Suelen ser increíblemente buenos mecánicamente con ese campeón, aunque pueden sufrir si se lo banean."),
    ("¿Qué significa estar 'AFK'?", "Away From Keyboard (Lejos del teclado). Significa que un jugador está desconectado, inactivo en la base o se fue al baño en medio de una pelea de equipo."),
    ("¿Es obligatorio usar el chat de texto para ganar?", "Para nada. De hecho, a menudo el chat de texto solo sirve para distraerse. Usar los 'pings' (señales rápidas del mapa) es mil veces más efectivo y rápido para comunicarte con el equipo."),
    ("¿Por qué los ADC (Tiradores) mueren tan rápido?", "Porque están diseñados para hacer el mayor daño posible a distancia a costa de ser sumamente frágiles y tener poca vida (Glass Cannon). Todo el equipo enemigo intentará asesinarlos primero."),
    ("¿Qué hago si juego contra un 'Smurf'?", "Un Smurf es un jugador de rango muy alto jugando en una cuenta de rango bajo. Mantén la calma, juega muy defensivo bajo tu torre, no le des muertes gratuitas y espera a los combates grupales de tu equipo."),
    ("¿Qué es 'Freezar' o congelar la línea?", "Una técnica donde solo das el último golpe a los súbditos en el último milisegundo. Su objetivo es mantener a los súbditos congelados cerca de tu torre, dejando al enemigo expuesto si intenta acercarse por oro."),
    ("¿Puedo jugar con Garen en la posición de ADC?", "Si estás jugando partidas normales para reírte con un amigo, haz lo que quieras. Si estás jugando clasificatorias (Ranked), no seas troll y respeta los roles, tus compañeros te lo agradecerán."),
    ("¿Qué pasa si desinstalo la app? ¿Pierdo mi historial?", "Sí. Por privacidad, tus Drafts guardados, configuraciones y preferencias se almacenan estrictamente de forma local en tu dispositivo con una base de datos SQLite. Si borras la app, los datos se eliminan con ella."),
    ("¿Para qué sirve el hechizo 'Castigo' (Smite)?", "Es obligatorio ÚNICAMENTE para el Jungla. Se usa para matar monstruos de la jungla más rápido y asegurar (dar el golpe de gracia) objetivos críticos como Dragones y Barones. Si no eres jungla, jamás te lo pongas."),
    ("Si compro dos o tres botas diferentes, ¿correré el triple de rápido?", "No, es un error clásico de novato. En Wild Rift, las mejoras de velocidad de movimiento y los encantamientos de las botas no se acumulan de esta forma. Estarás desperdiciando oro valioso."),
    ("¿Quién es el mejor campeón del juego actualmente?", "Ninguno es el mejor por siempre. Riot Games hace balances constantes. Revisa nuestra sección 'Tier List' para saber quién está en Tier S+ esta semana."),
    ("¿Qué significa 'Divear' (hacer Dive)?", "Lanzarse valientemente (o tontamente) a atacar y matar a un enemigo mientras estás debajo de SU propia torre, asumiendo que la torre te disparará a ti. Requiere coordinación y calcular bien el daño."),
    ("¿Por qué saco el MVP y muchísimas muertes, pero igual pierdo?", "Porque esto es un juego de tomar objetivos (Torres, Dragones, Nexo), no un Deathmatch de Call of Duty. Puedes tener 20 kills, pero si tu equipo no empuja torres, terminarán perdiendo por el mapa."),
    ("¿Para qué sirve matar al Escurridizo del Río (Cangrejo)?", "Es muy importante. Al matarlo te da oro, experiencia, y lo más valioso: un campo de visión inamovible en el centro del río que además le otorga velocidad a tus aliados cuando pasan por encima."),
    ("¿Debería perseguir a un Singed enemigo que corre con poca vida?", "La regla número 1 y más sagrada de la historia de los MOBAs: NUNCA, jamás, bajo ninguna circunstancia, persigas a un Singed por el mapa. Es una trampa, te ahogarás en su veneno."),
    ("¿Qué es hacer 'Focus' en una pelea?", "Significa coordinar a tu equipo para enfocar y lanzar todo el daño posible sobre un solo objetivo enemigo importante (como su ADC o su Mago), en lugar de repartir el daño entre el tanque y los demás."),
    ("¿Por qué mi Soporte no para de golpear a los súbditos en lugar de dejármelos?", "Si no tiene comprado el objeto inicial de soporte (que comparte el oro automáticamente), entonces tu soporte es nuevo, no conoce el rol, o simplemente tiene intenciones de arruinarte el farmeo. Háblale con cariño y explícale."),
    ("¿Si minimizo el juego para ver Wild Rift Drafting, me desconectará de la partida?", "Wild Rift a veces te desconecta si lo dejas en segundo plano mucho tiempo. Por eso esta aplicación utiliza un 'Overlay Flotante' que se dibuja ENCIMA del juego, para que uses el asistente sin tener que salir ni minimizar la app del juego."),
]

kotlin_faqs = []
for q, a in faqs:
    # Escape quotes
    q = q.replace('"', '\\"')
    a = a.replace('"', '\\"')
    kotlin_faqs.append(f'            Pair(\n                "{q}",\n                "{a}"\n            )')

pairs_str = ",\n".join(kotlin_faqs)

new_block = f"""    val faqs = remember {{
        listOf(
{pairs_str}
        )
    }}"""

with open("app/src/main/java/com/example/ui/screens/FAQScreen.kt", "r") as f:
    content = f.read()

start_index = content.find("val faqs = remember {")
end_index = content.find("    val filteredFaqs = remember(faqSearchQuery) {")

if start_index != -1 and end_index != -1:
    new_content = content[:start_index] + new_block + "\n\n" + content[end_index:]
    with open("app/src/main/java/com/example/ui/screens/FAQScreen.kt", "w") as f:
        f.write(new_content)
    print(f"Updated FAQ with {len(faqs)} questions.")
else:
    print("Could not find FAQ block.")

