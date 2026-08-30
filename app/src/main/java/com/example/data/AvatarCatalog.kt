package com.example.data

import com.example.model.AvatarItem

object AvatarCatalog {

    val DEFAULT_AVATAR = AvatarItem(
        id = "default_poro",
        name = "Poro Guardián",
        title = "Espíritu de la Grieta",
        region = "Freljord",
        rarity = "Clásico",
        imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/588.png",
        borderHex = "#C8AA6E",
        description = "El leal compañero de todo Invocador de Wild Rift.",
        isDefault = true
    )

    val avatars: List<AvatarItem> = listOf(
        DEFAULT_AVATAR,
        AvatarItem(
            id = "jinx",
            name = "Jinx Caótica",
            title = "El Gatillo Suelto",
            region = "Zaun",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Jinx.png",
            borderHex = "#EC4899",
            description = "Especialista en desatar fuegos artificiales y caos absoluto."
        ),
        AvatarItem(
            id = "yasuo",
            name = "Yasuo Viento de Honor",
            title = "La Espada Sin Honor",
            region = "Jonia",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Yasuo.png",
            borderHex = "#0AC8B9",
            description = "Domina el acero y las corrientes de aire jonias."
        ),
        AvatarItem(
            id = "ahri",
            name = "Ahri Espíritu Floral",
            title = "La Doncella de Nueve Colas",
            region = "Jonia",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Ahri.png",
            borderHex = "#F43F5E",
            description = "Encanto irresistible y magia espiritual ancestral."
        ),
        AvatarItem(
            id = "zed",
            name = "Zed Sombra Mortal",
            title = "El Maestro de las Sombras",
            region = "Jonia",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Zed.png",
            borderHex = "#EF4444",
            description = "La oscuridad prohibida ejecutada con precisión quirúrgica."
        ),
        AvatarItem(
            id = "akali",
            name = "Akali K/DA Neón",
            title = "La Asesina Furtiva",
            region = "Jonia",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Akali.png",
            borderHex = "#A855F7",
            description = "Letal en las sombras y brillante bajo las luces de neón."
        ),
        AvatarItem(
            id = "leesin",
            name = "Lee Sin Dragón Divino",
            title = "El Monje Ciego",
            region = "Jonia",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/LeeSin.png",
            borderHex = "#EAB308",
            description = "Disciplina marcial y poder ancestral del dragón."
        ),
        AvatarItem(
            id = "lux",
            name = "Lux Luz Cósmica",
            title = "La Dama Luminosa",
            region = "Demacia",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Lux.png",
            borderHex = "#38BDF8",
            description = "La radiante chispa de esperanza para Demacia."
        ),
        AvatarItem(
            id = "garen",
            name = "Garen Justicia Suprema",
            title = "El Poder de Demacia",
            region = "Demacia",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Garen.png",
            borderHex = "#C8AA6E",
            description = "Espada y escudo inquebrantables al frente de la vanguardia."
        ),
        AvatarItem(
            id = "darius",
            name = "Darius Rey del Duelo",
            title = "La Mano de Noxus",
            region = "Noxus",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Darius.png",
            borderHex = "#DC2626",
            description = "Fuerza implacable y guillotina noxiana definitiva."
        ),
        AvatarItem(
            id = "katarina",
            name = "Katarina Daga Siniestra",
            title = "La Cuchilla Siniestra",
            region = "Noxus",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Katarina.png",
            borderHex = "#E11D48",
            description = "Danza letal entre flores de dagas y velocidad pura."
        ),
        AvatarItem(
            id = "thresh",
            name = "Thresh Carcelero Espectral",
            title = "El Carcelero Implacable",
            region = "Islas de la Sombra",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Thresh.png",
            borderHex = "#10B981",
            description = "Guardián de la linterna y recolector eterno de almas."
        ),
        AvatarItem(
            id = "viego",
            name = "Viego Rey Arruinado",
            title = "El Rey Arruinado",
            region = "Islas de la Sombra",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Viego.png",
            borderHex = "#06B6D4",
            description = "Gobernante de la Niebla Negra y conquistador de cuerpos."
        ),
        AvatarItem(
            id = "aatrox",
            name = "Aatrox Devastador Oscuro",
            title = "La Espada de los Oscuros",
            region = "Los Oscuros",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Aatrox.png",
            borderHex = "#991B1B",
            description = "El fin de los dioses y el despertar del coloso alado."
        ),
        AvatarItem(
            id = "teemo",
            name = "Teemo Diablo Sonriente",
            title = "El Explorador Veloz",
            region = "Ciudad de Bandle",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Teemo.png",
            borderHex = "#84CC16",
            description = "El terror camuflado de setas venenosas de la jungla."
        ),
        AvatarItem(
            id = "kaisa",
            name = "Kai'Sa Hija del Vacío",
            title = "Hija del Vacío",
            region = "El Vacío",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Kaisa.png",
            borderHex = "#8B5CF6",
            description = "Segunda piel simbiótica y proyectiles de plasma vivo."
        ),
        AvatarItem(
            id = "yone",
            name = "Yone Cazador Espiritual",
            title = "El Cazador Imperecedero",
            region = "Jonia",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Yone.png",
            borderHex = "#6366F1",
            description = "Portador de espadas duales entre el reino humano y espiritual."
        ),
        AvatarItem(
            id = "jhin",
            name = "Jhin Virtuoso del Cuatro",
            title = "El Virtuoso",
            region = "Jonia",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Jhin.png",
            borderHex = "#D97706",
            description = "Cada disparo es una obra de arte y una sinfonía fatal."
        ),
        AvatarItem(
            id = "ekko",
            name = "Ekko Cronoquiebre",
            title = "El Joven que Rompió el Tiempo",
            region = "Zaun",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Ekko.png",
            borderHex = "#14B8A6",
            description = "Controlador del Z-Drive para rebobinar y ganar cualquier batalla."
        ),
        AvatarItem(
            id = "vi",
            name = "Vi Guanteletes Hextech",
            title = "La Defensora de Piltóver",
            region = "Piltóver",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Vi.png",
            borderHex = "#F472B6",
            description = "Golpea primero, pregunta mientras caen."
        ),
        AvatarItem(
            id = "caitlyn",
            name = "Caitlyn Ojo de Piltóver",
            title = "La Sheriff de Piltóver",
            region = "Piltóver",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Caitlyn.png",
            borderHex = "#60A5FA",
            description = "Francotiradora implacable con visión perfecta a larga distancia."
        ),
        AvatarItem(
            id = "ashe",
            name = "Ashe Hielo Puro",
            title = "La Arquera de Hielo",
            region = "Freljord",
            rarity = "Clásico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Ashe.png",
            borderHex = "#38BDF8",
            description = "Matriarca unificadora del norte con flechas encantadas."
        ),
        AvatarItem(
            id = "samira",
            name = "Samira Rosa Letal",
            title = "La Rosa del Desierto",
            region = "Noxus",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Samira.png",
            borderHex = "#FB923C",
            description = "Calificación Estilo S constante con espadas y pistolas."
        ),
        AvatarItem(
            id = "volibear",
            name = "Volibear Señor del Trueno",
            title = "La Tormenta Implacable",
            region = "Freljord",
            rarity = "Legendario",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/champion/Volibear.png",
            borderHex = "#0284C7",
            description = "Semidiós primitivo que invoca rayos destructores."
        ),
        AvatarItem(
            id = "baron_nashor",
            name = "Barón Nashor Supremo",
            title = "Monstruo de la Grieta",
            region = "Grieta del Invocador",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/685.png",
            borderHex = "#9333EA",
            description = "El gobernante supremo del foso neutral en Wild Rift."
        ),
        AvatarItem(
            id = "elder_dragon",
            name = "Dragón Anciano Ancestral",
            title = "Poder de Ejecución",
            region = "Grieta del Invocador",
            rarity = "Mítico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/684.png",
            borderHex = "#E11D48",
            description = "Otorga la bendición final de quemadura y ejecución masiva."
        ),
        AvatarItem(
            id = "arcade_poro",
            name = "Poro Arcade Retro",
            title = "Héroe de 8-Bits",
            region = "Eventos Arcade",
            rarity = "Épico",
            imageUrl = "https://ddragon.leagueoflegends.com/cdn/14.20.1/img/profileicon/1381.png",
            borderHex = "#A855F7",
            description = "Un poro gamer listo para alcanzar la puntuación más alta."
        )
    )

    fun getAvatarById(id: String): AvatarItem {
        return avatars.find { it.id.equals(id, ignoreCase = true) } ?: DEFAULT_AVATAR
    }
}
