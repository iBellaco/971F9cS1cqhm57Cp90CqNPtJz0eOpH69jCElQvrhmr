import re

file_path = "app/src/main/java/com/example/model/Champion.kt"
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()

if "MetaDataSource" not in content:
    content += """
data class MetaDataSource(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val focusArea: String,
    val badge: String = "Sincronizado"
)
"""
    with open(file_path, "w", encoding="utf-8") as f:
        f.write(content)

file_path2 = "app/src/main/java/com/example/data/WildRiftRepository.kt"
with open(file_path2, "r", encoding="utf-8") as f:
    content2 = f.read()

if "import com.example.model.MetaDataSource" not in content2:
    content2 = content2.replace("import com.example.model.WildRiftItem", "import com.example.model.WildRiftItem\nimport com.example.model.MetaDataSource")

if "val metaSources" not in content2:
    # Insert it before summonerSpells
    metaSources = """

    // ==========================================
    // FUENTES DE DATOS Y META ACTUAL
    // ==========================================
    val metaSources: List<MetaDataSource> = listOf(
        MetaDataSource(
            id = "riot_games_oficial",
            name = "Wild Rift Oficial (Riot Games)",
            description = "Catálogo Oficial de Campeones y Habilidades",
            url = "https://wildrift.leagueoflegends.com/es-es/champions/",
            focusArea = "Datos Canónicos y Oficiales"
        ),
        MetaDataSource(
            id = "wildriftcore",
            name = "WildRiftCore (ES)",
            description = "Runas en Español, Parches y Novedades",
            url = "https://wildriftcore.com/es/",
            focusArea = "Runas y Novedades en Español"
        ),
        MetaDataSource(
            id = "bestbuildwr",
            name = "BestBuildWR",
            description = "Builds Óptimas e Ítems Situacionales",
            url = "https://bestbuildwr.com/",
            focusArea = "Armado de Objetos Profundo"
        ),
        MetaDataSource(
            id = "wildriftfire",
            name = "WildRiftFire",
            description = "Tier Lists Globales y Sinergias",
            url = "https://www.wildriftfire.com/",
            focusArea = "Tier List General (S+ a C)"
        ),
        MetaDataSource(
            id = "wr_meta",
            name = "WR-Meta",
            description = "Estadísticas en Tiempo Real y Counters",
            url = "https://wr-meta.com/",
            focusArea = "Winrates y Counters Dinámicos"
        )
    )

"""
    content2 = content2.replace("    // CATÁLOGO DE HECHIZOS DE INVOCADOR", metaSources + "    // CATÁLOGO DE HECHIZOS DE INVOCADOR")

with open(file_path2, "w", encoding="utf-8") as f:
    f.write(content2)

print("Reverted MetaDataSource in Champion.kt and WildRiftRepository.kt")
