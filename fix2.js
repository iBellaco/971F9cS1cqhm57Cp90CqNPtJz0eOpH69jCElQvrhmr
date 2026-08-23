const fs = require('fs');
let ktCode = fs.readFileSync('WildRiftItemsData_new.kt', 'utf-8');
ktCode = ktCode.replace(/}\n}\n$/, `
    }

    fun getItemIconByName(name: String): String {
        val clean = name.trim()
        if (clean.isEmpty()) return ""
        val exact = list.find { it.name.equals(clean, ignoreCase = true) }
        if (exact != null && exact.iconUrl.isNotBlank()) return exact.iconUrl
        val partial = list.find { it.name.contains(clean, ignoreCase = true) || clean.contains(it.name, ignoreCase = true) }
        return partial?.iconUrl ?: ""
    }

    fun getItemByName(name: String): WildRiftItem? {
        val clean = name.trim()
        if (clean.isEmpty()) return null
        return list.find { 
            it.name.equals(clean, ignoreCase = true) || 
            it.name.contains(clean, ignoreCase = true) || 
            clean.contains(it.name, ignoreCase = true) 
        }
    }
}
`);
fs.writeFileSync('app/src/main/java/com/example/data/WildRiftItemsData.kt', ktCode);
