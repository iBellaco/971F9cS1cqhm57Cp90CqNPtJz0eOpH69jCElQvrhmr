import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    content = f.read()

target = """        debugBoxesComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setContent {"""

replacement = """        debugBoxesComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnDetachedFromWindow)
            setContent {"""

content = content.replace(target, replacement)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.write(content)
