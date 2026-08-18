package com.example.data.sync

import android.util.Log
import com.example.data.WildRiftRepository
import com.example.model.Champion
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager {
    private val db = FirebaseFirestore.getInstance()
    private val championsCollection = db.collection("champions")

    suspend fun uploadLocalDataToFirestore(): Boolean {
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
                championsCollection.document(champion.id).set(data).await()
                Log.d("FirestoreManager", "Uploaded champion: ${champion.name}")
            }
            true
        } catch (e: Exception) {
            Log.e("FirestoreManager", "Error uploading data", e)
            false
        }
    }

    suspend fun getChampionsFromFirestore(): List<Champion> {
        return try {
            val snapshot = championsCollection.get().await()
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
}
