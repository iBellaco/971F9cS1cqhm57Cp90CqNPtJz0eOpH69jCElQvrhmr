with open("app/src/main/java/com/example/service/screen/VisionCalibrationConfig.kt", "r") as f:
    c = f.read()
c = c.replace("val enemyAvatarCenterX: Float = 0.927f", "val enemyAvatarCenterX: Float = 0.957f")
with open("app/src/main/java/com/example/service/screen/VisionCalibrationConfig.kt", "w") as f:
    f.write(c)

with open("app/src/main/java/com/example/ui/components/DraftCalibrationPanel.kt", "r") as f:
    panel_c = f.read()

old_modify = """    fun modify(deltaX: Float = 0f, deltaY: Float = 0f, deltaSize: Float = 0f) {
        val cur = config
        val updated = when (selectedTarget) {
            CalibrationTarget.GLOBAL_ALLY_X -> cur.copy(allyAvatarCenterX = (cur.allyAvatarCenterX + deltaX).coerceIn(0.01f, 0.40f))
            CalibrationTarget.GLOBAL_ENEMY_X -> cur.copy(enemyAvatarCenterX = (cur.enemyAvatarCenterX + deltaX).coerceIn(0.60f, 0.99f))
            CalibrationTarget.AVATAR_SIZE -> cur.copy(avatarDiameterRatio = (cur.avatarDiameterRatio + deltaSize).coerceIn(0.04f, 0.28f))
            CalibrationTarget.ALLY_SLOT_0 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[0] = (it[0] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_1 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[1] = (it[1] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_2 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[2] = (it[2] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_3 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[3] = (it[3] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_4 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[4] = (it[4] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_0 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[0] = (it[0] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_1 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[1] = (it[1] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_2 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[2] = (it[2] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_3 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[3] = (it[3] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_4 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[4] = (it[4] + deltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.GLOBAL_Y -> cur.copy(
                allySlotYRatios = cur.allySlotYRatios.map { (it + deltaY).coerceIn(0.05f, 0.95f) },
                enemySlotYRatios = cur.enemySlotYRatios.map { (it + deltaY).coerceIn(0.05f, 0.95f) }
            )
        }
        updateAndApply(updated)
    }"""

new_modify = """    fun modify(deltaX: Float = 0f, deltaY: Float = 0f, deltaSize: Float = 0f) {
        val cur = config
        val effectiveDeltaSize = if (deltaSize != 0f) deltaSize else (deltaY * 0.5f + deltaX * 0.5f)
        val effectiveDeltaX = if (deltaX != 0f) deltaX else deltaY
        val effectiveDeltaY = if (deltaY != 0f) deltaY else deltaX

        val updated = when (selectedTarget) {
            CalibrationTarget.GLOBAL_ALLY_X -> cur.copy(allyAvatarCenterX = (cur.allyAvatarCenterX + effectiveDeltaX).coerceIn(0.01f, 0.40f))
            CalibrationTarget.GLOBAL_ENEMY_X -> cur.copy(enemyAvatarCenterX = (cur.enemyAvatarCenterX + effectiveDeltaX).coerceIn(0.60f, 0.99f))
            CalibrationTarget.AVATAR_SIZE -> cur.copy(avatarDiameterRatio = (cur.avatarDiameterRatio + effectiveDeltaSize).coerceIn(0.04f, 0.28f))
            CalibrationTarget.ALLY_SLOT_0 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[0] = (it[0] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_1 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[1] = (it[1] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_2 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[2] = (it[2] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_3 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[3] = (it[3] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ALLY_SLOT_4 -> cur.copy(allySlotYRatios = cur.allySlotYRatios.toMutableList().also { it[4] = (it[4] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_0 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[0] = (it[0] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_1 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[1] = (it[1] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_2 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[2] = (it[2] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_3 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[3] = (it[3] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.ENEMY_SLOT_4 -> cur.copy(enemySlotYRatios = cur.enemySlotYRatios.toMutableList().also { it[4] = (it[4] + effectiveDeltaY).coerceIn(0.05f, 0.95f) })
            CalibrationTarget.GLOBAL_Y -> cur.copy(
                allySlotYRatios = cur.allySlotYRatios.map { (it + effectiveDeltaY).coerceIn(0.05f, 0.95f) },
                enemySlotYRatios = cur.enemySlotYRatios.map { (it + effectiveDeltaY).coerceIn(0.05f, 0.95f) }
            )
        }
        updateAndApply(updated)
    }"""

if old_modify in panel_c:
    panel_c = panel_c.replace(old_modify, new_modify)
    with open("app/src/main/java/com/example/ui/components/DraftCalibrationPanel.kt", "w") as f:
        f.write(panel_c)
    print("DraftCalibrationPanel patched successfully!")
else:
    print("Error: old_modify not found!")
