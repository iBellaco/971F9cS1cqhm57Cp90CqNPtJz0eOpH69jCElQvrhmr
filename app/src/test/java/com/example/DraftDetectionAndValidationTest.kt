package com.example

import com.example.data.WildRiftRepository
import com.example.model.LaneRole
import com.example.service.screen.ChampionNameResolver
import com.example.service.screen.DraftValidationLayer
import com.example.service.screen.ScannedSlotInfo
import org.junit.Assert.assertEquals
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

        // Verificación de probabilidades dinámicas calibradas:
        // Ezreal, Karma y Zoe son 100% ciertos por composición
        assertEquals(100, resolvedEnemiesDetailed.confidences[LaneRole.ADC])
        assertEquals(100, resolvedEnemiesDetailed.confidences[LaneRole.SUPPORT])
        assertEquals(100, resolvedEnemiesDetailed.confidences[LaneRole.MID])
        // Riven y Sett tienen ambigüedad flex: Riven en TOP al 85% y Sett flexeado a JUG al 80%
        assertEquals(85, resolvedEnemiesDetailed.confidences[LaneRole.TOP])
        assertEquals(80, resolvedEnemiesDetailed.confidences[LaneRole.JUNGLE])
    }
}
