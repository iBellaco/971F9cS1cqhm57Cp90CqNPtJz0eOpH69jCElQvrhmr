import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    content = f.read()

target_signature = """@Composable
private fun FloatingOverlayContent(
    onClose: () -> Unit,
    onDragDelta: (Int, Int) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {"""

replacement_signature = """@Composable
private fun FloatingOverlayContent(
    screenCaptureManager: ScreenCaptureManager?,
    onClose: () -> Unit,
    onDragDelta: (Int, Int) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {"""

content = content.replace(target_signature, replacement_signature)

target_lists = """    val allies = remember {
        listOfNotNull(
            WildRiftRepository.getChampionById("vayne"),
            WildRiftRepository.getChampionById("janna"),
            WildRiftRepository.getChampionById("viego")
        )
    }

    val enemies = remember {
        listOfNotNull(
            WildRiftRepository.getChampionById("sett"),
            WildRiftRepository.getChampionById("vi"),
            WildRiftRepository.getChampionById("caitlyn")
        )
    }"""

replacement_lists = """    val allies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }
    val enemies = remember { androidx.compose.runtime.mutableStateListOf<Champion>() }"""

content = content.replace(target_lists, replacement_lists)


target_call = """                        FloatingOverlayContent(
                            onClose = { stopSelf() },
                            onDragDelta = { dx, dy ->"""

replacement_call = """                        FloatingOverlayContent(
                            screenCaptureManager = screenCaptureManager,
                            onClose = { stopSelf() },
                            onDragDelta = { dx, dy ->"""

content = content.replace(target_call, replacement_call)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(content)
