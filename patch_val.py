import sys
with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "r") as f:
    content = f.read()

old_sig = """    fun resolveTeamRolesDetailed(
        scannedSlots: List<ScannedSlotInfo>,
        allChampions: List<Champion>,
        auditList: MutableList<String>
    ): ResolvedTeam {"""

new_sig = """    fun resolveTeamRolesDetailed(
        scannedSlots: List<ScannedSlotInfo>,
        allChampions: List<Champion>,
        auditList: MutableList<String>,
        isAllyTeam: Boolean = true
    ): ResolvedTeam {"""

old_step2 = """        // 2. ASIGNACIÓN POR POSICIÓN ESTÁNDAR DEL SLOT SI EL CAMPEÓN PUEDE JUGARLA (Certeza 95%)
        // En Wild Rift los slots 0..4 corresponden de forma fija a: TOP, JUNGLE, MID, ADC, SUPPORT
        for (slot in validSlots) {"""

new_step2 = """        // 2. ASIGNACIÓN POR POSICIÓN ESTÁNDAR DEL SLOT SI EL CAMPEÓN PUEDE JUGARLA (Certeza 95%)
        // En Wild Rift los slots 0..4 corresponden de forma fija a: TOP, JUNGLE, MID, ADC, SUPPORT
        // ESTO SOLO APLICA PARA EL EQUIPO ALIADO (El enemigo se ordena por orden de pick, no por rol)
        if (isAllyTeam) {
            for (slot in validSlots) {"""

old_step3 = """        // 3. ASIGNACIÓN POR ROL PRIMARIO DEL CAMPEÓN"""
new_step3 = """            }
        }

        // 3. ASIGNACIÓN POR ROL PRIMARIO DEL CAMPEÓN"""

content = content.replace(old_sig, new_sig)
content = content.replace(old_step2, new_step2)
content = content.replace(old_step3, new_step3)

with open("app/src/main/java/com/example/service/screen/DraftValidationLayer.kt", "w") as f:
    f.write(content)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "r") as f:
    content = f.read()

old_enemy_call = "val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, auditList)"
new_enemy_call = "val enemyResolved = DraftValidationLayer.resolveTeamRolesDetailed(validEnemySlots, allChamps, auditList, isAllyTeam = false)"
content = content.replace(old_enemy_call, new_enemy_call)

with open("app/src/main/java/com/example/service/screen/DraftVisionScanner.kt", "w") as f:
    f.write(content)
