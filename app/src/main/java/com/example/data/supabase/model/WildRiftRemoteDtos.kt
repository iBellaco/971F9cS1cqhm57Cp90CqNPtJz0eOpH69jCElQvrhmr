package com.example.data.supabase.model

import com.example.model.Champion
import com.example.model.DamageType
import com.example.model.ItemCategory
import com.example.model.LaneRole
import com.example.model.RuneItem
import com.example.model.SummonerSpellItem
import com.example.model.WildRiftItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WrPatchDto(
    val id: String = "current",
    val version: String = "",
    val notes: String = "",
    @SerialName("updated_at") val updatedAt: String? = null
)

@Serializable
data class WrItemDto(
    val id: String = "",
    val name: String = "",
    @SerialName("name_en") val nameEn: String = "",
    @SerialName("name_pt") val namePt: String = "",
    val category: String = "BASIC",
    @SerialName("gold_cost") val goldCost: Int = 0,
    val stats: String = "",
    @SerialName("stats_en") val statsEn: String = "",
    @SerialName("stats_pt") val statsPt: String = "",
    val passive: String = "",
    @SerialName("passive_en") val passiveEn: String = "",
    @SerialName("passive_pt") val passivePt: String = "",
    @SerialName("icon_url") val iconUrl: String = ""
) {
    fun toModel(): WildRiftItem = WildRiftItem(
        id = id,
        name = name, nameEn = nameEn, namePt = namePt,
        category = category,
        goldCost = goldCost,
        stats = stats, statsEn = statsEn, statsPt = statsPt,
        passive = passive, passiveEn = passiveEn, passivePt = passivePt,
        iconUrl = iconUrl
    )
    companion object {
        fun fromModel(model: WildRiftItem): WrItemDto = WrItemDto(
            id = model.id,
            name = model.name, nameEn = model.nameEn, namePt = model.namePt,
            category = model.category,
            goldCost = model.goldCost,
            stats = model.stats, statsEn = model.statsEn, statsPt = model.statsPt,
            passive = model.passive, passiveEn = model.passiveEn, passivePt = model.passivePt,
            iconUrl = model.iconUrl
        )
    }
}

