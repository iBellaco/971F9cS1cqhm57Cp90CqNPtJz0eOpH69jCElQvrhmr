const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', 'utf8');

const themeUpdates = {
    "PILTOVER": {
        primary: "Color(0xFF0AC8B9)",
        primaryLight: "Color(0xFF00F2FE)",
        primaryDark: "Color(0xFF005A82)",
        primaryGlow: "Color(0xFF1D8CF8)",
        secondary: "Color(0xFFC8AA6E)",
        secondaryLight: "Color(0xFFF0E6D2)",
        secondaryDark: "Color(0xFF785A28)",
        secondaryGlow: "Color(0xFFFFD700)"
    },
    "AGUAS_ESTANCADAS": {
        primary: "Color(0xFFFF8F00)",
        primaryLight: "Color(0xFFFFB300)",
        primaryDark: "Color(0xFFB34700)",
        primaryGlow: "Color(0xFFFFD54F)",
        secondary: "Color(0xFF00B4D8)",
        secondaryLight: "Color(0xFF48CAE4)",
        secondaryDark: "Color(0xFF0077B6)",
        secondaryGlow: "Color(0xFF90E0EF)"
    },
    "CIUDAD_DE_BANDLE": {
        primary: "Color(0xFFB388FF)",
        primaryLight: "Color(0xFFD1B3FF)",
        primaryDark: "Color(0xFF5E2D91)",
        primaryGlow: "Color(0xFFE040FB)",
        secondary: "Color(0xFFF472B6)",
        secondaryLight: "Color(0xFFFBCFE8)",
        secondaryDark: "Color(0xFFBE185D)",
        secondaryGlow: "Color(0xFFF9A8D4)"
    },
    "DEMACIA": {
        primary: "Color(0xFF2563EB)",
        primaryLight: "Color(0xFF60A5FA)",
        primaryDark: "Color(0xFF1D4ED8)",
        primaryGlow: "Color(0xFF3B82F6)",
        secondary: "Color(0xFFFDE047)",
        secondaryLight: "Color(0xFFFEF08A)",
        secondaryDark: "Color(0xFFCA8A04)",
        secondaryGlow: "Color(0xFFFDF08A)"
    },
    "EL_VACIO": {
        primary: "Color(0xFFD946EF)",
        primaryLight: "Color(0xFFF0ABFC)",
        primaryDark: "Color(0xFFA21CAF)",
        primaryGlow: "Color(0xFFE879F9)",
        secondary: "Color(0xFF8B5CF6)",
        secondaryLight: "Color(0xFFC4B5FD)",
        secondaryDark: "Color(0xFF5B21B6)",
        secondaryGlow: "Color(0xFFA78BFA)"
    },
    "FRELJORD": {
        primary: "Color(0xFF00E5FF)",
        primaryLight: "Color(0xFF84FFFF)",
        primaryDark: "Color(0xFF00B8D4)",
        primaryGlow: "Color(0xFF18FFFF)",
        secondary: "Color(0xFF93C5FD)",
        secondaryLight: "Color(0xFFBFDBFE)",
        secondaryDark: "Color(0xFF2563EB)",
        secondaryGlow: "Color(0xFF60A5FA)"
    },
    "ISLAS_DE_LA_SOMBRA": {
        primary: "Color(0xFF00FFB3)",
        primaryLight: "Color(0xFF6EE7B7)",
        primaryDark: "Color(0xFF059669)",
        primaryGlow: "Color(0xFF34D399)",
        secondary: "Color(0xFF14B8A6)",
        secondaryLight: "Color(0xFF5EEAD4)",
        secondaryDark: "Color(0xFF0F766E)",
        secondaryGlow: "Color(0xFF2DD4BF)"
    },
    "IXTAL": {
        primary: "Color(0xFF10B981)",
        primaryLight: "Color(0xFF34D399)",
        primaryDark: "Color(0xFF047857)",
        primaryGlow: "Color(0xFF6EE7B7)",
        secondary: "Color(0xFFF59E0B)",
        secondaryLight: "Color(0xFFFCD34D)",
        secondaryDark: "Color(0xFFB45309)",
        secondaryGlow: "Color(0xFFFBBF24)"
    },
    "JONIA": {
        primary: "Color(0xFFF472B6)",
        primaryLight: "Color(0xFFFBCFE8)",
        primaryDark: "Color(0xFFBE185D)",
        primaryGlow: "Color(0xFFF9A8D4)",
        secondary: "Color(0xFF38BDF8)",
        secondaryLight: "Color(0xFFBAE6FD)",
        secondaryDark: "Color(0xFF0284C7)",
        secondaryGlow: "Color(0xFF7DD3FC)"
    },
    "NOXUS": {
        primary: "Color(0xFFEF4444)",
        primaryLight: "Color(0xFFF87171)",
        primaryDark: "Color(0xFF991B1B)",
        primaryGlow: "Color(0xFFFCA5A5)",
        secondary: "Color(0xFFF97316)",
        secondaryLight: "Color(0xFFFDBA74)",
        secondaryDark: "Color(0xFFC2410C)",
        secondaryGlow: "Color(0xFFFB923C)"
    },
    "SHURIMA": {
        primary: "Color(0xFFFFB300)",
        primaryLight: "Color(0xFFFFCA28)",
        primaryDark: "Color(0xFFFF8F00)",
        primaryGlow: "Color(0xFFFFD54F)",
        secondary: "Color(0xFF2DD4BF)",
        secondaryLight: "Color(0xFF99F6E4)",
        secondaryDark: "Color(0xFF0F766E)",
        secondaryGlow: "Color(0xFF5EEAD4)"
    },
    "TARGON": {
        primary: "Color(0xFF7C3AED)",
        primaryLight: "Color(0xFFA78BFA)",
        primaryDark: "Color(0xFF5B21B6)",
        primaryGlow: "Color(0xFF8B5CF6)",
        secondary: "Color(0xFFFBBF24)",
        secondaryLight: "Color(0xFFFDE68A)",
        secondaryDark: "Color(0xFFD97706)",
        secondaryGlow: "Color(0xFFFCD34D)"
    },
    "ZAUN": {
        primary: "Color(0xFF39FF14)",
        primaryLight: "Color(0xFF86EFAC)",
        primaryDark: "Color(0xFF166534)",
        primaryGlow: "Color(0xFF4ADE80)",
        secondary: "Color(0xFFEAB308)",
        secondaryLight: "Color(0xFFFEF08A)",
        secondaryDark: "Color(0xFFA16207)",
        secondaryGlow: "Color(0xFFFACC15)"
    }
};

