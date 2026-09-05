import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

state_class = """class OverlayState {
    var isExpanded by androidx.compose.runtime.mutableStateOf(false)
    var overlayHubTab by androidx.compose.runtime.mutableStateOf(OverlayHubTab.DRAFT)
    var showSaveDraftDialog by androidx.compose.runtime.mutableStateOf(false)
    var showRoleChangeDialog by androidx.compose.runtime.mutableStateOf(false)
    var isSavedRecently by androidx.compose.runtime.mutableStateOf(false)
    var activeRole by androidx.compose.runtime.mutableStateOf(LaneRole.MID)
    var isFirstPick by androidx.compose.runtime.mutableStateOf(false)
    var isCompactBubble by androidx.compose.runtime.mutableStateOf(false)
    var isScanning by androidx.compose.runtime.mutableStateOf(false)
    var autoScanEnabled by androidx.compose.runtime.mutableStateOf(false)
    var scanNoticeMessage by androidx.compose.runtime.mutableStateOf<String?>(null)
    var isDraggingBubble by androidx.compose.runtime.mutableStateOf(false)
    var dragAccumulatedY by androidx.compose.runtime.mutableFloatStateOf(0f)
    var isNearCloseThreshold by androidx.compose.runtime.mutableStateOf(false)
    var selectedChampionDetail by androidx.compose.runtime.mutableStateOf<com.example.model.Champion?>(null)
    var showChampionPickerForSlot by androidx.compose.runtime.mutableStateOf<Pair<Boolean, Int>?>(null)
    var isLoadingScreenMode by androidx.compose.runtime.mutableStateOf(false)
    var isOverlayTabsMinimized by androidx.compose.runtime.mutableStateOf(false)
    val allies = androidx.compose.runtime.mutableStateListOf<com.example.model.Champion?>().apply { repeat(5) { add(null) } }
    val enemies = androidx.compose.runtime.mutableStateListOf<com.example.model.Champion?>().apply { repeat(5) { add(null) } }
}

"""

# Add state_class before FloatingOverlayContent
text = text.replace("@Composable\nprivate fun FloatingOverlayContent", state_class + "@Composable\nprivate fun FloatingOverlayContent")

# Update FloatingOverlayContent signature
text = text.replace(
"""private fun FloatingOverlayContent(
    isLandscapeMode: Boolean,
    screenCaptureManager: ScreenCaptureManager?,
    onClose: () -> Unit,
    onDragDelta: (dx: Int, dy: Int, isDragging: Boolean, isEnded: Boolean) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onCompactModeChange: (Boolean) -> Unit
) {""",
"""private fun FloatingOverlayContent(
    state: OverlayState,
    isLandscapeMode: Boolean,
    screenCaptureManager: ScreenCaptureManager?,
    onClose: () -> Unit,
    onDragDelta: (dx: Int, dy: Int, isDragging: Boolean, isEnded: Boolean) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onCompactModeChange: (Boolean) -> Unit
) {""")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
