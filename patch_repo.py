import re

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'r') as f:
    content = f.read()

# Current: 
#     var champions: List<Champion> by mutableStateOf(
#         ...
#     ).distinctBy { it.id })
#
# Fix it to:
#     var champions: List<Champion> by mutableStateOf((
#         ...
#     ).distinctBy { it.id })

content = content.replace("    var champions: List<Champion> by mutableStateOf(\n        com.example.data.champions.BaronLaneChampions.list", "    var champions: List<Champion> by mutableStateOf((\n        com.example.data.champions.BaronLaneChampions.list")

with open('app/src/main/java/com/example/data/WildRiftRepository.kt', 'w') as f:
    f.write(content)