for (const [themeId, colors] of Object.entries(themeUpdates)) {
    const regex = new RegExp(`(${themeId}\\([\\s\\S]*?primary = )Color\\([\\s\\S]*?\\),(?:\\s*primaryLight = )Color\\([\\s\\S]*?\\),(?:\\s*primaryDark = )Color\\([\\s\\S]*?\\),(?:\\s*primaryGlow = )Color\\([\\s\\S]*?\\),(?:\\s*secondary = )Color\\([\\s\\S]*?\\),(?:\\s*secondaryLight = )Color\\([\\s\\S]*?\\),(?:\\s*secondaryDark = )Color\\([\\s\\S]*?\\),(?:\\s*secondaryGlow = )Color\\([\\s\\S]*?\\),`);
    
    code = code.replace(regex, `$1${colors.primary},\n        primaryLight = ${colors.primaryLight},\n        primaryDark = ${colors.primaryDark},\n        primaryGlow = ${colors.primaryGlow},\n        secondary = ${colors.secondary},\n        secondaryLight = ${colors.secondaryLight},\n        secondaryDark = ${colors.secondaryDark},\n        secondaryGlow = ${colors.secondaryGlow},`);
}

fs.writeFileSync('app/src/main/java/com/example/ui/theme/AppThemeManager.kt', code);
console.log("Updated theme colors successfully");
