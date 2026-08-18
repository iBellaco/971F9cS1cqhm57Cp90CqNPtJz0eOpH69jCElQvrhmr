package com.example.data.sync

import android.util.Log
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager {
    companion object {
        var initErrorMsg: String? = null
    }

    private val db: FirebaseFirestore? by lazy {
        try {
            FirebaseFirestore.getInstance()
        } catch (e: Exception) {
            initErrorMsg = e.localizedMessage ?: e.toString()
            Log.e("FirestoreManager", "Firestore not initialized", e)
            null
        }
    }
    private val championsCollection by lazy { db?.collection("champions") }
    private val spellsCollection by lazy { db?.collection("spells") }
    private val runesCollection by lazy { db?.collection("runes") }
    private val itemsCollection by lazy { db?.collection("items") }
    private val objectivesCollection by lazy { db?.collection("objectives") }

    suspend fun uploadLocalDataToFirestore(): String? {
        if (championsCollection == null) return "Fallo al iniciar Firestore: ${initErrorMsg ?: "Desconocido"}"
        
        return try {
            val localChampions = WildRiftRepository.champions
            for (champion in localChampions) {
                // Ensure Enums are serialized as strings to avoid complex custom mapping requirements if not configured
                val data = hashMapOf(
                    "id" to champion.id,
                    "name" to champion.name,
                    "title" to champion.title,
                    "ddragonId" to champion.ddragonId,
                    "avatarUrl" to champion.avatarUrl,
                    "primaryRole" to champion.primaryRole.name,
                    "secondaryRoles" to champion.secondaryRoles.map { it.name },
                    "tier" to champion.tier,
                    "winrate" to champion.winrate,
                    "pickRate" to champion.pickRate,
                    "banRate" to champion.banRate,
                    "damageType" to champion.damageType.name,
                    "summary" to champion.summary,
                    "advantageAgainst" to champion.advantageAgainst,
                    "counteredBy" to champion.counteredBy,
                    "synergies" to champion.synergies,
                    "tacticalAdvice" to champion.tacticalAdvice,
                    "recommendedRunes" to champion.recommendedRunes,
                    "runeTreeDetails" to champion.runeTreeDetails,
                    "primaryRuneIconUrl" to champion.primaryRuneIconUrl,
                    "recommendedSpells" to champion.recommendedSpells,
                    "spellsIcons" to champion.spellsIcons,
                    "coreItems" to champion.coreItems,
                    "coreItemsIcons" to champion.coreItemsIcons,
                    "situationalItems" to champion.situationalItems,
                    "situationalItemsIcons" to champion.situationalItemsIcons,
                    "skillOrder" to champion.skillOrder,
                    "skills" to champion.skills.map { skill ->
                        hashMapOf(
                            "slot" to skill.slot,
                            "slotName" to skill.slotName,
                            "name" to skill.name,
                            "iconUrl" to skill.iconUrl,
                            "description" to skill.description,
                            "cooldown" to skill.cooldown
                        )
                    },
                    "isRanged" to champion.isRanged,
                    "isFrontline" to champion.isFrontline,
                    "wildRiftFireUrl" to champion.wildRiftFireUrl,
                    "wrMetaUrl" to champion.wrMetaUrl,
                    "wildRiftCoreUrl" to champion.wildRiftCoreUrl,
                    "bestBuildWrUrl" to champion.bestBuildWrUrl
                )
                championsCollection?.document(champion.id)?.set(data)?.await()
                Log.d("FirestoreManager", "Uploaded champion: ${champion.name}")
            }

            val spells = WildRiftRepository.summonerSpells
            for (spell in spells) {
                val data = hashMapOf(
                    "id" to spell.id,
                    "name" to spell.name,
                    "cooldown" to spell.cooldown,
                    "iconUrl" to spell.iconUrl,
                    "description" to spell.description
                )
                spellsCollection?.document(spell.id)?.set(data)?.await()
            }

            val runes = WildRiftRepository.runes
            for (rune in runes) {
                val data = hashMapOf(
                    "id" to rune.id,
                    "name" to rune.name,
                    "category" to rune.category,
                    "iconUrl" to rune.iconUrl,
                    "description" to rune.description
                )
                runesCollection?.document(rune.id)?.set(data)?.await()
            }

            val items = WildRiftRepository.items
            for (item in items) {
                val data = hashMapOf(
                    "id" to item.id,
                    "name" to item.name,
                    "category" to item.category.name,
                    "goldCost" to item.goldCost,
                    "stats" to item.stats,
                    "passive" to item.passive,
                    "iconUrl" to item.iconUrl
                )
                itemsCollection?.document(item.id)?.set(data)?.await()
            }

            val objectives = WildRiftRepository.mapObjectives
            for (obj in objectives) {
                val data = hashMapOf(
                    "id" to obj.id,
                    "name" to obj.name,
                    "spawnTime" to obj.spawnTime,
                    "respawnTime" to obj.respawnTime,
                    "iconUrl" to obj.iconUrl,
                    "buffDescription" to obj.buffDescription,
                    "tactics" to obj.tactics
                )
                objectivesCollection?.document(obj.id)?.set(data)?.await()
            }

            null // Null means success (no error)
        } catch (e: Exception) {
            Log.e("FirestoreManager", "Error uploading data", e)
            e.localizedMessage ?: e.toString()
        }
    }

    suspend fun getChampionsFromFirestore(): List<Champion> {
        if (championsCollection == null) return emptyList()
        return try {
            val snapshot = championsCollection!!.get().await()
            val champions = snapshot.documents.mapNotNull { doc ->
                try {
                    val primaryRoleStr = doc.getString("primaryRole") ?: "MID"
                    val primaryRole = com.example.model.LaneRole.valueOf(primaryRoleStr)
                    
                    val secondaryRolesList = doc.get("secondaryRoles") as? List<String> ?: emptyList()
                    val secondaryRoles = secondaryRolesList.map { com.example.model.LaneRole.valueOf(it) }

                    val damageTypeStr = doc.getString("damageType") ?: "PHYSICAL"
                    val damageType = com.example.model.DamageType.valueOf(damageTypeStr)

                    val skillsList = doc.get("skills") as? List<Map<String, Any>> ?: emptyList()
                    val skills = skillsList.map { skillMap ->
                        com.example.model.ChampionSkill(
                            slot = skillMap["slot"] as? String ?: "",
                            slotName = skillMap["slotName"] as? String ?: "",
                            name = skillMap["name"] as? String ?: "",
                            iconUrl = skillMap["iconUrl"] as? String ?: "",
                            description = skillMap["description"] as? String ?: "",
                            cooldown = skillMap["cooldown"] as? String ?: ""
                        )
                    }

                    com.example.model.Champion(
                        id = doc.getString("id") ?: "",
                        name = doc.getString("name") ?: "",
                        title = doc.getString("title") ?: "",
                        ddragonId = doc.getString("ddragonId") ?: "",
                        avatarUrl = doc.getString("avatarUrl") ?: "",
                        primaryRole = primaryRole,
                        secondaryRoles = secondaryRoles,
                        tier = doc.getString("tier") ?: "B",
                        winrate = doc.getDouble("winrate") ?: 50.0,
                        pickRate = doc.getDouble("pickRate") ?: 0.0,
                        banRate = doc.getDouble("banRate") ?: 0.0,
                        damageType = damageType,
                        summary = doc.getString("summary") ?: "",
                        advantageAgainst = doc.get("advantageAgainst") as? List<String> ?: emptyList(),
                        counteredBy = doc.get("counteredBy") as? List<String> ?: emptyList(),
                        synergies = doc.get("synergies") as? List<String> ?: emptyList(),
                        tacticalAdvice = doc.getString("tacticalAdvice") ?: "",
                        recommendedRunes = doc.getString("recommendedRunes") ?: "",
                        runeTreeDetails = doc.getString("runeTreeDetails") ?: "",
                        primaryRuneIconUrl = doc.getString("primaryRuneIconUrl") ?: "",
                        recommendedSpells = doc.get("recommendedSpells") as? List<String> ?: emptyList(),
                        spellsIcons = doc.get("spellsIcons") as? List<String> ?: emptyList(),
                        coreItems = doc.get("coreItems") as? List<String> ?: emptyList(),
                        coreItemsIcons = doc.get("coreItemsIcons") as? List<String> ?: emptyList(),
                        situationalItems = doc.get("situationalItems") as? List<String> ?: emptyList(),
                        situationalItemsIcons = doc.get("situationalItemsIcons") as? List<String> ?: emptyList(),
                        skillOrder = doc.getString("skillOrder") ?: "",
                        skills = skills,
                        isRanged = doc.getBoolean("isRanged") ?: false,
                        isFrontline = doc.getBoolean("isFrontline") ?: false,
                        wildRiftFireUrl = doc.getString("wildRiftFireUrl") ?: "",
                        wrMetaUrl = doc.getString("wrMetaUrl") ?: "",
                        wildRiftCoreUrl = doc.getString("wildRiftCoreUrl") ?: "",
                        bestBuildWrUrl = doc.getString("bestBuildWrUrl") ?: ""
                    )
                } catch (e: Exception) {
                    Log.e("FirestoreManager", "Error parsing champion ${doc.id}", e)
                    null
                }
            }
            champions
        } catch (e: Exception) {
            Log.e("FirestoreManager", "Error fetching champions", e)
            emptyList()
        }
    }

    suspend fun getSpellsFromFirestore(): List<com.example.model.SummonerSpellItem> {
        if (spellsCollection == null) return emptyList()
        return try {
            val snapshot = spellsCollection!!.get().await()
            snapshot.documents.mapNotNull { doc ->
                try {
                    com.example.model.SummonerSpellItem(
                        id = doc.getString("id") ?: "",
                        name = doc.getString("name") ?: "",
                        cooldown = doc.getString("cooldown") ?: "",
                        iconUrl = doc.getString("iconUrl") ?: "",
                        description = doc.getString("description") ?: ""
                    )
                } catch (e: Exception) {
                    null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getRunesFromFirestore(): List<com.example.model.RuneItem> {
        if (runesCollection == null) return emptyList()
        return try {
            val snapshot = runesCollection!!.get().await()
            snapshot.documents.mapNotNull { doc ->
                try {
                    com.example.model.RuneItem(
                        id = doc.getString("id") ?: "",
                        name = doc.getString("name") ?: "",
                        category = doc.getString("category") ?: "",
                        iconUrl = doc.getString("iconUrl") ?: "",
                        description = doc.getString("description") ?: ""
                    )
                } catch (e: Exception) {
                    null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getItemsFromFirestore(): List<com.example.model.WildRiftItem> {
        if (itemsCollection == null) return emptyList()
        return try {
            val snapshot = itemsCollection!!.get().await()
            snapshot.documents.mapNotNull { doc ->
                try {
                    val catStr = doc.getString("category") ?: "PHYSICAL"
                    val cat = com.example.model.ItemCategory.valueOf(catStr)
                    com.example.model.WildRiftItem(
                        id = doc.getString("id") ?: "",
                        name = doc.getString("name") ?: "",
                        category = cat,
                        goldCost = doc.getLong("goldCost")?.toInt() ?: 0,
                        stats = doc.getString("stats") ?: "",
                        passive = doc.getString("passive") ?: "",
                        iconUrl = doc.getString("iconUrl") ?: ""
                    )
                } catch (e: Exception) {
                    null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getObjectivesFromFirestore(): List<com.example.model.MapObjectiveItem> {
        if (objectivesCollection == null) return emptyList()
        return try {
            val snapshot = objectivesCollection!!.get().await()
            snapshot.documents.mapNotNull { doc ->
                try {
                    com.example.model.MapObjectiveItem(
                        id = doc.getString("id") ?: "",
                        name = doc.getString("name") ?: "",
                        spawnTime = doc.getString("spawnTime") ?: "",
                        respawnTime = doc.getString("respawnTime") ?: "",
                        iconUrl = doc.getString("iconUrl") ?: "",
                        buffDescription = doc.getString("buffDescription") ?: "",
                        tactics = doc.getString("tactics") ?: ""
                    )
                } catch (e: Exception) {
                    null
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
