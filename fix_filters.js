const fs = require('fs');
let content = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

const newFilterCode = `    val filterOptions = listOf(
        "Todos",
        "Jonia",
        "Zaun / Piltóver",
        "Demacia / Noxus",
        "Freljord / Shurima",
        "Runaterra / Islas",
        "Mascotas / Yordles"
    )

    val filteredAvatars = remember(selectedFilter) {
        when (selectedFilter) {
            "Jonia" -> AvatarCatalog.avatars.filter { it.region.equals("Jonia", ignoreCase = true) }
            "Zaun / Piltóver" -> AvatarCatalog.avatars.filter {
                it.region.contains("Zaun", ignoreCase = true) || it.region.contains("Piltóver", ignoreCase = true)
            }
            "Demacia / Noxus" -> AvatarCatalog.avatars.filter {
                it.region.contains("Demacia", ignoreCase = true) || it.region.contains("Noxus", ignoreCase = true)
            }
            "Freljord / Shurima" -> AvatarCatalog.avatars.filter {
                it.region.contains("Freljord", ignoreCase = true) || it.region.contains("Shurima", ignoreCase = true)
            }
            "Runaterra / Islas" -> AvatarCatalog.avatars.filter {
                it.region.contains("Runaterra", ignoreCase = true) || it.region.contains("Islas", ignoreCase = true) || it.region.contains("Targon", ignoreCase = true) || it.region.contains("Aguas", ignoreCase = true) || it.region.contains("Vacío", ignoreCase = true) || it.region.contains("Oscuros", ignoreCase = true)
            }
            "Mascotas / Yordles" -> AvatarCatalog.avatars.filter {
                it.region.contains("Mascotas", ignoreCase = true) || it.region.contains("Bandle", ignoreCase = true) || it.isDefault
            }
            else -> AvatarCatalog.avatars
        }
    }`;

// Replace everything from `val filterOptions = listOf(` up to `else -> AvatarCatalog.avatars\n        }\n    }`
content = content.replace(/val filterOptions = listOf\([\s\S]*?else -> AvatarCatalog\.avatars\s*\}\s*\}/, newFilterCode);
fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', content);
console.log("Replaced filter options");
