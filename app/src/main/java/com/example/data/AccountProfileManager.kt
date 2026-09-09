package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.util.SubscriptionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

@Serializable
data class BlueEssencePurchase(
    val amount: Int,
    val price: Double,
    val currency: String = "USD",
    val timestamp: Long = System.currentTimeMillis()
)

@Serializable
data class CreatorChampionBuild(
    val championId: String,
    val championName: String,
    val role: String = "Mid",
    val title: String = "Build de Élite",
    val startingItem: String = "",
    val bootsItem: String = "",
    val bootsEnchant: String = "",
    val coreItems: List<String> = emptyList(),
    val situationalItems: List<String> = emptyList(),
    val keystoneRune: String = "",
    val secondaryRunes: List<String> = emptyList(),
    val spell1: String = "",
    val spell2: String = "",
    val guideNotes: String = "",
    val comboTips: String = "",
    val lastUpdated: Long = System.currentTimeMillis()
)

@Serializable
data class AccountProfile(
    val id: String,
    val name: String,
    val tag: String = "",
    val avatarId: String = "default_poro",
    val isDefault: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val blueEssence: Int = 0,
    val purchaseHistory: List<BlueEssencePurchase> = emptyList(),
    val isCreator: Boolean = false,
    val creatorStatus: String = "NONE", // "NONE", "PENDING", "APPROVED"
    val creatorApplicationReason: String = "",
    val subscribersCount: Int = 0,
    val subscribers: List<String> = emptyList(),
    val subscribedTo: List<String> = emptyList(),
    val creatorBuild: CreatorChampionBuild? = null
)

object AccountProfileManager {

    private const val PREFS_NAME = "wr_account_profiles_prefs"
    private const val KEY_PROFILES_JSON = "account_profiles_json"
    private const val KEY_ACTIVE_PROFILE_ID = "active_account_profile_id"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    private val _allProfiles = MutableStateFlow<List<AccountProfile>>(emptyList())
    val allProfiles: StateFlow<List<AccountProfile>> = _allProfiles.asStateFlow()

    private val _activeProfileId = MutableStateFlow<String>("default")
    val activeProfileId: StateFlow<String> = _activeProfileId.asStateFlow()

    private val _activeProfile = MutableStateFlow<AccountProfile>(
        AccountProfile("default", "Cuenta Principal", tag = "Main", isDefault = true)
    )
    val activeProfile: StateFlow<AccountProfile> = _activeProfile.asStateFlow()

    private fun updateActiveProfileInternal() {
        val currentId = _activeProfileId.value
        val found = _allProfiles.value.find { it.id == currentId }
            ?: _allProfiles.value.firstOrNull()
            ?: AccountProfile("default", "Cuenta Principal", tag = "Main", isDefault = true)
        _activeProfile.value = found
    }

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun init(context: Context) {
        val prefs = getPrefs(context)
        val rawJson = prefs.getString(KEY_PROFILES_JSON, null)
        val defaultName = SubscriptionManager.userName.value.ifBlank { "Cuenta Principal" }

        val profiles = if (!rawJson.isNullOrBlank()) {
            try {
                json.decodeFromString<List<AccountProfile>>(rawJson)
            } catch (_: Exception) {
                listOf(AccountProfile("default", defaultName, tag = "Main", isDefault = true))
            }
        } else {
            listOf(AccountProfile("default", defaultName, tag = "Main", isDefault = true))
        }

        val savedActiveId = prefs.getString(KEY_ACTIVE_PROFILE_ID, "default") ?: "default"
        val activeExists = profiles.any { it.id == savedActiveId }

        _allProfiles.value = profiles
        _activeProfileId.value = if (activeExists) savedActiveId else profiles.firstOrNull()?.id ?: "default"
        updateActiveProfileInternal()
    }

    fun getActiveProfile(context: Context): AccountProfile {
        if (_allProfiles.value.isEmpty()) {
            init(context)
        }
        val currentId = _activeProfileId.value
        return _allProfiles.value.find { it.id == currentId }
            ?: _allProfiles.value.firstOrNull()
            ?: AccountProfile("default", "Cuenta Principal", tag = "Main", isDefault = true)
    }

    fun setActiveProfile(context: Context, profileId: String) {
        _activeProfileId.value = profileId
        getPrefs(context).edit().putString(KEY_ACTIVE_PROFILE_ID, profileId).apply()
        updateActiveProfileInternal()
    }

