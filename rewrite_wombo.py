import sys

content = open('app/src/main/java/com/example/ui/components/DraftWomboSynergyCard.kt', 'r').read()

start_marker = "        // 1. Yasuo + Airbone Knock-ups"
end_marker = "        return detected.distinctBy { \"${it.champ1.name}_${it.champ2.name}\" }"

new_wombos = """
        // 1. Yasuo + Airbone Knock-ups
        val yasuo = find("yasuo")
        val knockupper = find("malphite", "diana", "alistar", "yone", "wukong", "rakan", "gragas", "vi", "nautilus", "lee sin", "xin zhao", "nunu")
        if (yasuo != null && knockupper != null && yasuo.id != knockupper.id) {
            detected.add(WomboCombo(
                title = "💥 Wombo-Combo Aéreo Imparable", type = "KNOCKUP", champ1 = knockupper, champ2 = yasuo,
                description = "Iniciación de ${knockupper.name} con Derribo Aéreo masivo activa instantáneamente la Definitiva de Yasuo.",
                executionTip = "Esperar a que ${knockupper.name} impacte a 2 o más enemigos antes de activar la Definitiva de Yasuo."
            ))
        }

        // 2. Orianna + Ball Delivery / Heavy Engage
        val orianna = find("orianna")
        val ballCarrier = find("malphite", "jarvan", "rakan", "diana", "amumu", "sett", "vi", "hecarim", "alistar", "rengar", "wukong", "camille")
        if (orianna != null && ballCarrier != null && orianna.id != ballCarrier.id) {
            detected.add(WomboCombo(
                title = "🌀 Balón de Choque & Erradicación", type = "SHOCKWAVE", champ1 = ballCarrier, champ2 = orianna,
                description = "El balón de Orianna colocado sobre ${ballCarrier.name} permite una Definitiva (Onda de Choque) perfecta tras el salto.",
                executionTip = "Colocar H3 (Proteger) sobre ${ballCarrier.name} justo antes del dive y presionar la Definitiva en el punto de impacto."
            ))
        }

        // 3. Lulu/Yuumi/Janna/Milio + Hypercarry Protection
        val enchanter = find("lulu", "yuumi", "milio", "janna", "karma")
        val hypercarry = find("vayne", "jinx", "kog'maw", "twitch", "tristana", "zeri", "samira", "kaisa", "kai'sa")
        if (enchanter != null && hypercarry != null && enchanter.id != hypercarry.id) {
            detected.add(WomboCombo(
                title = "🛡️ Hypercarry Blindado & Velocidad Letal", type = "HYPERCARRY", champ1 = enchanter, champ2 = hypercarry,
                description = "Buffs de velocidad, escudos y utilidad de ${enchanter.name} transforman a ${hypercarry.name} en una máquina imparable.",
                executionTip = "Guardar la definitiva de soporte para contrarrestar el dive enemigo sobre el tirador en teamfights."
            ))
        }

        // 4. Aggressive ADC + Hard CC Chain All-In
        val aggressiveAdc = find("samira", "lucian", "draven", "kalista", "nilah", "tristana", "kaisa", "xayah")
        val hardCcSupport = find("nautilus", "leona", "thresh", "pyke", "blitzcrank", "braum", "alistar", "rakan")
        if (aggressiveAdc != null && hardCcSupport != null && aggressiveAdc.id != hardCcSupport.id) {
            detected.add(WomboCombo(
                title = "⛓️ Cadena de CC & All-In Explosivo", type = "CC_BURST", champ1 = hardCcSupport, champ2 = aggressiveAdc,
                description = "El control de masas encadenado de ${hardCcSupport.name} garantiza el burst completo de ${aggressiveAdc.name}.",
                executionTip = "En nivel 2 y nivel 5 buscar all-in inmediato tras el primer gancho o stun acertado."
            ))
        }

        // 5. AoE DPS + AoE Lockdown
        val aoeDps = find("miss fortune", "kennen", "gangplank", "katarina", "fiddlesticks", "brand", "ziggs", "yone", "samira")
        val aoeLockdown = find("amumu", "sona", "seraphine", "galio", "jarvan", "malphite", "nunu")
        if (aoeDps != null && aoeLockdown != null && aoeDps.id != aoeLockdown.id) {
            detected.add(WomboCombo(
                title = "🔥 Tormenta en Área & Encierro Masivo", type = "AREA_CURSE", champ1 = aoeLockdown, champ2 = aoeDps,
                description = "El aturdimiento masivo de ${aoeLockdown.name} congela al equipo rival dentro de la definitiva destructiva de ${aoeDps.name}.",
                executionTip = "Luchar exclusivamente en cuellos de botella de la jungla o alrededor de los fosos de Dragón/Barón."
            ))
        }

        // 6. Lucian + Nami / Electrocutar
        val lucian = find("lucian")
        val nami = find("nami")
        if (lucian != null && nami != null) {
            detected.add(WomboCombo(
                title = "🌊 Electro-Ráfaga Acuática", type = "ENCHANTER", champ1 = nami, champ2 = lucian,
                description = "La Habilidad 3 de Nami se activa con los dobles disparos pasivos de Lucian aplicando gran burst al instante.",
                executionTip = "Nami aplica H3 sobre Lucian justo cuando este usa su desplazamiento hacia adelante."
            ))
        }

        // 7. Xayah + Rakan
        val xayah = find("xayah")
        val rakan = find("rakan")
        if (xayah != null && rakan != null) {
            detected.add(WomboCombo(
                title = "💖 Pareja Letal: Baile de Plumas", type = "DUO_SYNERGY", champ1 = rakan, champ2 = xayah,
                description = "Interacciones únicas: Rakan obtiene mayor rango de salto hacia Xayah y comparten el regreso a base.",
                executionTip = "Rakan puede iniciar desde mucho más lejos si Xayah se posiciona adelante. Encadenar CC con las plumas."
            ))
        }

        // 8. Jarvan IV + Galio
        val jarvan = find("jarvan")
        val galio = find("galio")
        if (jarvan != null && galio != null) {
            detected.add(WomboCombo(
                title = "⚔️ Entrada Heroica & Cataclismo", type = "AREA_CURSE", champ1 = jarvan, champ2 = galio,
                description = "Jarvan encierra a los enemigos en su arena, creando el objetivo perfecto para la definitiva global de Galio.",
                executionTip = "Jarvan inicia con su combo E+Q+R, Galio tira la R inmediatamente sobre Jarvan para encadenar CC."
            ))
        }

        // 9. Ashe + Braum
        val ashe = find("ashe")
        val braum = find("braum")
        if (ashe != null && braum != null) {
            detected.add(WomboCombo(
                title = "❄️ Freljord: Hielo Eterno", type = "CC_BURST", champ1 = braum, champ2 = ashe,
                description = "La pasiva de Braum se carga instantáneamente con la Q de Ashe, garantizando stuns constantes.",
                executionTip = "Braum aplica la marca, Ashe usa H1 (Concentración Máxima) para aturdir al instante."
            ))
        }

        // 10. Caitlyn + Morgana
        val caitlyn = find("caitlyn")
        val morgana = find("morgana")
        if (caitlyn != null && morgana != null) {
            detected.add(WomboCombo(
                title = "🎯 Trampas Encadenadas (Snare City)", type = "CC_BURST", champ1 = morgana, champ2 = caitlyn,
                description = "Si Morgana acierta una Q, Caitlyn pone una trampa debajo, garantizando un headshot crítico y más CC.",
                executionTip = "Morgana lanza Hechizo Oscuro, Caitlyn coloca Trampa para Yordles bajo el enemigo inmovilizado."
            ))
        }
        
        // 11. Twitch/Evelynn + Yuumi
        val invisible = find("twitch", "evelynn", "rengar", "kha'zix", "khazix")
        val yuumi = find("yuumi")
        if (invisible != null && yuumi != null) {
            detected.add(WomboCombo(
                title = "👻 Submarino Invisible", type = "DIVE", champ1 = yuumi, champ2 = invisible,
                description = "Yuumi se vuelve invisible junto al portador, permitiendo usar su definitiva desde el sigilo.",
                executionTip = "El asesino flanquea invisible, Yuumi activa Últimas Páginas para inmovilizar sin ser vistos previamente."
            ))
        }

        // 12. Hecarim/Rammus + Yuumi/Karma
        val speedster = find("hecarim", "rammus", "lillia", "singed", "nunu")
        val speedBuffer = find("yuumi", "karma", "lulu", "zilean", "sivir")
        if (speedster != null && speedBuffer != null && speedster.id != speedBuffer.id && invisible?.id != speedster.id) {
            detected.add(WomboCombo(
                title = "🏎️ Velocidad Absurda & Atropello", type = "DIVE", champ1 = speedBuffer, champ2 = speedster,
                description = "${speedBuffer.name} potencia la velocidad de movimiento de ${speedster.name}, amplificando su daño pasivo e iniciación.",
                executionTip = "Buffear velocidad justo antes del impacto para maximizar el daño del golpe inicial."
            ))
        }

        // 13. Pantheon/Shen + Twisted Fate/Taliyah/Galio
        val global1 = find("pantheon", "shen", "nocturne")
        val global2 = find("twisted fate", "taliyah", "galio")
        if (global1 != null && global2 != null) {
            detected.add(WomboCombo(
                title = "🌍 Presencia Global: Gank 4v2", type = "GANK", champ1 = global1, champ2 = global2,
                description = "Ambos campeones pueden teletransportarse a la misma línea instantáneamente, ganando cualquier pelea por superioridad numérica.",
                executionTip = "Coordinar definitivas hacia la línea de dragón para asegurar muertes y el objetivo neutral."
            ))
        }

        // 14. Camille + Galio/Shen
        val camille = find("camille")
        val protector = find("galio", "shen")
        if (camille != null && protector != null) {
            detected.add(WomboCombo(
                title = "⛓️ Aislamiento Hextech & Entrada", type = "DIVE", champ1 = protector, champ2 = camille,
                description = "Camille aísla al carry enemigo con su Ultimátum Hextech mientras ${protector.name} cae sobre ella para protegerla y añadir CC.",
                executionTip = "Camille ultea al ADC enemigo, ${protector.name} lanza su definitiva sobre Camille de inmediato."
            ))
        }

        // 15. Nunu & Willump + Katarina/Morgana/Kennen
        val nunu = find("nunu")
        val aoeUlt = find("katarina", "morgana", "kennen", "neeko")
        if (nunu != null && aoeUlt != null) {
            detected.add(WomboCombo(
                title = "❄️ Zero Absoluto & Destrucción", type = "AREA_CURSE", champ1 = nunu, champ2 = aoeUlt,
                description = "Nunu ralentiza masivamente y agrupa enemigos, permitiendo a ${aoeUlt.name} conectar todo el daño de su definitiva.",
                executionTip = "Nunu canaliza Cero Absoluto desde un seto, ${aoeUlt.name} entra cuando los enemigos intentan escapar ralentizados."
            ))
        }

        // 16. Yone + Lillia
        val yone = find("yone")
        val lillia = find("lillia")
        if (yone != null && lillia != null) {
            detected.add(WomboCombo(
                title = "💤 Sueño Inevitable & Destino Sellado", type = "AREA_CURSE", champ1 = lillia, champ2 = yone,
                description = "Lillia duerme a múltiples enemigos con Arrullo, dejando el escenario perfecto para el Destino Sellado de Yone.",
                executionTip = "Lillia duerme a los objetivos, Yone carga su definitiva para golpear a todos al mismo tiempo."
            ))
        }

        // 17. Senna + Lucian
        val senna = find("senna")
        if (senna != null && lucian != null) {
            detected.add(WomboCombo(
                title = "🔫 Venganza y Redención", type = "DUO_SYNERGY", champ1 = senna, champ2 = lucian,
                description = "El poke constante de Senna y el burst de Lucian dominan la línea. Escalas infinitas y daño mixto letal.",
                executionTip = "Senna enraíza con el Abrazo Final, Lucian descarga El Sacrificio completo sobre el objetivo."
            ))
        }

        // 18. Jinx + Thresh
        val jinx = find("jinx")
        val thresh = find("thresh")
        if (jinx != null && thresh != null) {
            detected.add(WomboCombo(
                title = "⛓️ Gancho, Linterna y Masacre", type = "CC_BURST", champ1 = thresh, champ2 = jinx,
                description = "Thresh proporciona la movilidad y protección que Jinx necesita, mientras encadenan CC de trampas sobre el gancho.",
                executionTip = "Thresh acierta Sentencia de Muerte, Jinx lanza Mascafuegos (trampas) justo en los pies del enemigo atrapado."
            ))
        }

        // 19. Ezreal + Karma/Seraphine
        val ezreal = find("ezreal")
        val pokeSupport = find("karma", "seraphine", "lux", "brand", "zyra")
        if (ezreal != null && pokeSupport != null) {
            detected.add(WomboCombo(
                title = "✨ Asedio y Pokeo Infinito", type = "POKE", champ1 = pokeSupport, champ2 = ezreal,
                description = "Combinación de altísimo rango que no deja farmear al enemigo. Te desgastan hasta obligarte a volver a base.",
                executionTip = "Empujar la oleada rápidamente e impactar habilidades bajo la torre enemiga, manteniendo las distancias."
            ))
        }

        // 20. Tristana + Alistar/Leona
        val tristana = find("tristana")
        val diveEngage = find("alistar", "leona", "nautilus", "rell")
        if (tristana != null && diveEngage != null) {
            detected.add(WomboCombo(
                title = "💣 Torre de Derribo Instantáneo", type = "CC_BURST", champ1 = diveEngage, champ2 = tristana,
                description = "La iniciación dura de ${diveEngage.name} permite a Tristana cargar toda la bomba explosiva y saltar para el reset.",
                executionTip = "El soporte inicia, Tristana lanza E, ataca 4 veces y usa W sobre el enemigo para rematar."
            ))
        }
        
"""

new_content = content[:content.find(start_marker)] + new_wombos + content[content.find(end_marker):]

open('app/src/main/java/com/example/ui/components/DraftWomboSynergyCard.kt', 'w').write(new_content)
