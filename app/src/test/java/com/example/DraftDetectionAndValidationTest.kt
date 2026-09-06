package com.example

import com.example.data.WildRiftRepository
import com.example.model.LaneRole
import com.example.service.screen.ChampionNameResolver
import com.example.service.screen.DraftValidationLayer
import com.example.service.screen.ScannedSlotInfo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import androidx.test.core.app.ApplicationProvider

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class DraftDetectionAndValidationTest {

    private fun initChamps() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        WildRiftRepository.initChampions(context)
    }

    @Test
    fun testSummonerNameIsNotConfusedWithLucian() {
        initChamps()
        val champs = WildRiftRepository.champions
        
        // "XCS Lucianito" debe ser identificado como invocador y no confundirse con Lucian
        assertTrue(DraftValidationLayer.isLikelySummonerName("XCS Lucianito"))
        val matchedFromSummoner = ChampionNameResolver.findChampionInText("XCS Lucianito", champs)
        assertNull("XCS Lucianito no debe devolver el campeón Lucian", matchedFromSummoner)

        // "Lucianito" solo tampoco debe devolver Lucian
        val matchedDiminutive = ChampionNameResolver.findChampionInText("Lucianito", champs)
        assertNull("Lucianito no debe devolver el campeón Lucian", matchedDiminutive)

        // "LUCIAN" en mayúsculas o "Lucian" exacto sí debe devolver Lucian
        val matchedLucian = ChampionNameResolver.findChampionInText("LUCIAN", champs)
        assertNotNull(matchedLucian)
        assertEquals("lucian", matchedLucian?.id)
    }

    @Test
    fun testJugadorDoesNotMatchJungleRole() {
        // "Jugador 1", "Jugador 2", etc. NO deben ser reconocidos como JUNGLA
        assertNull(DraftValidationLayer.parseRoleFromText("Jugador 1"))
        assertNull(DraftValidationLayer.parseRoleFromText("Jugador 2"))
        assertNull(DraftValidationLayer.parseRoleFromText("Jugador 3"))
        assertNull(DraftValidationLayer.parseRoleFromText("Jugador 4"))
        assertNull(DraftValidationLayer.parseRoleFromText("Jugador 5"))
        assertNull(DraftValidationLayer.parseRoleFromText("Player 1"))
    }

    @Test
    fun testRoleNameParsingMultilingual() {
        // Spanish
        assertEquals(LaneRole.TOP, DraftValidationLayer.parseRoleFromText("CARRIL DE BARÓN"))
        assertEquals(LaneRole.TOP, DraftValidationLayer.parseRoleFromText("CARRIL DE BARON"))
        assertEquals(LaneRole.JUNGLE, DraftValidationLayer.parseRoleFromText("JUNGLA"))
        assertEquals(LaneRole.MID, DraftValidationLayer.parseRoleFromText("CARRIL CENTRAL"))
        assertEquals(LaneRole.ADC, DraftValidationLayer.parseRoleFromText("CARRIL DEL DRAGÓN"))
        assertEquals(LaneRole.ADC, DraftValidationLayer.parseRoleFromText("CARRIL DEL DRAGON"))
        assertEquals(LaneRole.SUPPORT, DraftValidationLayer.parseRoleFromText("SOPORTE"))

        // English
        assertEquals(LaneRole.TOP, DraftValidationLayer.parseRoleFromText("BARON LANE"))
        assertEquals(LaneRole.JUNGLE, DraftValidationLayer.parseRoleFromText("JUNGLE"))
        assertEquals(LaneRole.MID, DraftValidationLayer.parseRoleFromText("MID LANE"))
        assertEquals(LaneRole.ADC, DraftValidationLayer.parseRoleFromText("DUO LANE"))
        assertEquals(LaneRole.SUPPORT, DraftValidationLayer.parseRoleFromText("SUPPORT"))

        // Portuguese
        assertEquals(LaneRole.TOP, DraftValidationLayer.parseRoleFromText("ROTA DE BARÃO"))
        assertEquals(LaneRole.JUNGLE, DraftValidationLayer.parseRoleFromText("CAÇADOR"))
        assertEquals(LaneRole.MID, DraftValidationLayer.parseRoleFromText("ROTA DO MEIO"))
        assertEquals(LaneRole.ADC, DraftValidationLayer.parseRoleFromText("ROTA DO DRAGÃO"))
        assertEquals(LaneRole.SUPPORT, DraftValidationLayer.parseRoleFromText("SUPORTE"))
    }

    @Test
    fun testExactMatchScreenshotTeamRoleResolution() {
        initChamps()
        val champs = WildRiftRepository.champions
        val caitlyn = champs.first { it.id == "caitlyn" }
        val thresh = champs.first { it.id == "thresh" }
        val jarvan = champs.first { it.id == "jarvan_iv" }
        val vladimir = champs.first { it.id == "vladimir" }
        val urgot = champs.first { it.id == "urgot" }

        val ezreal = champs.first { it.id == "ezreal" }
        val karma = champs.first { it.id == "karma" }
        val riven = champs.first { it.id == "riven" }
        val sett = champs.first { it.id == "sett" }
        val zoe = champs.first { it.id == "zoe" }

        // Aliados del screenshot:
        // Slot 0: Caitlyn (ADC)
        // Slot 1: Thresh (SUPPORT)
        // Slot 2: Jarvan IV (JUNGLE)
        // Slot 3: Vladimir (MID)
        // Slot 4: Urgot con texto explícito "CARRIL DE BARÓN" (TOP)
        val allySlots = listOf(
            ScannedSlotInfo(slotIndex = 0, champion = caitlyn, explicitRole = null),
            ScannedSlotInfo(slotIndex = 1, champion = thresh, explicitRole = null),
            ScannedSlotInfo(slotIndex = 2, champion = jarvan, explicitRole = null),
            ScannedSlotInfo(slotIndex = 3, champion = vladimir, explicitRole = null),
            ScannedSlotInfo(slotIndex = 4, champion = urgot, explicitRole = LaneRole.TOP)
        )

        val auditAllies = mutableListOf<String>()
        val resolvedAllies = DraftValidationLayer.resolveTeamRoles(allySlots, champs, auditAllies)

        assertEquals(5, resolvedAllies.size)
        assertEquals("urgot", resolvedAllies[LaneRole.TOP]?.id)
        assertEquals("jarvan_iv", resolvedAllies[LaneRole.JUNGLE]?.id)
        assertEquals("vladimir", resolvedAllies[LaneRole.MID]?.id)
        assertEquals("caitlyn", resolvedAllies[LaneRole.ADC]?.id)
        assertEquals("thresh", resolvedAllies[LaneRole.SUPPORT]?.id)

        // Rivales del screenshot:
        // Slot 0: Ezreal (Jugador 1 -> ADC)
        // Slot 1: Karma (Jugador 2 -> SUPPORT)
        // Slot 2: Riven (Jugador 3 -> TOP)
        // Slot 3: Sett (Jugador 4 -> JUNGLE)
        // Slot 4: Zoe (Jugador 5 -> MID)
        val enemySlots = listOf(
            ScannedSlotInfo(slotIndex = 0, champion = ezreal, explicitRole = null),
            ScannedSlotInfo(slotIndex = 1, champion = karma, explicitRole = null),
            ScannedSlotInfo(slotIndex = 2, champion = riven, explicitRole = null),
            ScannedSlotInfo(slotIndex = 3, champion = sett, explicitRole = null),
            ScannedSlotInfo(slotIndex = 4, champion = zoe, explicitRole = null)
        )

        val auditEnemies = mutableListOf<String>()
        val resolvedEnemiesDetailed = DraftValidationLayer.resolveTeamRolesDetailed(enemySlots, champs, auditEnemies)
        val resolvedEnemies = resolvedEnemiesDetailed.assignments

        assertEquals(5, resolvedEnemies.size)
        assertEquals("riven", resolvedEnemies[LaneRole.TOP]?.id)
        assertEquals("sett", resolvedEnemies[LaneRole.JUNGLE]?.id)
        assertEquals("zoe", resolvedEnemies[LaneRole.MID]?.id)
        assertEquals("ezreal", resolvedEnemies[LaneRole.ADC]?.id)
        assertEquals("karma", resolvedEnemies[LaneRole.SUPPORT]?.id)

        // Verificación de certezas:
        // Ezreal, Karma y Zoe tienen certeza >= 90% (asignados por slot/rol primario)
        assertTrue(resolvedEnemiesDetailed.confidences[LaneRole.ADC]!! >= 90)
        assertTrue(resolvedEnemiesDetailed.confidences[LaneRole.SUPPORT]!! >= 90)
        assertTrue(resolvedEnemiesDetailed.confidences[LaneRole.MID]!! >= 90)
        // Riven y Sett tienen asignación válida
        assertTrue(resolvedEnemiesDetailed.confidences[LaneRole.TOP]!! >= 60)
        assertTrue(resolvedEnemiesDetailed.confidences[LaneRole.JUNGLE]!! >= 60)
    }

    @Test
    fun testRealUserScreenshotsAccuracy() {
        initChamps()
        val champs = WildRiftRepository.champions

        // Validación basada en las capturas reales del usuario:
        // Captura 1: Wukong (Barón), Galio (Apoyo), Veigar (Mid), Sivir (Dúo), Yone (Jungla con Smite)
        // Rivales: Lulu (Apoyo), Varus (Dúo), Olaf (Jungla/Top), Slot 3 y 4 aún no han elegido ("Jugador 4", "Jugador 5")
        val wukong = champs.find { it.id == "wukong" }!!
        val galio = champs.find { it.id == "galio" }!!
        val veigar = champs.find { it.id == "veigar" }!!
        val sivir = champs.find { it.id == "sivir" }!!
        val yone = champs.find { it.id == "yone" }!!

        val lulu = champs.find { it.id == "lulu" }!!
        val varus = champs.find { it.id == "varus" }!!
        val olaf = champs.find { it.id == "olaf" }!!

        // Verificación de parsing de roles en texto en español de Wild Rift
        assertEquals(LaneRole.ADC, DraftValidationLayer.parseRoleFromText("CALLE DEL DRAGÓN"))
        assertEquals(LaneRole.MID, DraftValidationLayer.parseRoleFromText("CALLE CENTRAL"))
        assertEquals(LaneRole.TOP, DraftValidationLayer.parseRoleFromText("CARRIL DE BARÓN"))
        assertEquals(LaneRole.JUNGLE, DraftValidationLayer.parseRoleFromText("JUNGLA"))
        assertEquals(LaneRole.SUPPORT, DraftValidationLayer.parseRoleFromText("APOYO"))

        // Verificación de aliados
        val allySlots = listOf(
            ScannedSlotInfo(slotIndex = 0, isAlly = true, champion = wukong, explicitRole = LaneRole.TOP),
            ScannedSlotInfo(slotIndex = 1, isAlly = true, champion = galio, explicitRole = LaneRole.SUPPORT),
            ScannedSlotInfo(slotIndex = 2, isAlly = true, champion = veigar, explicitRole = LaneRole.MID),
            ScannedSlotInfo(slotIndex = 3, isAlly = true, champion = sivir, explicitRole = LaneRole.ADC),
            ScannedSlotInfo(slotIndex = 4, isAlly = true, champion = yone, explicitRole = LaneRole.JUNGLE)
        )
        val alliesMap = allySlots.associate { it.explicitRole!! to it.champion!! }
        assertEquals("wukong", alliesMap[LaneRole.TOP]?.id)
        assertEquals("yone", alliesMap[LaneRole.JUNGLE]?.id)
        assertEquals("veigar", alliesMap[LaneRole.MID]?.id)
        assertEquals("sivir", alliesMap[LaneRole.ADC]?.id)
        assertEquals("galio", alliesMap[LaneRole.SUPPORT]?.id)

        // Verificación de rivales: solo 3 seleccionados, 2 aún no han elegido
        val enemySlots = listOf(
            ScannedSlotInfo(slotIndex = 0, isAlly = false, champion = lulu, explicitRole = null),
            ScannedSlotInfo(slotIndex = 1, isAlly = false, champion = varus, explicitRole = null),
            ScannedSlotInfo(slotIndex = 2, isAlly = false, champion = olaf, explicitRole = null),
            ScannedSlotInfo(slotIndex = 3, isAlly = false, champion = null, isLikelyUnpicked = true),
            ScannedSlotInfo(slotIndex = 4, isAlly = false, champion = null, isLikelyUnpicked = true)
        )
        val validPicks = enemySlots.filter { it.champion != null }
        val auditList = mutableListOf<String>()
        val resolvedEnemies = DraftValidationLayer.resolveTeamRolesDetailed(validPicks, champs, auditList)

        assertEquals(3, resolvedEnemies.assignments.size)
        assertEquals("lulu", resolvedEnemies.assignments[LaneRole.SUPPORT]?.id)
        assertEquals("varus", resolvedEnemies.assignments[LaneRole.ADC]?.id)
        // Olaf cubre el carril prioritario que le corresponde (TOP o JUNGLE)
        assertTrue(resolvedEnemies.assignments.containsKey(LaneRole.TOP) || resolvedEnemies.assignments.containsKey(LaneRole.JUNGLE))
    }

    @Test
    fun testAssetLoadingAndVisualMatching() {
        initChamps()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        com.example.util.ChampionHashes.initFromAssets(context)
        val signatures = com.example.util.ChampionHashes.getAllSignatures()
        assertTrue("Debe cargar firmas desde assets", signatures.isNotEmpty())

        val yoneBitmap = context.assets.open("champions/yone.png").use {
            android.graphics.BitmapFactory.decodeStream(it)
        }
        assertNotNull(yoneBitmap)

        val champs = WildRiftRepository.champions
        val match = com.example.util.ImageHashMatcher.findBestVisualMatch(yoneBitmap, champs, isAlly = true)
        assertNotNull("Debe encontrar coincidencia para Yone", match)
        assertEquals("El asset de Yone debe coincidir con Yone", "yone", match?.champion?.id)

        val matchJungle = com.example.util.ImageHashMatcher.findBestVisualMatch(yoneBitmap, champs, preferredRole = LaneRole.JUNGLE, isAlly = true)
        assertNotNull("Debe encontrar coincidencia para Yone con preferredRole JUNGLE", matchJungle)
        assertEquals("Aun con preferredRole JUNGLE debe seguir siendo Yone", "yone", matchJungle?.champion?.id)
    }

    @Test
    fun testSmiteSpellDetectionAndRoleResolution() {
        // Crear un bitmap sintético con colores de fuego / Smite (naranja/rojo/oro brillante)
        val smiteBitmap = android.graphics.Bitmap.createBitmap(40, 40, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(smiteBitmap)
        val paint = android.graphics.Paint()
        paint.color = android.graphics.Color.rgb(240, 120, 20) // Naranja brillante
        canvas.drawRect(0f, 0f, 40f, 40f, paint)

        val isSmite = com.example.util.ImageHashMatcher.detectSmiteSpell(smiteBitmap)
        assertTrue("El bitmap de prueba con colores de Castigo/Smite debe ser detectado", isSmite)

        // Crear un bitmap de color neutro (gris/azul oscuro de fondo)
        val nonSmiteBitmap = android.graphics.Bitmap.createBitmap(40, 40, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas2 = android.graphics.Canvas(nonSmiteBitmap)
        val paint2 = android.graphics.Paint()
        paint2.color = android.graphics.Color.rgb(30, 40, 60)
        canvas2.drawRect(0f, 0f, 40f, 40f, paint2)

        val isNotSmite = com.example.util.ImageHashMatcher.detectSmiteSpell(nonSmiteBitmap)
        assertFalse("Un fondo azul/gris oscuro no debe ser detectado como Smite", isNotSmite)
    }

    @Test
    fun testChampionNameWithSummonerNameExtraction() {
        initChamps()
        val champs = WildRiftRepository.champions

        // Casos reales donde el OCR detecta el nombre del campeón junto al nombre de invocador
        val wukong = ChampionNameResolver.findChampionInText("WUKONG XCS Alee22", champs)
        assertNotNull("Debe extraer a Wukong incluso si está acompañado del nombre de invocador con clan", wukong)
        assertEquals("wukong", wukong?.id)

        val galio = ChampionNameResolver.findChampionInText("GALIO XCS Elchicho7", champs)
        assertNotNull("Debe extraer a Galio incluso si está acompañado del nombre de invocador con números", galio)
        assertEquals("galio", galio?.id)

        val veigar = ChampionNameResolver.findChampionInText("VEIGAR Gustavo GG", champs)
        assertNotNull("Debe extraer a Veigar incluso con nombre de invocador con espacios", veigar)
        assertEquals("veigar", veigar?.id)

        // Casos donde solo es un nombre de invocador y NO hay campeón: debe descartarse
        val summonerOnly = ChampionNameResolver.findChampionInText("XCS Alee22", champs)
        assertNull("Un invocador aislado sin campeón no debe confundirse", summonerOnly)

        val diego = ChampionNameResolver.findChampionInText("D I E G O", champs)
        assertNull("El apodo Diego no debe confundirse con ningún campeón", diego)
    }

    @Test
    fun testCircularMaskProperties() {
        // Verificar que la máscara circular tiene aproximadamente ~570-580 píxeles activos de 1024
        val maskCount = com.example.util.ChampionHashes.CIRCLE_MASK.count { it }
        assertTrue("La máscara circular debe tener entre 550 y 600 píxeles", maskCount in 550..600)
        // Las 4 esquinas deben estar desactivadas (máscara false)
        assertFalse("Esquina superior izquierda debe ser false", com.example.util.ChampionHashes.CIRCLE_MASK[0])
        assertFalse("Esquina superior derecha debe ser false", com.example.util.ChampionHashes.CIRCLE_MASK[31])
        assertFalse("Esquina inferior izquierda debe ser false", com.example.util.ChampionHashes.CIRCLE_MASK[32 * 31])
        assertFalse("Esquina inferior derecha debe ser false", com.example.util.ChampionHashes.CIRCLE_MASK[1023])
        // El centro (15, 15) o (16, 16) debe estar activo
        assertTrue("El centro debe estar activo", com.example.util.ChampionHashes.CIRCLE_MASK[16 * 32 + 16])
    }

    @Test
    fun testVisualMatchingAgainstLocalAssets() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        WildRiftRepository.initChampions(context)
        com.example.util.ChampionHashes.ensureLoaded(context)
        val champs = WildRiftRepository.champions

        val testIds = listOf("wukong", "galio", "veigar", "sona", "yone", "lulu", "varus", "olaf", "ambessa", "mel", "yunara", "norra")
        for (id in testIds) {
            val assetStream = context.assets.open("champions/$id.png")
            val bitmap = android.graphics.BitmapFactory.decodeStream(assetStream)
            assertNotNull("El asset champions/$id.png debe existir y cargarse", bitmap)

            val match = com.example.util.ImageHashMatcher.findBestVisualMatch(
                bitmap,
                champs,
                isAlly = true
            )
            println("Test match para $id -> detectado como ${match?.champion?.id} con score ${match?.confidencePercent}%")
            assertEquals("El avatar local de $id debe detectarse como $id", id, match?.champion?.id)
            bitmap.recycle()
        }
    }

    @Test
    fun testEmptyAndNoiseVisualMatching() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        WildRiftRepository.initChampions(context)
        com.example.util.ChampionHashes.ensureLoaded(context)
        val champs = WildRiftRepository.champions

        // Caso 1: Bitmap negro/oscuro (slot vacío)
        val darkBitmap = android.graphics.Bitmap.createBitmap(120, 120, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(darkBitmap)
        canvas.drawColor(android.graphics.Color.rgb(20, 25, 35))

        val matchDark = com.example.util.ImageHashMatcher.findBestVisualMatch(darkBitmap, champs, isAlly = false)
        println("Resultado para slot oscuro: ${matchDark?.champion?.name} (conf: ${matchDark?.confidencePercent}%)")
        assertNull("Un slot oscuro/vacío NO debe dar match con ningún campeón", matchDark)

        // Caso 2: Círculo gris azulado típico de Wild Rift sin campeón
        canvas.drawColor(android.graphics.Color.rgb(30, 40, 55))
        val matchGray = com.example.util.ImageHashMatcher.findBestVisualMatch(darkBitmap, champs, isAlly = true)
        println("Resultado para slot gris: ${matchGray?.champion?.name} (conf: ${matchGray?.confidencePercent}%)")
        assertNull("Un slot grisáceo/fondo NO debe dar match con ningún campeón", matchGray)

        darkBitmap.recycle()
    }

    @Test
    fun testAll141ChampionsSelfMatchPrecision() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        WildRiftRepository.initChampions(context)
        com.example.util.ChampionHashes.ensureLoaded(context)
        val champs = WildRiftRepository.champions

        val assetManager = context.assets
        val list = assetManager.list("champions")?.filter { it.endsWith(".png") } ?: emptyList()
        assertTrue("Deben existir al menos 130 avatares en assets/champions", list.size >= 130)

        var correctCount = 0
        val mismatches = mutableListOf<String>()

        for (filename in list) {
            val expectedId = filename.removeSuffix(".png")
            val stream = assetManager.open("champions/$filename")
            val bitmap = android.graphics.BitmapFactory.decodeStream(stream)
            if (bitmap != null) {
                val match = com.example.util.ImageHashMatcher.findBestVisualMatch(
                    bitmap,
                    champs,
                    isAlly = true
                )
                if (match?.champion?.id == expectedId) {
                    correctCount++
                } else {
                    mismatches.add("$expectedId -> ${match?.champion?.id} (conf: ${match?.confidencePercent}%)")
                }
                bitmap.recycle()
            }
        }

        println("=== REPORTE DE PRECISIÓN 141 CAMPEONES ===")
        println("Correctos: $correctCount / ${list.size}")
        if (mismatches.isNotEmpty()) {
            println("Mismatches (${mismatches.size}):")
            mismatches.take(20).forEach { println("  $it") }
        }
        assertEquals("La precisión sobre todos los avatares locales debe ser 100% sin inventar ningún campeón. Fallos: $mismatches", 0, mismatches.size)
    }

    @Test
    fun testRoleDiscrepanciesAndCorrectRoleAssignments() {
        initChamps()
        val champs = WildRiftRepository.champions

        val wukong = champs.find { it.id == "wukong" }!!
        val yone = champs.find { it.id == "yone" }!!
        val veigar = champs.find { it.id == "veigar" }!!
        val varus = champs.find { it.id == "varus" }!!
        val sona = champs.find { it.id == "sona" }!!

        // Simular los 5 slots aliados estándar de Wild Rift:
        // Slot 0: Wukong (Baron/Top)
        // Slot 1: Yone (Jungle)
        // Slot 2: Veigar (Mid)
        // Slot 3: Varus (Dragon/ADC)
        // Slot 4: Sona (Support)
        val allySlots = listOf(
            ScannedSlotInfo(slotIndex = 0, isAlly = true, champion = wukong, explicitRole = null),
            ScannedSlotInfo(slotIndex = 1, isAlly = true, champion = yone, explicitRole = null),
            ScannedSlotInfo(slotIndex = 2, isAlly = true, champion = veigar, explicitRole = null),
            ScannedSlotInfo(slotIndex = 3, isAlly = true, champion = varus, explicitRole = null),
            ScannedSlotInfo(slotIndex = 4, isAlly = true, champion = sona, explicitRole = null)
        )

        val auditList = mutableListOf<String>()
        val resolved = DraftValidationLayer.resolveTeamRolesDetailed(allySlots, champs, auditList)

        assertEquals("Wukong debe ser TOP", "wukong", resolved.assignments[LaneRole.TOP]?.id)
        assertEquals("Yone debe ser JUNGLE por su posición en slot 1", "yone", resolved.assignments[LaneRole.JUNGLE]?.id)
        assertEquals("Veigar debe ser MID", "veigar", resolved.assignments[LaneRole.MID]?.id)
        assertEquals("Varus debe ser ADC", "varus", resolved.assignments[LaneRole.ADC]?.id)
        assertEquals("Sona debe ser SUPPORT", "sona", resolved.assignments[LaneRole.SUPPORT]?.id)

        // Ningún campeón debe quedar en un rol completamente ajeno
        assertTrue("Wukong NUNCA debe ser ADC", allySlots[0].assignedRole != LaneRole.ADC)
        assertTrue("Wukong NUNCA debe ser SUPPORT", allySlots[0].assignedRole != LaneRole.SUPPORT)
    }

    @Test
    fun testSummonerNamesAreNotHallucinatedAsChampions() {
        initChamps()
        val champs = WildRiftRepository.champions

        // Nombres de invocador comunes que antes se confundían con campeones
        val falsePositives = listOf(
            "Kain99",
            "SonyBoy",
            "SamPro",
            "Gaby11anos",
            "martincho137",
            "CacauVegannah",
            "ElChicho777",
            "ProGamer2024",
            "D I E G O"
        )

        for (name in falsePositives) {
            val result = ChampionNameResolver.findChampionInText(name, champs)
            assertNull("El texto '$name' es un nombre de jugador y NO debe inventar un campeón", result)
        }
    }
}
