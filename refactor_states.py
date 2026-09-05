import re

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

# Replace the remember definitions with state delegates
replacements = [
    (r"var isExpanded by remember { mutableStateOf\(false\) }", r"var isExpanded by state::isExpanded"),
    (r"var overlayHubTab by remember { mutableStateOf\(OverlayHubTab\.DRAFT\) }", r"var overlayHubTab by state::overlayHubTab"),
    (r"var showSaveDraftDialog by remember { mutableStateOf\(false\) }", r"var showSaveDraftDialog by state::showSaveDraftDialog"),
    (r"var showRoleChangeDialog by remember { mutableStateOf\(false\) }", r"var showRoleChangeDialog by state::showRoleChangeDialog"),
    (r"var isSavedRecently by remember { mutableStateOf\(false\) }", r"var isSavedRecently by state::isSavedRecently"),
    (r"var activeRole by remember { mutableStateOf\(com\.example\.util\.UserPreferences\.getActiveDraftRole\(context\)\) }", r"var activeRole by state::activeRole"),
    (r"var isFirstPick by remember { mutableStateOf\(false\) }", r"var isFirstPick by state::isFirstPick"),
    (r"var isCompactBubble by remember { mutableStateOf\(false\) }", r"var isCompactBubble by state::isCompactBubble"),
    (r"var isScanning by remember { mutableStateOf\(false\) }", r"var isScanning by state::isScanning"),
    (r"var autoScanEnabled by remember { mutableStateOf\(false\) }", r"var autoScanEnabled by state::autoScanEnabled"),
    (r"var scanNoticeMessage by remember { mutableStateOf<String\?>\(null\) }", r"var scanNoticeMessage by state::scanNoticeMessage"),
    (r"var isDraggingBubble by remember { mutableStateOf\(false\) }", r"var isDraggingBubble by state::isDraggingBubble"),
    (r"var dragAccumulatedY by remember { mutableFloatStateOf\(0f\) }", r"var dragAccumulatedY by state::dragAccumulatedY"),
    (r"var isNearCloseThreshold by remember { mutableStateOf\(false\) }", r"var isNearCloseThreshold by state::isNearCloseThreshold"),
    (r"var selectedChampionDetail by remember { mutableStateOf<Champion\?>\(null\) }", r"var selectedChampionDetail by state::selectedChampionDetail"),
    (r"var showChampionPickerForSlot by remember { mutableStateOf<Pair<Boolean, Int>\?>\(null\) } // Pair\(isAlly, slotIndex\)", r"var showChampionPickerForSlot by state::showChampionPickerForSlot"),
    (r"var isLoadingScreenMode by remember { mutableStateOf\(false\) }", r"var isLoadingScreenMode by state::isLoadingScreenMode"),
    (r"var isOverlayTabsMinimized by remember { mutableStateOf\(false\) }", r"var isOverlayTabsMinimized by state::isOverlayTabsMinimized"),
    (r"val allies = remember { mutableStateListOf<Champion\?>\(\)\.apply { repeat\(5\) { add\(null\) } } }", r"val allies = state.allies"),
    (r"val enemies = remember { mutableStateListOf<Champion\?>\(\)\.apply { repeat\(5\) { add\(null\) } } }", r"val enemies = state.enemies")
]

for old, new in replacements:
    text = re.sub(old, new, text)

# Now inject the overlayState instance into the service and pass it
text = text.replace(
"""class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
""",
"""class FloatingAssistantService : Service(), LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    private val overlayState = OverlayState()
""")

text = text.replace(
"""            setContent {
                FloatingOverlayContent(
                    isLandscapeMode = isDeviceLandscape.value,
                    screenCaptureManager = screenCaptureManager,""",
"""            setContent {
                FloatingOverlayContent(
                    state = overlayState,
                    isLandscapeMode = isDeviceLandscape.value,
                    screenCaptureManager = screenCaptureManager,""")

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)

