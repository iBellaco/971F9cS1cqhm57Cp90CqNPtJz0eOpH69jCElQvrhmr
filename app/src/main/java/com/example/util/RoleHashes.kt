package com.example.util

import com.example.model.LaneRole

object RoleHashes {
    val map = mapOf<LaneRole, Long>(
        LaneRole.TOP to -2346764510008434547L, // Valores hardcodeados de respaldo aproximados
        LaneRole.JUNGLE to 4683743612465315840L,
        LaneRole.MID to 2305843009213693952L,
        LaneRole.ADC to 864691128455135232L,
        LaneRole.SUPPORT to 1234567890L
    )
}
