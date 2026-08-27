import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

old_code = """        try {
            val format = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            val parsed = context.assets.open("champions.json.gz").use { inputStream ->
                java.util.zip.GZIPInputStream(inputStream).bufferedReader().use { reader ->
                    format.decodeFromString<List<Champion>>(reader.readText())
                }
            }"""

new_code = """        try {
            val format = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            val parsed = context.resources.openRawResource(com.example.R.raw.champions).bufferedReader().use { reader ->
                format.decodeFromString<List<Champion>>(reader.readText())
            }"""

content = content.replace(old_code, new_code)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
