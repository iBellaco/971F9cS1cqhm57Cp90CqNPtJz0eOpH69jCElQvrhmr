import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    content = f.read()

content = content.replace(
'''fun MetaAndDraftScreen(
    mode: MetaScreenMode = MetaScreenMode.CATALOG,
    userMainRole: LaneRole,
    onNavigateBack: () -> Unit
) {''',
'''fun MetaAndDraftScreen(
    mode: MetaScreenMode = MetaScreenMode.CATALOG,
    userMainRole: LaneRole,
    initialChampionId: String? = null,
    onNavigateBack: () -> Unit
) {'''
)

bad_str = "    var selectedDetailChampion by remember { mutableStateOf<Champion?>(null) }"
good_str = """    var selectedDetailChampion by remember { mutableStateOf<Champion?>(
        initialChampionId?.let { id -> WildRiftRepository.getChampionById(id) }
    ) }"""

content = content.replace(bad_str, good_str)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(content)