@Serializable
data class WrChampionDto(
    val id: String = "",
    val name: String = "",
    @SerialName("name_en") val nameEn: String = "",
    @SerialName("name_pt") val namePt: String = "",
    val title: String = "",
    @SerialName("title_en") val titleEn: String = "",
    @SerialName("title_pt") val titlePt: String = "",
    @SerialName("primary_role") val primaryRole: String = "TOP",
    @SerialName("secondary_roles") val secondaryRoles: String = "",
    val tier: String = "A",
    val winrate: Double = 50.0,
    val pickrate: Double = 5.0,
    val banrate: Double = 5.0,
    @SerialName("damage_type") val damageType: String = "PHYSICAL",
    @SerialName("avatar_url") val avatarUrl: String = "",
    val counters: String = "",
    val synergies: String = "",
    @SerialName("core_items") val coreItems: String = "",
    @SerialName("situational_items") val situationalItems: String = "",
    @SerialName("is_ranged") val isRanged: Boolean = false,
    @SerialName("is_frontline") val isFrontline: Boolean = false
) {
    fun toModel(existingFallback: Champion? = null): Champion {
        val pRole = try { LaneRole.valueOf(primaryRole) } catch (_: Exception) { LaneRole.TOP }
        val sRoles = secondaryRoles.split(",").mapNotNull { r ->
            try { LaneRole.valueOf(r.trim()) } catch (_: Exception) { null }
        }
        val dmg = try { DamageType.valueOf(damageType) } catch (_: Exception) { DamageType.PHYSICAL }
        val counterList = counters.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val synergyList = synergies.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val coreItemList = coreItems.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        val sitItemList = situationalItems.split(",").map { it.trim() }.filter { it.isNotEmpty() }

        return existingFallback?.copy(
            id = id,
            name = name.ifEmpty { existingFallback.name }, nameEn = nameEn.ifEmpty { existingFallback.nameEn }, namePt = namePt.ifEmpty { existingFallback.namePt },
            title = title.ifEmpty { existingFallback.title }, titleEn = titleEn.ifEmpty { existingFallback.titleEn }, titlePt = titlePt.ifEmpty { existingFallback.titlePt },
            primaryRole = pRole,
            secondaryRoles = if (sRoles.isNotEmpty()) sRoles else existingFallback.secondaryRoles,
            tier = tier,
            winrate = winrate,
            pickRate = pickrate,
            banRate = banrate,
            damageType = dmg,
            avatarUrl = avatarUrl.ifEmpty { existingFallback.avatarUrl },
            counteredBy = if (counterList.isNotEmpty()) counterList else existingFallback.counteredBy,
            synergies = if (synergyList.isNotEmpty()) synergyList else existingFallback.synergies,
            coreItems = if (coreItemList.isNotEmpty()) coreItemList else existingFallback.coreItems,
            situationalItems = if (sitItemList.isNotEmpty()) sitItemList else existingFallback.situationalItems,
            isRanged = isRanged,
            isFrontline = isFrontline
        ) ?: Champion(
            id = id,
            name = name, nameEn = nameEn, namePt = namePt,
            title = title, titleEn = titleEn, titlePt = titlePt,
            primaryRole = pRole,
            secondaryRoles = sRoles,
            tier = tier,
            winrate = winrate,
            pickRate = pickrate,
            banRate = banrate,
            damageType = dmg,
            avatarUrl = avatarUrl,
            counteredBy = counterList,
            synergies = synergyList,
            coreItems = coreItemList,
            situationalItems = sitItemList,
            isRanged = isRanged,
            isFrontline = isFrontline
        )
    }
    companion object {
        fun fromModel(model: Champion): WrChampionDto = WrChampionDto(
            id = model.id,
            name = model.name, nameEn = model.nameEn, namePt = model.namePt,
            title = model.title, titleEn = model.titleEn, titlePt = model.titlePt,
            primaryRole = model.primaryRole.name,
            secondaryRoles = model.secondaryRoles.joinToString(",") { it.name },
            tier = model.tier,
            winrate = model.winrate,
            pickrate = model.pickRate,
            banrate = model.banRate,
            damageType = model.damageType.name,
            avatarUrl = model.avatarUrl,
            counters = model.counteredBy.joinToString(","),
            synergies = model.synergies.joinToString(","),
            coreItems = model.coreItems.joinToString(","),
            situationalItems = model.situationalItems.joinToString(","),
            isRanged = model.isRanged,
            isFrontline = model.isFrontline
        )
    }
}

@Serializable
data class WrRuneDto(
    val id: String = "",
    val name: String = "",
    @SerialName("name_en") val nameEn: String = "",
    @SerialName("name_pt") val namePt: String = "",
    val category: String = "",
    @SerialName("icon_url") val iconUrl: String = "",
    val description: String = "",
    @SerialName("description_en") val descriptionEn: String = "",
    @SerialName("description_pt") val descriptionPt: String = ""
) {
    fun toModel(): RuneItem = RuneItem(id, name, nameEn, namePt, category, iconUrl, description, descriptionEn, descriptionPt)
    companion object {
        fun fromModel(model: RuneItem): WrRuneDto = WrRuneDto(
            id = model.id,
            name = model.name, nameEn = model.nameEn, namePt = model.namePt,
            category = model.category,
            iconUrl = model.iconUrl,
            description = model.description, descriptionEn = model.descriptionEn, descriptionPt = model.descriptionPt
        )
    }
}

@Serializable
data class WrSpellDto(
    val id: String = "",
    val name: String = "",
    @SerialName("name_en") val nameEn: String = "",
    @SerialName("name_pt") val namePt: String = "",
    val cooldown: String = "",
    @SerialName("icon_url") val iconUrl: String = "",
    val description: String = "",
    @SerialName("description_en") val descriptionEn: String = "",
    @SerialName("description_pt") val descriptionPt: String = ""
) {
    fun toModel(): SummonerSpellItem = SummonerSpellItem(id, name, nameEn, namePt, cooldown, iconUrl, description, descriptionEn, descriptionPt)
    companion object {
        fun fromModel(model: SummonerSpellItem): WrSpellDto = WrSpellDto(
            id = model.id,
            name = model.name, nameEn = model.nameEn, namePt = model.namePt,
            cooldown = model.cooldown,
            iconUrl = model.iconUrl,
            description = model.description, descriptionEn = model.descriptionEn, descriptionPt = model.descriptionPt
        )
    }
}