    fun createProfile(context: Context, name: String, tag: String = "", avatarId: String = "default_poro"): AccountProfile {
        val currentList = _allProfiles.value.toMutableList()
        val newProfile = AccountProfile(
            id = "acc_${UUID.randomUUID().toString().take(8)}",
            name = name.ifBlank { "Invocador #${currentList.size + 1}" },
            tag = tag.trim(),
            avatarId = avatarId,
            isDefault = currentList.isEmpty()
        )
        currentList.add(newProfile)
        saveProfiles(context, currentList)
        setActiveProfile(context, newProfile.id)
        return newProfile
    }

    fun updateProfile(context: Context, profileId: String, newName: String, newTag: String = "", newAvatarId: String = "default_poro") {
        val updated = _allProfiles.value.map { profile ->
            if (profile.id == profileId) {
                profile.copy(
                    name = newName.ifBlank { profile.name },
                    tag = newTag.trim(),
                    avatarId = newAvatarId
                )
            } else {
                profile
            }
        }
        saveProfiles(context, updated)
    }

    fun deleteProfile(context: Context, profileId: String) {
        if (_allProfiles.value.size <= 1) return // Do not delete the last profile
        val updated = _allProfiles.value.filterNot { it.id == profileId }
        saveProfiles(context, updated)
        if (_activeProfileId.value == profileId) {
            val fallback = updated.firstOrNull()?.id ?: "default"
            setActiveProfile(context, fallback)
        }
    }

    fun importProfiles(context: Context, profiles: List<AccountProfile>, targetActiveId: String? = null) {
        if (profiles.isNotEmpty()) {
            saveProfiles(context, profiles)
            val newActive = if (targetActiveId != null && profiles.any { it.id == targetActiveId }) {
                targetActiveId
            } else {
                profiles.first().id
            }
            setActiveProfile(context, newActive)
        }
    }

    fun buyBlueEssence(context: Context, profileId: String, amount: Int, price: Double) {
        val currentProfiles = _allProfiles.value.toMutableList()
        val index = currentProfiles.indexOfFirst { it.id == profileId }
        if (index != -1) {
            val prof = currentProfiles[index]
            val newPurchase = BlueEssencePurchase(amount, price)
            val updatedProf = prof.copy(
                blueEssence = prof.blueEssence + amount,
                purchaseHistory = prof.purchaseHistory + newPurchase
            )
            currentProfiles[index] = updatedProf
            saveProfiles(context, currentProfiles)
        }
    }

    fun spendBlueEssence(context: Context, profileId: String, amount: Int): Boolean {
        val currentProfiles = _allProfiles.value.toMutableList()
        val index = currentProfiles.indexOfFirst { it.id == profileId }
        if (index != -1) {
            val prof = currentProfiles[index]
            if (prof.blueEssence >= amount) {
                val updatedProf = prof.copy(
                    blueEssence = prof.blueEssence - amount
                )
                currentProfiles[index] = updatedProf
                saveProfiles(context, currentProfiles)
                return true
            }
        }
        return false
    }

    fun applyForCreator(context: Context, profileId: String, reason: String = "") {
        val currentProfiles = _allProfiles.value.toMutableList()
        val index = currentProfiles.indexOfFirst { it.id == profileId }
        if (index != -1) {
            val prof = currentProfiles[index]
            val updatedProf = prof.copy(
                isCreator = true,
                creatorStatus = "APPROVED",
                creatorApplicationReason = reason.trim()
            )
            currentProfiles[index] = updatedProf
            saveProfiles(context, currentProfiles)
        }
    }

    /**
     * Guarda la build del Creador garantizando que SOLO PUEDE CREAR DE 1 CAMPEÓN.
     */
    fun saveCreatorBuild(context: Context, profileId: String, build: CreatorChampionBuild): Boolean {
        val currentProfiles = _allProfiles.value.toMutableList()
        val index = currentProfiles.indexOfFirst { it.id == profileId }
        if (index != -1) {
            val prof = currentProfiles[index]
            if (!prof.isCreator) return false
            // Actualiza o establece su build única para su campeón
            val updatedProf = prof.copy(
                creatorBuild = build.copy(lastUpdated = System.currentTimeMillis())
            )
            currentProfiles[index] = updatedProf
            saveProfiles(context, currentProfiles)
            return true
        }
        return false
    }

