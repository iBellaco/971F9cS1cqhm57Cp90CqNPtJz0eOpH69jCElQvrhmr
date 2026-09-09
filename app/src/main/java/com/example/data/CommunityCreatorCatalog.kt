package com.example.data

import android.content.Context
import com.example.model.Champion
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class CommunityCreatorItem(
    val id: String,
    val name: String,
    val tag: String,
    val avatarId: String,
    val isLocalUser: Boolean = false,
    val subscribersCount: Int,
    val subscribers: List<String> = emptyList(),
    val build: CreatorChampionBuild
)

object CommunityCreatorCatalog {

    private const val PREFS_NAME = "wr_community_creators_prefs"
    private const val KEY_COMMUNITY_DATA = "community_creators_data"

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = true
    }

    private val defaultCreators: List<CommunityCreatorItem> = listOf(
        CommunityCreatorItem(
            id = "creator_coach_diego",
            name = "Coach Diego",
            tag = "Challenger #1",
            avatarId = "pro_coach",
            isLocalUser = false,
            subscribersCount = 94, // Cerca del límite de 100
            subscribers = (1..94).map { "sub_dummy_$it" },
            build = CreatorChampionBuild(
                championId = "zed",
                championName = "Zed",
                role = "Mid",
                title = "Letalidad Sombría & Ejecución Quirúrgica",
                startingItem = "Espada Larga",
                bootsItem = "Botas Jonias de la Lucidez",
                bootsEnchant = "Protocinturón",
                coreItems = listOf("Filo Fantasma de Youmuu", "Guja Sombría", "Filo de la Noche", "Rencor de Serylda", "Ángel custodio"),
                situationalItems = listOf("Colmillo de Serpiente", "Fauce de Malmortius"),
                keystoneRune = "Electrocutar",
                secondaryRunes = listOf("Impacto Repentino", "Marca del Verdugo", "Cazador Voraz"),
                spell1 = "Destello",
                spell2 = "Prender",
                guideNotes = "Early Game (Niveles 1-5): Juega seguro hasta desbloquear H2 (Sombra Viviente) y H3 (Cuchillada de Sombra). Tu pico de presión real ocurre en el nivel 5 con la Definitiva (H4 - Marca de la Muerte). Nunca malgastes H2 de forma ofensiva sin visión del jungla enemigo.\n\nMid/Late Game: Prioriza flanqueos hacia los tiradores o magos rivales antes de que inicie la pelea por el Dragón o Barón. Utiliza la sombra de H2 para escapar tras detonar la marca de H4.",
                comboTips = "Combo Asesinato: H2 (Sombra hacia flanco) -> Definitiva (H4) sobre el objetivo prioritario -> H3 -> H1 (Doble Shuriken) -> Básico potenciado por pasiva -> Reactivar H4 o H2 para reposicionarse.",
                lastUpdated = System.currentTimeMillis() - 86400000L
            )
        ),
        CommunityCreatorItem(
            id = "creator_faker_mid",
            name = "AzirKing",
            tag = "Gran Maestro",
            avatarId = "challenger_crest",
            isLocalUser = false,
            subscribersCount = 89,
            subscribers = (1..89).map { "sub_dummy_$it" },
            build = CreatorChampionBuild(
                championId = "ahri",
                championName = "Ahri",
                role = "Mid",
                title = "Encanto Glacial & Rotaciones Pro",
                startingItem = "Tomo Amplificador",
                bootsItem = "Botas de Hechicero",
                bootsEnchant = "Estasis (Zhonya)",
                coreItems = listOf("Eco de Luden", "Cetro de Cristal de Rylai", "Sombrero Mortal de Rabadon", "Bastón del Vacío", "Corona de la Reina Ahogada"),
                situationalItems = listOf("Morellonomicón", "Velo del Hada de la Muerte"),
                keystoneRune = "Electrocutar",
                secondaryRunes = listOf("Orbe de Anulación", "Piroláser", "Trascendencia"),
                spell1 = "Destello",
                spell2 = "Prender",
                guideNotes = "Fase de Líneas: Conecta H3 (Beso Encantador) únicamente cuando el enemigo gaste su habilidad de desplazamiento o intente rematar un súbdito cañón. Combina H1 (Orbe del Engaño) para desgastar la línea.\n\nObjetivos Neutrales: Guarda las cargas de la Definitiva (H4 - Impulso Espiritual) para reposicionarte en la fosa del Dragón e interceptar al jungla rival.",
                comboTips = "Combo Élite: H3 (Encanto) -> H1 (Orbe) -> H2 (Fuego Zorruno) -> Definitiva (H4) para rematar o esquivar proyectiles.",
                lastUpdated = System.currentTimeMillis() - 172800000L
            )
        ),
        CommunityCreatorItem(
            id = "creator_top_titan",
            name = "DarkinLord",
            tag = "Challenger Top",
            avatarId = "avatar_aatrox",
            isLocalUser = false,
            subscribersCount = 99, // A solo 1 del límite!
            subscribers = (1..99).map { "sub_dummy_$it" },
            build = CreatorChampionBuild(
                championId = "aatrox",
                championName = "Aatrox",
                role = "Baron",
                title = "Destructor Oscuro Inmortal",
                startingItem = "Espada Larga",
                bootsItem = "Botas Blindadas",
                bootsEnchant = "Gloria Justiciera",
                coreItems = listOf("Cuchilla Negra", "Baile de la Muerte", "Rompecascos", "Calibrador de Sterak", "Fuerza de la Naturaleza"),
                situationalItems = listOf("Malla de Espinas", "Rancor de Serylda"),
                keystoneRune = "Conquistador",
                secondaryRunes = listOf("Triunfo", "Coraza Ósea", "Perseverancia"),
                spell1 = "Destello",
                spell2 = "Prender",
                guideNotes = "Baron Lane: Domina el rango de los filos de H1 (La Espada Darkin). Usa H3 (Paso Sombrío) para ajustar la distancia de los impactos y mantener el sustain.\n\nTeamfight: Activa la Definitiva (H4 - Destructor de Mundos) en cuanto inicie el enfrentamiento para maximizar la curación y ganar velocidad de movimiento.",
                comboTips = "Combo Básico: H1 (primer impacto) -> H2 (Cadenas Infernales) -> H1 + H3 hacia adelante -> Básico pasivo -> H1 (tercer impacto central).",
                lastUpdated = System.currentTimeMillis() - 43200000L
            )
        ),
        CommunityCreatorItem(
            id = "creator_lee_sin",
            name = "InsecWR",
            tag = "Master Jungla",
            avatarId = "avatar_lee_sin",
            isLocalUser = false,
            subscribersCount = 76,
            subscribers = (1..76).map { "sub_dummy_$it" },
            build = CreatorChampionBuild(
                championId = "lee_sin",
                championName = "Lee Sin",
                role = "Jungla",
                title = "Presión de Jungla & Insec Perfecto",
                startingItem = "Cuchillo de Caza",
                bootsItem = "Botas Jonias de la Lucidez",
                bootsEnchant = "Protocinturón",
                coreItems = listOf("Guanto de Hielo", "Cuchilla Negra", "Danza de la Muerte", "Ángel custodio", "Maw of Malmortius"),
                situationalItems = listOf("Frontera de la Noche", "Rencor de Serylda"),
                keystoneRune = "Conquistador",
                secondaryRunes = listOf("Triunfo", "Golpe de Gracia", "Caminante Sobre el Agua"),
                spell1 = "Destello",
                spell2 = "Aplastar",
                guideNotes = "Ruta de Jungla Temprana: Rojo -> Lobos -> Azul -> Escarabajo del Río. Busca emboscadas a nivel 3 usando H2 (Salvaguarda) sobre un súbdito o aliado para cerrar distancia antes de lanzar H1 (Onda Sónica).\n\nControl de Heraldo y Dragones: Tu Aplastar combinado con la segunda activación de H1 garantiza el aseguramiento de objetivos frente a cualquier rival.",
                comboTips = "El Insec Definitivo en Wild Rift: H1 (acierto) -> H1 (viaje) -> H2 o Protocinturón tras la espalda del carry -> Definitiva (H4 - Furia del Dragón) pateándolo hacia tu equipo -> Destello si es necesario.",
                lastUpdated = System.currentTimeMillis() - 259200000L
            )
        ),
        CommunityCreatorItem(
            id = "creator_jinx_carry",
            name = "ZaunRocket",
            tag = "Challenger ADC",
            avatarId = "avatar_jinx",
            isLocalUser = false,
            subscribersCount = 68,
            subscribers = (1..68).map { "sub_dummy_$it" },
            build = CreatorChampionBuild(
                championId = "jinx",
                championName = "Jinx",
                role = "Duo",
                title = "DPS Crítico & Aceleración Pasiva",
                startingItem = "Espada Larga",
                bootsItem = "Grebas de Furia",
                bootsEnchant = "Fajín de Mercurio",
                coreItems = listOf("Fuerza de la Trinidad", "Filo del Infinito", "Huracán de Runaan", "Recordatorio Mortal", "La Sanguinaria"),
                situationalItems = listOf("Ángel custodio", "Cañón Magnético"),
                keystoneRune = "Compás Letal",
                secondaryRunes = listOf("Triunfo", "Linaje", "Golpe de Gracia"),
                spell1 = "Destello",
                spell2 = "Curar",
                guideNotes = "Fase de Líneas: Alterna H1 (¡Cambiazo!) entre la ametralladora Pium Pium para farmear a corta distancia y Carapescado (cohetes) para castigar el posicionamiento enemigo.\n\nTeamfights: Mantén un espaciado defensivo estricto. Cuando se active tu pasiva ¡A toda máquina!, arrasa con los cohetes desde rango seguro.",
                comboTips = "Combo Trampa: Lanza H3 (¡Mascafuegos!) detrás del objetivo inmovilizado por tu soporte -> H2 (¡Zap!) para ralentizar -> Disparo global de la Definitiva (H4 - ¡Supermegacohete Mortal!).",
                lastUpdated = System.currentTimeMillis() - 86400000L
            )
        )
    )

    fun getCommunityCreators(context: Context): List<CommunityCreatorItem> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedJson = prefs.getString(KEY_COMMUNITY_DATA, null)
        val baseList = if (!savedJson.isNullOrBlank()) {
            try {
                json.decodeFromString<List<CommunityCreatorItem>>(savedJson)
            } catch (e: Exception) {
                defaultCreators
            }
        } else {
            defaultCreators
        }

        // Incorporar a los usuarios locales que se hayan registrado como Creadores y creado su build de 1 campeón
        val localProfiles = AccountProfileManager.allProfiles.value
        val localCreators = localProfiles.filter { it.isCreator && it.creatorBuild != null }.map { prof ->
            CommunityCreatorItem(
                id = prof.id,
                name = prof.name,
                tag = if (prof.tag.isNotBlank()) prof.tag else "Creador",
                avatarId = prof.avatarId,
                isLocalUser = true,
                subscribersCount = prof.subscribersCount,
                subscribers = prof.subscribers,
                build = prof.creatorBuild!!
            )
        }

        // Combinar locales con los de la comunidad externa
        val combined = (localCreators + baseList.filter { base -> localCreators.none { it.id == base.id } })
        return combined
    }

    fun saveCommunityCreators(context: Context, list: List<CommunityCreatorItem>) {
        val externalOnly = list.filter { !it.isLocalUser }
        val encoded = json.encodeToString(externalOnly)
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_COMMUNITY_DATA, encoded)
            .apply()
    }

    /**
     * Suscripción con Esencia Azul a un creador.
     * Regla: Máximo 100 suscriptores por creador.
     */
    fun subscribeToCreator(
        context: Context,
        creatorId: String,
        subscriberProfileId: String,
        cost: Int = 100
    ): Pair<Boolean, String> {
        // Si el creador es un perfil local, lo canaliza por AccountProfileManager
        val localProfiles = AccountProfileManager.allProfiles.value
        if (localProfiles.any { it.id == creatorId }) {
            return AccountProfileManager.subscribeToUser(context, subscriberProfileId, creatorId, cost)
        }

        val all = getCommunityCreators(context).toMutableList()
        val index = all.indexOfFirst { it.id == creatorId }
        if (index == -1) return Pair(false, "Creador no encontrado.")

        val creator = all[index]
        if (creator.subscribersCount >= 100 || creator.subscribers.size >= 100) {
            return Pair(false, "¡Este creador ha alcanzado el límite máximo de 100 suscriptores!")
        }

        val activeProfile = AccountProfileManager.getActiveProfile(context)
        if (activeProfile.subscribedTo.contains(creatorId)) {
            return Pair(false, "Ya estás suscrito a este creador.")
        }

        if (activeProfile.blueEssence < cost) {
            return Pair(false, "No tienes suficiente Esencia Azul (requieres $cost EA).")
        }

        // Cobrar la esencia azul del suscriptor
        val deducted = AccountProfileManager.spendBlueEssence(context, subscriberProfileId, cost)
        if (!deducted) {
            return Pair(false, "Error al procesar la Esencia Azul.")
        }

        // Registrar suscripción en el perfil activo
        val updatedLocalProfiles = localProfiles.toMutableList()
        val pIndex = updatedLocalProfiles.indexOfFirst { it.id == subscriberProfileId }
        if (pIndex != -1) {
            val p = updatedLocalProfiles[pIndex]
            updatedLocalProfiles[pIndex] = p.copy(
                subscribedTo = (p.subscribedTo + creatorId).distinct()
            )
            AccountProfileManager.importProfiles(context, updatedLocalProfiles, subscriberProfileId)
        }

        // Actualizar el creador externo
        val updatedCreator = creator.copy(
            subscribersCount = (creator.subscribersCount + 1).coerceAtMost(100),
            subscribers = (creator.subscribers + subscriberProfileId).distinct().take(100)
        )
        all[index] = updatedCreator
        saveCommunityCreators(context, all)

        return Pair(true, "¡Te has suscrito exitosamente a ${creator.name} por $cost Esencias Azules!")
    }

    fun unsubscribeFromCreator(
        context: Context,
        creatorId: String,
        subscriberProfileId: String
    ): Pair<Boolean, String> {
        val localProfiles = AccountProfileManager.allProfiles.value
        if (localProfiles.any { it.id == creatorId }) {
            AccountProfileManager.unsubscribeFromUser(context, subscriberProfileId, creatorId)
            return Pair(true, "Suscripción cancelada.")
        }

        val all = getCommunityCreators(context).toMutableList()
        val index = all.indexOfFirst { it.id == creatorId }
        if (index != -1) {
            val creator = all[index]
            val updated = creator.copy(
                subscribersCount = (creator.subscribersCount - 1).coerceAtLeast(0),
                subscribers = creator.subscribers - subscriberProfileId
            )
            all[index] = updated
            saveCommunityCreators(context, all)
        }

        val updatedLocalProfiles = localProfiles.toMutableList()
        val pIndex = updatedLocalProfiles.indexOfFirst { it.id == subscriberProfileId }
        if (pIndex != -1) {
            val p = updatedLocalProfiles[pIndex]
            updatedLocalProfiles[pIndex] = p.copy(
                subscribedTo = p.subscribedTo - creatorId
            )
            AccountProfileManager.importProfiles(context, updatedLocalProfiles, subscriberProfileId)
        }

        return Pair(true, "Suscripción cancelada.")
    }
}
