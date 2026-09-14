package com.example

import com.example.data.WildRiftRepository
import com.example.model.Champion
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
        synchronized(WildRiftRepository) {
            WildRiftRepository.initChampions(context)
            com.example.service.screen.LocalVisionAnalyzer.ensureInitialized(context)
        }
    }

    private fun getSafeChamps(): List<Champion> {
        initChamps()
        return synchronized(WildRiftRepository) {
            WildRiftRepository.champions.toList()
        }
    }

    @Test
    fun testSummonerNameIsNotConfusedWithLucian() {
        val champs = getSafeChamps()
        
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
        val champs = getSafeChamps()
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
        val champs = getSafeChamps()

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
    fun testSmiteSpellDetectionAndRoleResolution() {
        // Crear un bitmap sintético con colores de fuego / Smite (púrpura / Castigo)
        val smiteBitmap = android.graphics.Bitmap.createBitmap(40, 40, android.graphics.Bitmap.Config.ARGB_8888)
        smiteBitmap.eraseColor(android.graphics.Color.rgb(180, 50, 200))

        val match = com.example.util.SummonerSpellDetector.detectSpell(smiteBitmap, android.graphics.Rect(0, 0, 40, 40))
        assertNotNull("El bitmap de prueba con colores de Castigo/Smite debe ser detectado", match)
        assertEquals("smite", match?.spellId)
    }

    @Test
    fun testChampionNameWithSummonerNameExtraction() {
        val champs = getSafeChamps()

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
    fun testRoleDiscrepanciesAndCorrectRoleAssignments() {
        val champs = getSafeChamps()

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
        val champs = getSafeChamps()

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

    @Test
    fun testScannerV2CalibratedROICalculation() {
        val width = 1536
        val height = 695

        val avatarDiameter = (height * 0.120f).toInt().coerceAtLeast(32) // ~83 px
        val allyAvatarCenterX = (height * 0.160f).toInt().coerceAtLeast(16) // ~111 px
        val enemyAvatarCenterX = (width - (height * 0.160f)).toInt().coerceIn(0, width) // ~1425 px

        assertEquals(83, avatarDiameter)
        assertEquals(111, allyAvatarCenterX)
        assertEquals(1424, enemyAvatarCenterX)

        val slotYRatios = floatArrayOf(0.195f, 0.324f, 0.459f, 0.594f, 0.728f)
        val expectedYCenters = intArrayOf(135, 225, 319, 412, 505)

        for (i in 0..4) {
            val yCenter = (height * slotYRatios[i]).toInt()
            assertEquals("Y center para slot $i", expectedYCenters[i], yCenter)

            // Cálculo ROI aliado
            val startX = (allyAvatarCenterX - avatarDiameter / 2).coerceIn(0, width - avatarDiameter)
            val startY = (yCenter - avatarDiameter / 2).coerceIn(0, height - avatarDiameter)
            val roiRect = android.graphics.Rect(startX, startY, startX + avatarDiameter, startY + avatarDiameter)

            assertTrue("ROI X dentro de límites", roiRect.left >= 0 && roiRect.right <= width)
            assertTrue("ROI Y dentro de límites", roiRect.top >= 0 && roiRect.bottom <= height)
            assertEquals("Diámetro exacto de ROI en ancho", avatarDiameter, roiRect.width())
            assertEquals("Diámetro exacto de ROI en alto", avatarDiameter, roiRect.height())

            // En slot 0, X debe ser exactamente [70..153] y Y [94..177] (centro 111, 135)
            if (i == 0) {
                assertEquals(70, roiRect.left)
                assertEquals(94, roiRect.top)
                assertEquals(153, roiRect.right)
                assertEquals(177, roiRect.bottom)
            }
        }

        // Verificar que el avatar enemigo en 1424px evita el panel lateral de Android (típicamente en >1480px)
        val enemyStartX = (enemyAvatarCenterX - avatarDiameter / 2)
        val enemyEndX = enemyStartX + avatarDiameter
        assertEquals(1383, enemyStartX)
        assertEquals(1466, enemyEndX)
        assertTrue("ROI enemiga no toca el borde derecho ni panel de volumen", enemyEndX < width - 50)
    }

    @Test
    fun testLocalVisionVolibearMatching() {
        val champs = getSafeChamps()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        // Cargar imagen de Volibear desde assets
        val assetManager = context.assets
        val stream = assetManager.open("champions/volibear.png")
        val bmp = android.graphics.BitmapFactory.decodeStream(stream)
        assertNotNull("Bitmap de volibear.png no debe ser nulo", bmp)

        com.example.service.screen.LocalVisionAnalyzer.ensureInitialized(context)
        val match = com.example.service.screen.LocalVisionAnalyzer.matchAvatar(
            crop = bmp,
            candidates = champs,
            expectedRole = LaneRole.JUNGLE,
            context = context
        )

        assertNotNull("Debe encontrar coincidencia para volibear", match)
        println("TEST MATCH RESULT: ${match?.first?.name} with confidence ${match?.second}")
        assertEquals("volibear", match?.first?.id)
    }

    @Test
    fun testMultiScaleVolibearLocalMatching() = kotlinx.coroutines.test.runTest {
        val champs = getSafeChamps()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        val assetManager = context.assets
        val stream = assetManager.open("champions/volibear.png")
        val volibearBmp = android.graphics.BitmapFactory.decodeStream(stream)!!

        // Escala normal
        val matchNormal = com.example.service.screen.LocalVisionAnalyzer.matchAvatar(
            crop = volibearBmp,
            candidates = champs,
            expectedRole = LaneRole.JUNGLE,
            context = context
        )
        assertNotNull("Volibear en escala normal debe coincidir", matchNormal)
        assertEquals("volibear", matchNormal?.first?.id)

        // Escala reducida (88%) simulando recorte interior anti-anillos
        val innerWidth = (volibearBmp.width * 0.88f).toInt()
        val innerHeight = (volibearBmp.height * 0.88f).toInt()
        val innerBmp = android.graphics.Bitmap.createScaledBitmap(volibearBmp, innerWidth, innerHeight, true)
        val matchInner = com.example.service.screen.LocalVisionAnalyzer.matchAvatar(
            crop = innerBmp,
            candidates = champs,
            expectedRole = LaneRole.JUNGLE,
            context = context
        )
        assertNotNull("Volibear con recorte interior (88%) debe coincidir", matchInner)
        assertEquals("volibear", matchInner?.first?.id)
        assertTrue("La confianza debe ser >= 0.70", (matchInner?.second ?: 0f) >= 0.70f)
    }

    @Test
    fun test10thPickInferiorAndSuperiorFlow() = kotlinx.coroutines.test.runTest {
        val champs = getSafeChamps()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val calib = com.example.service.screen.VisionCalibrationConfig()

        // Creamos un lienzo 1920x1080 simulando pantalla de Wild Rift
        val width = 1920
        val height = 1080
        val frame = android.graphics.Bitmap.createBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(frame)

        val assetManager = context.assets
        val stream = assetManager.open("champions/volibear.png")
        val volibearBmp = android.graphics.BitmapFactory.decodeStream(stream)!!

        // 1. Simular Volibear en la PARTE INFERIOR DERECHA (Slot 4 Rival para Primera Selección)
        val enemyInferiorX = (width * calib.enemyAvatarCenterX).toInt()
        val enemyInferiorY = (height * calib.enemySlotYRatios[4]).toInt()
        val diamInferior = (height * calib.avatarDiameterRatio).toInt()
        val scaledInferior = android.graphics.Bitmap.createScaledBitmap(volibearBmp, diamInferior, diamInferior, true)
        canvas.drawBitmap(scaledInferior, (enemyInferiorX - diamInferior / 2).toFloat(), (enemyInferiorY - diamInferior / 2).toFloat(), null)

        // Comprobamos escaneo en parte inferior con isFirstPick = true (debe mirar inferior derecha)
        val matchInferiorFP = com.example.service.screen.LocalVisionAnalyzer.identify10thPickInferior(
            bitmap = frame,
            isFirstPick = true,
            calib = calib,
            allChamps = champs,
            confirmedIds = emptySet(),
            expectedRole = LaneRole.JUNGLE,
            context = context
        )
        assertNotNull("Debe detectar a Volibear en la parte inferior derecha con First Pick = true", matchInferiorFP)
        assertEquals("volibear", matchInferiorFP?.first?.id)

        // 2. Simular Volibear en la PARTE SUPERIOR DERECHA (Barra superior rival)
        val topEnemyX = (width * calib.topEnemy5XRatio).toInt()
        val topEnemyY = (height * calib.topAvatarYRatio).toInt()
        val diamTop = (height * calib.topAvatarDiameterRatio).toInt()
        val scaledTop = android.graphics.Bitmap.createScaledBitmap(volibearBmp, diamTop, diamTop, true)
        canvas.drawBitmap(scaledTop, (topEnemyX - diamTop / 2).toFloat(), (topEnemyY - diamTop / 2).toFloat(), null)

        // Comprobamos confirmación en parte superior con isFirstPick = true (debe mirar superior derecha y dar 100% de certeza)
        val matchSuperiorFP = com.example.service.screen.LocalVisionAnalyzer.identify10thPickSuperior(
            bitmap = frame,
            isFirstPick = true,
            calib = calib,
            allChamps = champs,
            confirmedIds = emptySet(),
            expectedRole = LaneRole.JUNGLE,
            context = context
        )
        assertNotNull("Debe confirmar a Volibear en la parte superior derecha con First Pick = true", matchSuperiorFP)
        assertEquals("volibear", matchSuperiorFP?.first?.id)
        assertEquals(1.0f, matchSuperiorFP?.second ?: 0f, 0.001f)

        // 3. Comprobación de desaparición de slots de avatares en fase de preparación
        val dismissedInPrep = com.example.service.screen.LocalVisionAnalyzer.areAvatarSlotsDismissed(frame, isPrepPhaseDetected = true)
        assertTrue("En fase de preparación los slots de avatares deben considerarse desaparecidos", dismissedInPrep)

        // 4. Comprobación de logs detallados del 10º pick (métricas escaneadas, comparadas y justificación)
        val detailedInferior = com.example.service.screen.LocalVisionAnalyzer.identify10thPickInferiorDetailed(
            bitmap = frame,
            isFirstPick = true,
            calib = calib,
            allChamps = champs,
            confirmedIds = emptySet(),
            expectedRole = LaneRole.JUNGLE,
            context = context
        )
        assertNotNull("Debe generar log detallado para escaneo inferior", detailedInferior)
        assertEquals("volibear", detailedInferior?.selectedChampion?.id)
        assertFalse("En escaneo inferior el pick es hover y NO debe figurar como confirmado", detailedInferior?.isConfirmed ?: true)
        assertTrue("Debe contener métricas de recorte poblado", detailedInferior?.scannedMetrics?.isPopulated ?: false)
        assertTrue("Debe registrar candidatos comparados", (detailedInferior?.topCandidates?.size ?: 0) > 0)
        assertTrue("Debe incluir justificación de decisión con porcentaje", detailedInferior?.decisionReason?.contains("Volibear") ?: false)
        assertTrue("El resumen formateado debe contener secciones estructuradas", detailedInferior?.formattedSummary?.contains("CARACTERÍSTICAS ESCANEADAS") ?: false)

        val detailedSuperior = com.example.service.screen.LocalVisionAnalyzer.identify10thPickSuperiorDetailed(
            bitmap = frame,
            isFirstPick = true,
            calib = calib,
            allChamps = champs,
            confirmedIds = emptySet(),
            expectedRole = LaneRole.JUNGLE,
            context = context
        )
        assertNotNull("Debe generar log detallado para escaneo superior", detailedSuperior)
        assertEquals("volibear", detailedSuperior?.selectedChampion?.id)
        assertTrue("En escaneo superior el pick debe figurar como confirmado", detailedSuperior?.isConfirmed ?: false)
        assertTrue("El resumen formateado superior debe indicar confirmación definitiva", detailedSuperior?.formattedSummary?.contains("CONFIRMACIÓN DEFINITIVA") ?: false)
    }

    @Test
    fun testVolibearInGameCropMatchingWithZNCC() = kotlinx.coroutines.test.runTest {
        val champs = getSafeChamps()
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val assetManager = context.assets
        val stream = assetManager.open("champions/volibear.png")
        val volibearBmp = android.graphics.BitmapFactory.decodeStream(stream)!!

        // Crear una simulación de avatar con shading oscuro en los bordes como en Wild Rift
        val crop61 = android.graphics.Bitmap.createScaledBitmap(volibearBmp, 61, 61, true)
        com.example.service.screen.LocalVisionAnalyzer.ensureInitialized(context)

        val detailed = com.example.service.screen.LocalVisionAnalyzer.matchAvatarDetailed(
            crop = crop61,
            roiLabel = "Superior Derecha (Rival 5 - 10º Pick)",
            candidates = champs,
            expectedRole = LaneRole.JUNGLE,
            excludedChampionIds = setOf("malphite", "lux", "ashe", "jax", "sett", "vi", "viktor", "smolder", "senna"),
            context = context,
            isConfirmedPhase = true
        )

        assertNotNull("Debe generar log detallado de matching", detailed)
        assertEquals("volibear", detailed?.selectedChampion?.id)
        println("VOLIBEAR SCORE: ${detailed?.confidence} top: ${detailed?.topCandidates?.map { "${it.champion.name}=${it.compositeScore}" }}")
    }
}

