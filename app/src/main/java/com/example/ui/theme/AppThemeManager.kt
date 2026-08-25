package com.example.ui.theme

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

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
    HEXTECH(
        id = "HEXTECH",
        titleKey = "Hextech Piltover",
        regionTag = "Piltover",
        descKey = "Estilo oficial Hextech dorado y cian sobre fondo oscuro profundo.",
        background = Color(0xFF000000),
        surface = Color(0xFF0D131A),
        surfaceVariant = Color(0xFF141F2B),
        cardBorder = Color(0xFF1E3048),
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
    NOXUS(
        id = "NOXUS",
        titleKey = "Noxus Carmesí",
        regionTag = "Noxus",
        descKey = "Fuerza militar implacable con tonos rojo sangre, acero y oro bélico.",
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
    ZAUN(
        id = "ZAUN",
        titleKey = "Zaun Quimtech",
        regionTag = "Zaun",
        descKey = "Energía radioactiva ácida con verde neón, vapores y cian industrial.",
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
    ),
    TARGON(
        id = "TARGON",
        titleKey = "Targon Luz Estelar",
        regionTag = "Monte Targon",
        descKey = "Misticismo celestial cósmico con violeta astral y destellos dorados.",
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
    SHADOW_ISLES(
        id = "SHADOW_ISLES",
        titleKey = "Islas de la Sombra",
        regionTag = "Niebla Negra",
        descKey = "Espectros ancestrales con jade oscuro espectral y verde fantasma.",
        background = Color(0xFF010A0A),
        surface = Color(0xFF051717),
        surfaceVariant = Color(0xFF092626),
        cardBorder = Color(0xFF134242),
        primary = Color(0xFF3B82F6),
        primaryLight = Color(0xFF70F8E6),
        primaryDark = Color(0xFF004D40),
        primaryGlow = Color(0xFF00E5FF),
        secondary = Color(0xFF70E000),
        secondaryLight = Color(0xFFA7F844),
        secondaryDark = Color(0xFF387000),
        secondaryGlow = Color(0xFF9EF01A),
        textPrimary = Color(0xFFE0FAF6),
        textSecondary = Color(0xFF76A8A5),
        textMuted = Color(0xFF497371),
        isDark = true
    ),
    SHURIMA(
        id = "SHURIMA",
        titleKey = "Shurima Despertada",
        regionTag = "Shurima",
        descKey = "El poder del disco solar con oro imperial, ámbar ardiente y arenas.",
        background = Color(0xFF0C0700),
        surface = Color(0xFF1F1300),
        surfaceVariant = Color(0xFF332002),
        cardBorder = Color(0xFF5C3C08),
        primary = Color(0xFFF43F5E),
        primaryLight = Color(0xFFFFD166),
        primaryDark = Color(0xFF8A5A00),
        primaryGlow = Color(0xFFFFC300),
        secondary = Color(0xFFFB8500),
        secondaryLight = Color(0xFFF43F5E),
        secondaryDark = Color(0xFF9E4700),
        secondaryGlow = Color(0xFFFF7B00),
        textPrimary = Color(0xFFFFF3DB),
        textSecondary = Color(0xFFD4B483),
        textMuted = Color(0xFF9E8055),
        isDark = true
    ),
    SPIRIT_BLOSSOM(
        id = "SPIRIT_BLOSSOM",
        titleKey = "Flor Espiritual",
        regionTag = "Jonia",
        descKey = "Armonía espiritual con pétalos rosa sakura, lavanda y noche mística.",
        background = Color(0xFF0C0712),
        surface = Color(0xFF1B1026),
        surfaceVariant = Color(0xFF2E1B3E),
        cardBorder = Color(0xFF53316E),
        primary = Color(0xFFFF85A1),
        primaryLight = Color(0xFFFFB3C6),
        primaryDark = Color(0xFF800F3F),
        primaryGlow = Color(0xFFFF4D80),
        secondary = Color(0xFFC77DFF),
        secondaryLight = Color(0xFFE0AAFF),
        secondaryDark = Color(0xFF5A189A),
        secondaryGlow = Color(0xFF9D4EDD),
        textPrimary = Color(0xFFFCEFF9),
        textSecondary = Color(0xFFBFA2C9),
        textMuted = Color(0xFF876A91),
        isDark = true
    ),
    KDA(
        id = "KDA",
        titleKey = "K/DA Cyber Pop",
        regionTag = "K/DA All Out",
        descKey = "Glamour pop futurista con magenta neón, cian eléctrico y brillo.",
        background = Color(0xFF07000E),
        surface = Color(0xFF160126),
        surfaceVariant = Color(0xFF27053E),
        cardBorder = Color(0xFF4C0F73),
        primary = Color(0xFFFF007F),
        primaryLight = Color(0xFFFF66B2),
        primaryDark = Color(0xFF6B0038),
        primaryGlow = Color(0xFFFF1493),
        secondary = Color(0xFF00F0FF),
        secondaryLight = Color(0xFF80F7FF),
        secondaryDark = Color(0xFF006B73),
        secondaryGlow = Color(0xFF00FFFF),
        textPrimary = Color(0xFFFAEAFF),
        textSecondary = Color(0xFFC595DB),
        textMuted = Color(0xFF875B9C),
        isDark = true
    ),
    LIGHT_HEXTECH(
        id = "LIGHT_HEXTECH",
        titleKey = "Hextech Día (Claro)",
        regionTag = "Modo Claro",
        descKey = "Interfaz diurna de alto contraste con blanco puro y acentos dorados.",
        background = Color(0xFFF1F5F9),
        surface = Color(0xFFFFFFFF),
        surfaceVariant = Color(0xFFE2E8F0),
        cardBorder = Color(0xFFCBD5E1),
        primary = Color(0xFF0284C7),
        primaryLight = Color(0xFF0369A1),
        primaryDark = Color(0xFF075985),
        primaryGlow = Color(0xFF38BDF8),
        secondary = Color(0xFFB45309),
        secondaryLight = Color(0xFFD97706),
        secondaryDark = Color(0xFF78350F),
        secondaryGlow = Color(0xFFF59E0B),
        textPrimary = Color(0xFF0F172A),
        textSecondary = Color(0xFF334155),
        textMuted = Color(0xFF64748B),
        isDark = false
    )
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
        descKey = "Se adapta automáticamente a la paleta activa",
        colorHex = Color(0xFF818CF8),
        containerColor = Color.Unspecified,
        accentColor = Color.Unspecified,
        isAutomatic = true
    ),
    HEXTECH_GOLD(
        id = "HEXTECH_GOLD",
        titleKey = "Hextech Dorado",
        descKey = "Elegante oro piltover",
        colorHex = Color(0xFFC8AA6E),
        containerColor = Color(0xFF140F05),
        accentColor = Color(0xFFC8AA6E),
        isAutomatic = false
    ),
    HEXTECH_CYAN(
        id = "HEXTECH_CYAN",
        titleKey = "Hextech Cian",
        descKey = "Resplandor cian arcano dedicado",
        colorHex = Color(0xFF00E5FF),
        containerColor = Color(0xFF021B1C),
        accentColor = Color(0xFF00E5FF),
        isAutomatic = false
    ),
    NOXUS_CRIMSON(
        id = "NOXUS_CRIMSON",
        titleKey = "Noxus Carmesí",
        descKey = "Rojo carmesí bélico",
        colorHex = Color(0xFFFF2A42),
        containerColor = Color(0xFF1F0408),
        accentColor = Color(0xFFFF2A42),
        isAutomatic = false
    ),
    ZAUN_GREEN(
        id = "ZAUN_GREEN",
        titleKey = "Zaun Neón",
        descKey = "Verde tóxico radioactivo",
        colorHex = Color(0xFF00FF7F),
        containerColor = Color(0xFF041C0F),
        accentColor = Color(0xFF00FF7F),
        isAutomatic = false
    ),
    TARGON_PURPLE(
        id = "TARGON_PURPLE",
        titleKey = "Targon Cósmico",
        descKey = "Púrpura estelar astral",
        colorHex = Color(0xFFB388FF),
        containerColor = Color(0xFF150A2E),
        accentColor = Color(0xFFB388FF),
        isAutomatic = false
    ),
    FRELJORD_BLUE(
        id = "FRELJORD_BLUE",
        titleKey = "Azul Freljord",
        descKey = "Hielo puro cristalino",
        colorHex = Color(0xFF3B82F6),
        containerColor = Color(0xFF0F172A),
        accentColor = Color(0xFF3B82F6),
        isAutomatic = false
    ),
    IXTAL_MAGENTA(
        id = "IXTAL_MAGENTA",
        titleKey = "Magenta Ixtal",
        descKey = "Rosa salvaje selvático",
        colorHex = Color(0xFFF43F5E),
        containerColor = Color(0xFF1C050F),
        accentColor = Color(0xFFF43F5E),
        isAutomatic = false
    ),
    SPIRIT_PINK(
        id = "SPIRIT_PINK",
        titleKey = "Flor Rosa",
        descKey = "Pétalos rosa sakura",
        colorHex = Color(0xFFFF85A1),
        containerColor = Color(0xFF240E1B),
        accentColor = Color(0xFFFF85A1),
        isAutomatic = false
    ),
    KDA_MAGENTA(
        id = "KDA_MAGENTA",
        titleKey = "K/DA Magenta",
        descKey = "Magenta neón pop",
        colorHex = Color(0xFFFF007F),
        containerColor = Color(0xFF24001A),
        accentColor = Color(0xFFFF007F),
        isAutomatic = false
    ),
    DEEP_NAVY(
        id = "DEEP_NAVY",
        titleKey = "Azul Abisal",
        descKey = "Azul marino profundo",
        colorHex = Color(0xFF38BDF8),
        containerColor = Color(0xFF03142B),
        accentColor = Color(0xFF38BDF8),
        isAutomatic = false
    ),
    CARBON_GRAY(
        id = "CARBON_GRAY",
        titleKey = "Gris Carbón",
        descKey = "Gris industrial mate",
        colorHex = Color(0xFF64748B),
        containerColor = Color(0xFF1E2124),
        accentColor = Color(0xFFCBD5E1),
        isAutomatic = false
    )
}

object AppThemeManager {
    private const val PREFS_KEY_THEME = "selected_app_theme_id"
    private const val PREFS_KEY_NAV_BAR = "selected_app_navbar_id"

    var currentTheme by mutableStateOf(AppTheme.HEXTECH)
        private set

    var currentNavBarOption by mutableStateOf(NavBarColorOption.THEME_AUTO)
        private set

    fun init(context: Context) {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val savedThemeId = prefs.getString(PREFS_KEY_THEME, AppTheme.HEXTECH.id) ?: AppTheme.HEXTECH.id
        val savedNavId = prefs.getString(PREFS_KEY_NAV_BAR, NavBarColorOption.THEME_AUTO.id) ?: NavBarColorOption.THEME_AUTO.id

        currentTheme = AppTheme.entries.find { it.id == savedThemeId } ?: AppTheme.HEXTECH
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
        if (currentTheme == AppTheme.LIGHT_HEXTECH) {
            setTheme(AppTheme.HEXTECH, context)
        } else {
            setTheme(AppTheme.LIGHT_HEXTECH, context)
        }
    }

    fun getNavBarBackgroundColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.surface.copy(alpha = 0.65f)
        } else {
            currentNavBarOption.containerColor
        }
    }

    fun getNavBarAccentColor(): Color {
        return if (currentNavBarOption.isAutomatic) {
            currentTheme.primary
        } else {
            currentNavBarOption.accentColor
        }
    }

    fun getNavBarIndicatorColor(): Color {
        val accent = getNavBarAccentColor()
        return accent.copy(alpha = 0.22f)
    }

    fun getNavBarSelectedIconColor(): Color {
        return getNavBarAccentColor()
    }

    fun getNavBarSelectedTextColor(): Color {
        val bg = getNavBarBackgroundColor()
        val isBgDark = (0.299f * bg.red + 0.587f * bg.green + 0.114f * bg.blue) < 0.5f
        val accent = getNavBarAccentColor()
        return if (isBgDark) {
            accent
        } else {
            val accentLum = 0.299f * accent.red + 0.587f * accent.green + 0.114f * accent.blue
            if (accentLum > 0.5f) Color(0xFF0369A1) else accent
        }
    }

    fun getNavBarUnselectedColor(): Color {
        val bg = getNavBarBackgroundColor()
        val isBgDark = (0.299f * bg.red + 0.587f * bg.green + 0.114f * bg.blue) < 0.5f
        return if (isBgDark) Color(0xFF94A3B8) else Color(0xFF475569)
    }
}
