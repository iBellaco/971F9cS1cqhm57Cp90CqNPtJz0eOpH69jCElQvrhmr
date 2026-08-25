import re

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'r', encoding='utf-8') as f:
    content = f.read()

helpers = """
    fun getItemByName(name: String): WildRiftItem? {
        return list.find { it.name.equals(name, ignoreCase = true) }
    }

    fun getItemIconByName(name: String): String? {
        return list.find { it.name.equals(name, ignoreCase = true) }?.iconUrl
    }
}
"""

content = re.sub(r'}\s*$', helpers, content)

with open('app/src/main/java/com/example/data/WildRiftItemsData.kt', 'w', encoding='utf-8') as f:
    f.write(content)

