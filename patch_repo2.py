import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

# Add a var to store the error
error_var = """    var lastError: String? by mutableStateOf(null)
"""

content = content.replace("    var champions: List<Champion> by mutableStateOf(emptyList())", "    var champions: List<Champion> by mutableStateOf(emptyList())\n" + error_var)

old_catch = """        } catch (e: Exception) {
            android.util.Log.e("WildRiftRepository", "Failed to load champions", e)
        }"""

new_catch = """        } catch (e: Exception) {
            lastError = e.stackTraceToString()
            android.util.Log.e("WildRiftRepository", "Failed to load champions", e)
        }"""

content = content.replace(old_catch, new_catch)

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
