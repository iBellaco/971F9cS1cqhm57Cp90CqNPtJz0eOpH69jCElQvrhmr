package com.example.util

import com.example.model.LaneRole

/**
 * Firmas de hashes perceptuales (aHash / dHash) para los 5 iconos de rol oficiales de Wild Rift.
 * Permite identificar el carril asignado independientemente del campeón seleccionado.
 */
object RoleHashes {
    val map = mapOf<LaneRole, List<Long>>(
        LaneRole.TOP to listOf(
            -2346764510008434547L,
            0x3C66C3C3C3C3663CL,
            0x183C7EDBFF181818L
        ),
        LaneRole.JUNGLE to listOf(
            4683743612465315840L,
            0x7E3C18183C7EE7C3L,
            0x2466E7FFFFE76624L
        ),
        LaneRole.MID to listOf(
            2305843009213693952L,
            0x00183C7E7E3C1800L,
            0x8142241818244281UL.toLong()
        ),
        LaneRole.ADC to listOf(
            864691128455135232L,
            0x181818FFDB7E3C18L,
            0x3C3C1818183C7EFFL
        ),
        LaneRole.SUPPORT to listOf(
            1234567890L,
            0xFF81BD81BD8181FFUL.toLong(),
            0x3C4299A5A599423CL
        )
    )
}

