const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'utf8');

const PREF_VAR_DECLARATION = `    var isOledMode by mutableStateOf(false)
        private set`;

const PREF_VAR_NEW_DECLARATION = `    var isOledMode by mutableStateOf(false)
        private set

    var isParticlesEnabled by mutableStateOf(true)
        private set`;

code = code.replace(PREF_VAR_DECLARATION, PREF_VAR_NEW_DECLARATION);

const INIT_CODE = `        val savedOled = prefs.getBoolean(PREFS_KEY_OLED_MODE, false)

        currentTheme = AppTheme.fromId(savedThemeId)
        currentNavBarOption = NavBarColorOption.entries.find { it.id == savedNavId } ?: NavBarColorOption.THEME_AUTO
        isOledMode = savedOled`;

const INIT_CODE_NEW = `        val savedOled = prefs.getBoolean(PREFS_KEY_OLED_MODE, false)
        val savedParticles = prefs.getBoolean("particles_enabled", true)

        currentTheme = AppTheme.fromId(savedThemeId)
        currentNavBarOption = NavBarColorOption.entries.find { it.id == savedNavId } ?: NavBarColorOption.THEME_AUTO
        isOledMode = savedOled
        isParticlesEnabled = savedParticles`;

code = code.replace(INIT_CODE, INIT_CODE_NEW);

const SET_OLED_CODE = `    fun setOledMode(enabled: Boolean, context: Context? = null) {
        isOledMode = enabled
        context?.let {
            val prefs = it.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean(PREFS_KEY_OLED_MODE, enabled).apply()
        }
    }`;

const SET_OLED_CODE_NEW = `    fun setOledMode(enabled: Boolean, context: Context? = null) {
        isOledMode = enabled
        context?.let {
            val prefs = it.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean(PREFS_KEY_OLED_MODE, enabled).apply()
        }
    }
    
    fun setParticlesEnabled(enabled: Boolean, context: Context? = null) {
        isParticlesEnabled = enabled
        context?.let {
            val prefs = it.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("particles_enabled", enabled).apply()
        }
    }`;

code = code.replace(SET_OLED_CODE, SET_OLED_CODE_NEW);
fs.writeFileSync('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', code);
console.log("AppThemeManager updated");