    fun deleteCreatorBuild(context: Context, profileId: String) {
        val currentProfiles = _allProfiles.value.toMutableList()
        val index = currentProfiles.indexOfFirst { it.id == profileId }
        if (index != -1) {
            val prof = currentProfiles[index]
            val updatedProf = prof.copy(creatorBuild = null)
            currentProfiles[index] = updatedProf
            saveProfiles(context, currentProfiles)
        }
    }

    /**
     * Suscripción entre usuarios con Esencia Azul. Límite estricto de 100 suscriptores.
     */
    fun subscribeToUser(
        context: Context,
        subscriberProfileId: String,
        targetProfileId: String,
        essenceCost: Int = 100
    ): Pair<Boolean, String> {
        if (subscriberProfileId == targetProfileId) {
            return Pair(false, "No puedes suscribirte a tu propia cuenta.")
        }

        val currentProfiles = _allProfiles.value.toMutableList()
        val subIndex = currentProfiles.indexOfFirst { it.id == subscriberProfileId }
        val targetIndex = currentProfiles.indexOfFirst { it.id == targetProfileId }

        if (subIndex == -1) {
            return Pair(false, "Perfil suscriptor no encontrado.")
        }

        val subscriber = currentProfiles[subIndex]
        if (subscriber.subscribedTo.contains(targetProfileId)) {
            return Pair(false, "Ya estás suscrito a este usuario.")
        }

        if (subscriber.blueEssence < essenceCost) {
            return Pair(false, "Esencia Azul insuficiente. Necesitas $essenceCost EA.")
        }

        if (targetIndex != -1) {
            val target = currentProfiles[targetIndex]
            if (target.subscribersCount >= 100 || target.subscribers.size >= 100) {
                return Pair(false, "Este usuario ya alcanzó el límite máximo de 100 suscriptores.")
            }

            // Aplicar suscripción
            val updatedSub = subscriber.copy(
                blueEssence = subscriber.blueEssence - essenceCost,
                subscribedTo = subscriber.subscribedTo + targetProfileId
            )
            val updatedTarget = target.copy(
                subscribersCount = (target.subscribersCount + 1).coerceAtMost(100),
                subscribers = (target.subscribers + subscriberProfileId).distinct().take(100),
                blueEssence = target.blueEssence + (essenceCost * 8 / 10) // 80% va al creador
            )

            currentProfiles[subIndex] = updatedSub
            currentProfiles[targetIndex] = updatedTarget
            saveProfiles(context, currentProfiles)
            return Pair(true, "¡Te has suscrito con éxito por $essenceCost Esencias Azules!")
        } else {
            // Es un creador de la comunidad externo
            if (subscriber.blueEssence < essenceCost) {
                return Pair(false, "Esencia Azul insuficiente.")
            }
            val updatedSub = subscriber.copy(
                blueEssence = subscriber.blueEssence - essenceCost,
                subscribedTo = subscriber.subscribedTo + targetProfileId
            )
            currentProfiles[subIndex] = updatedSub
            saveProfiles(context, currentProfiles)
            return Pair(true, "¡Te has suscrito con éxito al Creador por $essenceCost Esencias Azules!")
        }
    }

    fun unsubscribeFromUser(context: Context, subscriberProfileId: String, targetProfileId: String) {
        val currentProfiles = _allProfiles.value.toMutableList()
        val subIndex = currentProfiles.indexOfFirst { it.id == subscriberProfileId }
        val targetIndex = currentProfiles.indexOfFirst { it.id == targetProfileId }

        if (subIndex != -1) {
            val subscriber = currentProfiles[subIndex]
            val updatedSub = subscriber.copy(
                subscribedTo = subscriber.subscribedTo - targetProfileId
            )
            currentProfiles[subIndex] = updatedSub

            if (targetIndex != -1) {
                val target = currentProfiles[targetIndex]
                val updatedTarget = target.copy(
                    subscribersCount = (target.subscribersCount - 1).coerceAtLeast(0),
                    subscribers = target.subscribers - subscriberProfileId
                )
                currentProfiles[targetIndex] = updatedTarget
            }
            saveProfiles(context, currentProfiles)
        }
    }

    private fun saveProfiles(context: Context, profiles: List<AccountProfile>) {
        _allProfiles.value = profiles
        val encoded = json.encodeToString(profiles)
        getPrefs(context).edit().putString(KEY_PROFILES_JSON, encoded).apply()
        updateActiveProfileInternal()
    }
}
