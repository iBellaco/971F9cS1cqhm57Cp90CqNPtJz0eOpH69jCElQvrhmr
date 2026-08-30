package com.example.ui.theme

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * Temas oficiales de League of Legends basados en las regiones del Universo de Runaterra
 * (https://universe.leagueoflegends.com/es_ES/regions/)
 *
 * Piltover es el tema por defecto.
 */
enum class AppTheme(
    val id: String,
    val titleKey: String,
    val regionTag: String,
    val descKey: String,
    val background: Color,
    val surface: Color,
    val surfaceVariant: Color,
    val cardBorder: Color,
    val primary: Color,
    val primaryLight: Color,
    val primaryDark: Color,
    val primaryGlow: Color,
    val secondary: Color,
    val secondaryLight: Color,
    val secondaryDark: Color,
    val secondaryGlow: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val isDark: Boolean
) {
    // 1. Piltover (Por defecto) - La Ciudad del Progreso: Oro Hextech, azul zafiro, blanco mármol y cian arcano
    PILTOVER(
        id = "PILTOVER",
        titleKey = "Piltover",
        regionTag = "Ciudad del Progreso",
        descKey = "La vanguardia del progreso, tecnología Hextech, oro pulido, zafiro brillante y cian arcano.",
        background = Color(0xFF040A14),
        surface = Color(0xFF0A1626),
        surfaceVariant = Color(0xFF10223B),
        cardBorder = Color(0xFF1F3D64),
        primary = Color(0xFF0AC8B9),
        primaryLight = Color(0xFF00F2FE),
        primaryDark = Color(0xFF005A82),
        primaryGlow = Color(0xFF1D8CF8),
        secondary = Color(0xFFC8AA6E),
        secondaryLight = Color(0xFFF0E6D2),
        secondaryDark = Color(0xFF785A28),
        secondaryGlow = Color(0xFFFFD700),
        textPrimary = Color(0xFFF0E6D2),
        textSecondary = Color(0xFF94A3B8),
        textMuted = Color(0xFF64748B),
        isDark = true
    ),

    // 2. Aguas Estancadas (Bilgewater) - Puerto sin ley: Mares profundos, maderas náuticas, cobre, oro pirata y turquesa
    AGUAS_ESTANCADAS(
        id = "AGUAS_ESTANCADAS",
        titleKey = "Aguas Estancadas",
        regionTag = "Puerto de Forajidos",
        descKey = "Bahía de piratas, cazadores de monstruos marinos, hierro salado, oro de botín y marea oscura.",
        background = Color(0xFF040E14),
        surface = Color(0xFF0B1F28),
        surfaceVariant = Color(0xFF13323E),
        cardBorder = Color(0xFF1D4D5F),
        primary = Color(0xFF00B4D8),
        primaryLight = Color(0xFF48CAE4),
        primaryDark = Color(0xFF0077B6),
        primaryGlow = Color(0xFF90E0EF),
        secondary = Color(0xFFD4A373),
        secondaryLight = Color(0xFFFAEDCD),
        secondaryDark = Color(0xFF8C531B),
        secondaryGlow = Color(0xFFE76F51),
        textPrimary = Color(0xFFE8F1F5),
        textSecondary = Color(0xFF8EAFBC),
        textMuted = Color(0xFF537888),
        isDark = true
    ),

    // 3. Ciudad de Bandle - Reino Yordle místico: Magia feérica, lavanda, destellos dorados y esmeralda brillante
    CIUDAD_DE_BANDLE(
        id = "CIUDAD_DE_BANDLE",
        titleKey = "Ciudad de Bandle",
        regionTag = "Hogar de los Yordles",
        descKey = "Reino feérico atemporal repleto de colores mágicos, lavanda encantada, oro solar y hojas brillantes.",
        background = Color(0xFF0F0619),
        surface = Color(0xFF201035),
        surfaceVariant = Color(0xFF321A50),
        cardBorder = Color(0xFF5A2F8E),
        primary = Color(0xFFB388FF),
        primaryLight = Color(0xFFD1B3FF),
        primaryDark = Color(0xFF5E2D91),
        primaryGlow = Color(0xFFE040FB),
        secondary = Color(0xFFFFD166),
        secondaryLight = Color(0xFFFFF0B3),
        secondaryDark = Color(0xFFB38600),
        secondaryGlow = Color(0xFF06D6A0),
        textPrimary = Color(0xFFF9F5FF),
        textSecondary = Color(0xFFC3AED6),
        textMuted = Color(0xFF8870A0),
        isDark = true
    ),

    // 4. Demacia - Reino de Honor y Petricita: Petricita marfil, oro regio noble, azul real y estandartes heroicos
    DEMACIA(
        id = "DEMACIA",
        titleKey = "Demacia",
        regionTag = "Reino de la Justicia",
        descKey = "Petricita resplandeciente, honor marfil, azul real inquebrantable y oro de las alas protectoras.",
        background = Color(0xFF050B14),
        surface = Color(0xFF0D1C30),
        surfaceVariant = Color(0xFF152A47),
        cardBorder = Color(0xFF2C4E7E),
        primary = Color(0xFF38BDF8),
        primaryLight = Color(0xFF7DD3FC),
        primaryDark = Color(0xFF0369A1),
        primaryGlow = Color(0xFF60A5FA),
        secondary = Color(0xFFE0C068),
        secondaryLight = Color(0xFFFBF4D8),
        secondaryDark = Color(0xFF9E7E24),
        secondaryGlow = Color(0xFFFFDF73),
        textPrimary = Color(0xFFF4F7FB),
        textSecondary = Color(0xFFA1B4CC),
        textMuted = Color(0xFF68809F),
        isDark = true
    ),

    // 5. El Vacío (The Void) - Dimensión corruptora: Púrpura cósmico abisal, violeta sobrenatural, magenta y oscuridad viva
    EL_VACIO(
        id = "EL_VACIO",
        titleKey = "El Vacío",
        regionTag = "La Nada Hambrienta",
        descKey = "Terror cósmico insaciable, púrpura bioluminiscente, magenta alienígena y oscuridad corruptora.",
        background = Color(0xFF08010E),
        surface = Color(0xFF160424),
        surfaceVariant = Color(0xFF26083C),
        cardBorder = Color(0xFF541285),
        primary = Color(0xFFD946EF),
        primaryLight = Color(0xFFF0ABFC),
        primaryDark = Color(0xFF701A75),
        primaryGlow = Color(0xFFC026D3),
        secondary = Color(0xFF8B5CF6),
        secondaryLight = Color(0xFFC4B5FD),
        secondaryDark = Color(0xFF5B21B6),
        secondaryGlow = Color(0xFFA855F7),
        textPrimary = Color(0xFFFCE7F3),
        textSecondary = Color(0xFFC084FC),
        textMuted = Color(0xFF7E22CE),
        isDark = true
    ),

    // 6. Freljord - Tierras del Hielo Puro: Hielo oscuro, cian glacial congelado, escarcha milenaria y runas árticas
    FRELJORD(
        id = "FRELJORD",
        titleKey = "Freljord",
        regionTag = "Tierras del Norte",
        descKey = "Hielo puro ancestral, escarcha ártica implacable, azul glacial y templanza de los Hijos del Hielo.",
        background = Color(0xFF030914),
        surface = Color(0xFF071829),
        surfaceVariant = Color(0xFF0E2741),
        cardBorder = Color(0xFF1A4670),
        primary = Color(0xFF00E5FF),
        primaryLight = Color(0xFF80F2FF),
        primaryDark = Color(0xFF0284C7),
        primaryGlow = Color(0xFF38BDF8),
        secondary = Color(0xFF93C5FD),
        secondaryLight = Color(0xFFEFF6FF),
        secondaryDark = Color(0xFF1E40AF),
        secondaryGlow = Color(0xFF60A5FA),
        textPrimary = Color(0xFFF0F9FF),
        textSecondary = Color(0xFF93C5FD),
        textMuted = Color(0xFF4B6B94),
        isDark = true
    ),

    // 7. Islas de la Sombra - Niebla Negra y Almas Malditas: Jade espectral, verde fantasma, niebla y piedras de ruina
    ISLAS_DE_LA_SOMBRA(
        id = "ISLAS_DE_LA_SOMBRA",
        titleKey = "Islas de la Sombra",
        regionTag = "Niebla Negra",
        descKey = "Tierras consumidas por la Ruina, jade espectral fulgurante, almas atormentadas y frío de ultratumba.",
        background = Color(0xFF010A0A),
        surface = Color(0xFF041818),
        surfaceVariant = Color(0xFF082626),
        cardBorder = Color(0xFF114848),
        primary = Color(0xFF00FFC2),
        primaryLight = Color(0xFF66FFE0),
        primaryDark = Color(0xFF005E46),
        primaryGlow = Color(0xFF00E676),
        secondary = Color(0xFF2DD4BF),
        secondaryLight = Color(0xFFCCFBF1),
        secondaryDark = Color(0xFF0F766E),
        secondaryGlow = Color(0xFF14B8A6),
        textPrimary = Color(0xFFE6FFFA),
        textSecondary = Color(0xFF5EEAD4),
        textMuted = Color(0xFF115E59),
        isDark = true
    ),

    // 8. Ixtal - Selva Elemental Ancestral: Verde esmeralda vivo, magia axioma, oro de la jungla y naturaleza primigenia
    IXTAL(
        id = "IXTAL",
        titleKey = "Ixtal",
        regionTag = "Magia Elemental",
        descKey = "Dominio de los axiomas elementales, selva densa esmeralda, orquídeas salvajes y bronce arcano.",
        background = Color(0xFF020C06),
        surface = Color(0xFF071F11),
        surfaceVariant = Color(0xFF0F331D),
        cardBorder = Color(0xFF195732),
        primary = Color(0xFF10B981),
        primaryLight = Color(0xFF34D399),
        primaryDark = Color(0xFF047857),
        primaryGlow = Color(0xFF059669),
        secondary = Color(0xFFEAB308),
        secondaryLight = Color(0xFFFEF08A),
        secondaryDark = Color(0xFFA16207),
        secondaryGlow = Color(0xFFF59E0B),
        textPrimary = Color(0xFFECFDF5),
        textSecondary = Color(0xFFA7F3D0),
        textMuted = Color(0xFF4B8B67),
        isDark = true
    ),

    // 9. Jonia - Las Tierras Primigenias: Flor de loto rosa, follaje espiritual verde suave, armonía y magia viva
    JONIA(
        id = "JONIA",
        titleKey = "Jonia",
        regionTag = "Tierras Primigenias",
        descKey = "Equilibrio supremo de la naturaleza, flores de loto sagradas, magia espiritual y cerezos en flor.",
        background = Color(0xFF0C050D),
        surface = Color(0xFF1C0E20),
        surfaceVariant = Color(0xFF2C1632),
        cardBorder = Color(0xFF52285E),
        primary = Color(0xFFF472B6),
        primaryLight = Color(0xFFFBCFE8),
        primaryDark = Color(0xFF9D174D),
        primaryGlow = Color(0xFFEC4899),
        secondary = Color(0xFF2DD4BF),
        secondaryLight = Color(0xFF99F6E4),
        secondaryDark = Color(0xFF0F766E),
        secondaryGlow = Color(0xFF14B8A6),
        textPrimary = Color(0xFFFDF2F8),
        textSecondary = Color(0xFFF472B6),
        textMuted = Color(0xFF834E79),
        isDark = true
    ),

    // 10. Noxus - Imperio Conquistador: Rojo carmesí de guerra, hierro forjado oscuro, acero y oro bélico
    NOXUS(
        id = "NOXUS",
        titleKey = "Noxus",
        regionTag = "Imperio de la Fuerza",
        descKey = "Fuerza militar implacable, rojo sangre de conquista, acero forjado y triunfo de los fuertes.",
        background = Color(0xFF0B0204),
        surface = Color(0xFF19060A),
        surfaceVariant = Color(0xFF26090F),
        cardBorder = Color(0xFF4A121A),
        primary = Color(0xFFFF2A42),
        primaryLight = Color(0xFFFF6B7D),
        primaryDark = Color(0xFF6B0E1B),
        primaryGlow = Color(0xFFFF0022),
        secondary = Color(0xFFE2B755),
        secondaryLight = Color(0xFFFCE7B2),
        secondaryDark = Color(0xFF8A6517),
        secondaryGlow = Color(0xFFFFC72C),
        textPrimary = Color(0xFFFCE7E9),
        textSecondary = Color(0xFFC48B92),
        textMuted = Color(0xFF8A5C63),
        isDark = true
    ),

    // 11. Shurima - El Imperio del Desierto: Oro del Disco Solar, arenas ardientes, ámbar imperial y oasis eterno
    SHURIMA(
        id = "SHURIMA",
        titleKey = "Shurima",
        regionTag = "Imperio del Disco Solar",
        descKey = "El gran Disco Solar de los Ascendidos, arenas doradas, ámbar ardiente y gloria renacida.",
        background = Color(0xFF0E0701),
        surface = Color(0xFF211303),
        surfaceVariant = Color(0xFF351F06),
        cardBorder = Color(0xFF613B0E),
        primary = Color(0xFFFFB703),
        primaryLight = Color(0xFFFFD166),
        primaryDark = Color(0xFFB07D02),
        primaryGlow = Color(0xFFFB8500),
        secondary = Color(0xFFFB8500),
        secondaryLight = Color(0xFFFFB703),
        secondaryDark = Color(0xFF8C3E00),
        secondaryGlow = Color(0xFFF43F5E),
        textPrimary = Color(0xFFFFF7ED),
        textSecondary = Color(0xFFFDBA74),
        textMuted = Color(0xFF8A6538),
        isDark = true
    ),

    // 12. Targon - La Cumbre Celestial: Solari dorado, Lunari plateado místico, violeta cósmico y polvo de estrellas
    TARGON(
        id = "TARGON",
        titleKey = "Targon",
        regionTag = "Cumbre Celestial",
        descKey = "Morada de los Aspectos Celestiales, violeta estelar profundo, plata lunar y fuego solar divino.",
        background = Color(0xFF060312),
        surface = Color(0xFF120C29),
        surfaceVariant = Color(0xFF1F1545),
        cardBorder = Color(0xFF3B2875),
        primary = Color(0xFFB388FF),
        primaryLight = Color(0xFFD1B3FF),
        primaryDark = Color(0xFF4A148C),
        primaryGlow = Color(0xFF7C4DFF),
        secondary = Color(0xFFFFD54F),
        secondaryLight = Color(0xFFFFF176),
        secondaryDark = Color(0xFFFF8F00),
        secondaryGlow = Color(0xFFFFE082),
        textPrimary = Color(0xFFF3E5F5),
        textSecondary = Color(0xFFA594C9),
        textMuted = Color(0xFF6E5D91),
        isDark = true
    ),

    // 13. Zaun - Ciudad Subterránea Quimtech: Verde neón radioactivo, vapores tóxicos, tuberías de cobre y brillo Shimmer
    ZAUN(
        id = "ZAUN",
        titleKey = "Zaun",
        regionTag = "Subciudad Quimtech",
        descKey = "Ingenio desenfrenado en las profundidades, verde ácido químico, humo Zaun y resplandor Shimmer.",
        background = Color(0xFF020B06),
        surface = Color(0xFF061A0F),
        surfaceVariant = Color(0xFF0B2B19),
        cardBorder = Color(0xFF144D2B),
        primary = Color(0xFF00FF7F),
        primaryLight = Color(0xFF66FFB2),
        primaryDark = Color(0xFF006633),
        primaryGlow = Color(0xFF39FF14),
        secondary = Color(0xFF00E5FF),
        secondaryLight = Color(0xFF80F2FF),
        secondaryDark = Color(0xFF006680),
        secondaryGlow = Color(0xFF00FFFF),
        textPrimary = Color(0xFFE8FDF0),
        textSecondary = Color(0xFF7EBF96),
        textMuted = Color(0xFF4D8A65),
        isDark = true
    );

    companion object {
        // Compatibilidad hacia atrás si un usuario guardó HEXTECH en SharedPrefs
        fun fromId(id: String?): AppTheme {
            if (id == null) return PILTOVER
            if (id == "HEXTECH") return PILTOVER
            return entries.find { it.id.equals(id, ignoreCase = true) } ?: PILTOVER
        }
    }
}

enum class NavBarColorOption(
    val id: String,
    val titleKey: String,
    val descKey: String,
    val colorHex: Color,
    val containerColor: Color,
    val accentColor: Color,
    val isAutomatic: Boolean
) {
    THEME_AUTO(
        id = "THEME_AUTO",
        titleKey = "Automático (Sincronizado con Tema)",
        descKey = "Se adapta automáticamente a la región activa",
        colorHex = Color(0xFF818CF8),
        containerColor = Color.Unspecified,
        accentColor = Color.Unspecified,
        isAutomatic = true
    ),
    PILTOVER_GOLD(
        id = "PILTOVER_GOLD",
        titleKey = "Piltover Oro",
        descKey = "Elegante oro Hextech refinado",
        colorHex = Color(0xFFC8AA6E),
        containerColor = Color(0xFF0A1626),
        accentColor = Color(0xFFC8AA6E),
        isAutomatic = false
    ),
    PILTOVER_CYAN(
        id = "PILTOVER_CYAN",
        titleKey = "Piltover Cian",
        descKey = "Resplandor cian arcano de cristal",
        colorHex = Color(0xFF0AC8B9),
        containerColor = Color(0xFF04121A),
        accentColor = Color(0xFF0AC8B9),
        isAutomatic = false
    ),
    BILGEWATER_TEAL(
        id = "BILGEWATER_TEAL",
        titleKey = "Aguas Estancadas",
        descKey = "Turquesa náutico y marea profunda",
        colorHex = Color(0xFF00B4D8),
        containerColor = Color(0xFF0B1F28),
        accentColor = Color(0xFF00B4D8),
        isAutomatic = false
    ),
    BANDLE_LAVENDER(
        id = "BANDLE_LAVENDER",
        titleKey = "Bandle Mágico",
        descKey = "Lavanda y magia feérica yordle",
        colorHex = Color(0xFFB388FF),
        containerColor = Color(0xFF201035),
        accentColor = Color(0xFFB388FF),
        isAutomatic = false
    ),
    DEMACIA_ROYAL(
        id = "DEMACIA_ROYAL",
        titleKey = "Demacia Real",
        descKey = "Azul petricita y nobleza",
        colorHex = Color(0xFF38BDF8),
        containerColor = Color(0xFF0D1C30),
        accentColor = Color(0xFFE0C068),
        isAutomatic = false
    ),
    VOID_MAGENTA(
        id = "VOID_MAGENTA",
        titleKey = "El Vacío",
        descKey = "Púrpura y magenta alienígena",
        colorHex = Color(0xFFD946EF),
        containerColor = Color(0xFF160424),
        accentColor = Color(0xFFD946EF),
        isAutomatic = false
    ),
    FRELJORD_GLACIAL(
        id = "FRELJORD_GLACIAL",
        titleKey = "Freljord Glacial",
        descKey = "Hielo puro y azul escarcha",
        colorHex = Color(0xFF00E5FF),
        containerColor = Color(0xFF071829),
        accentColor = Color(0xFF00E5FF),
        isAutomatic = false
    ),
    SHADOW_ISLES_JADE(
        id = "SHADOW_ISLES_JADE",
        titleKey = "Islas de la Sombra",
        descKey = "Jade espectral y niebla negra",
        colorHex = Color(0xFF00FFC2),
        containerColor = Color(0xFF041818),
        accentColor = Color(0xFF00FFC2),
        isAutomatic = false
    ),
    IXTAL_EMERALD(
        id = "IXTAL_EMERALD",
        titleKey = "Ixtal Selva",
        descKey = "Esmeralda elemental primigenia",
        colorHex = Color(0xFF10B981),
        containerColor = Color(0xFF071F11),
        accentColor = Color(0xFF10B981),
        isAutomatic = false
    ),
    IONIA_BLOSSOM(
        id = "IONIA_BLOSSOM",
        titleKey = "Jonia Espiritual",
        descKey = "Rosa flor de loto y cerezo",
        colorHex = Color(0xFFF472B6),
        containerColor = Color(0xFF1C0E20),
        accentColor = Color(0xFFF472B6),
        isAutomatic = false
    ),
    NOXUS_CRIMSON(
        id = "NOXUS_CRIMSON",
        titleKey = "Noxus Carmesí",
        descKey = "Rojo sangre y acero bélico",
        colorHex = Color(0xFFFF2A42),
        containerColor = Color(0xFF19060A),
        accentColor = Color(0xFFFF2A42),
        isAutomatic = false
    ),
    SHURIMA_GOLD(
        id = "SHURIMA_GOLD",
        titleKey = "Shurima Solar",
        descKey = "Oro imperial y fuego del desierto",
        colorHex = Color(0xFFFFB703),
        containerColor = Color(0xFF211303),
        accentColor = Color(0xFFFFB703),
        isAutomatic = false
    ),
    TARGON_COSMIC(
        id = "TARGON_COSMIC",
        titleKey = "Targon Cósmico",
        descKey = "Púrpura estelar y oro divino",
        colorHex = Color(0xFFB388FF),
        containerColor = Color(0xFF120C29),
        accentColor = Color(0xFFFFD54F),
        isAutomatic = false
    ),
    ZAUN_CHEMTECH(
        id = "ZAUN_CHEMTECH",
        titleKey = "Zaun Quimtech",
        descKey = "Verde tóxico y cian industrial",
        colorHex = Color(0xFF00FF7F),
        containerColor = Color(0xFF061A0F),
        accentColor = Color(0xFF00FF7F),
        isAutomatic = false
    )
}

object AppThemeManager {
    private const val PREFS_KEY_THEME = "selected_app_theme_id"
    private const val PREFS_KEY_NAV_BAR = "selected_app_navbar_id"

    var currentTheme by mutableStateOf(AppTheme.PILTOVER)
        private set

    var currentNavBarOption by mutableStateOf(NavBarColorOption.THEME_AUTO)
        private set

    fun init(context: Context) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val savedThemeId = prefs.getString(PREFS_KEY_THEME, AppTheme.PILTOVER.id) ?: AppTheme.PILTOVER.id
        val savedNavId = prefs.getString(PREFS_KEY_NAV_BAR, NavBarColorOption.THEME_AUTO.id) ?: NavBarColorOption.THEME_AUTO.id

        currentTheme = AppTheme.fromId(savedThemeId)
        currentNavBarOption = NavBarColorOption.entries.find { it.id == savedNavId } ?: NavBarColorOption.THEME_AUTO
    }

    fun setTheme(theme: AppTheme, context: Context? = null) {
        currentTheme = theme
        context?.let {
            val prefs = it.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.edit().putString(PREFS_KEY_THEME, theme.id).apply()
        }
    }

    fun setNavBarOption(option: NavBarColorOption, context: Context? = null) {
        currentNavBarOption = option
        context?.let {
            val prefs = it.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.edit().putString(PREFS_KEY_NAV_BAR, option.id).apply()
        }
    }

    fun toggleLightDark(context: Context? = null) {
        // No-op ya que todos los temas son oscuros inmersivos
    }

    fun getNavBarBackgroundColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.surface.copy(alpha = 0.85f)
        } else {
            currentNavBarOption.containerColor
        }
    }

    fun getNavBarAccentColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.secondary
        } else {
            currentNavBarOption.accentColor
        }
    }

    fun getNavBarIndicatorColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.primary.copy(alpha = 0.22f)
        } else {
            currentNavBarOption.colorHex.copy(alpha = 0.22f)
        }
    }

    fun getNavBarSelectedIconColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.primary
        } else {
            currentNavBarOption.colorHex
        }
    }

    fun getNavBarSelectedTextColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.secondary
        } else {
            currentNavBarOption.accentColor
        }
    }

    fun getNavBarUnselectedColor(): Color {
        return currentTheme.textMuted.copy(alpha = 0.85f)
    }
}
