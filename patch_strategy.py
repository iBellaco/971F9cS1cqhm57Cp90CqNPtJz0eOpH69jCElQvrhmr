import re
with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'r') as f:
    text = f.read()

replacement = """        floatingComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@FloatingAssistantService))"""

text = text.replace("""        floatingComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)""", replacement)

replacement2 = """        closeTargetComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)
            setViewCompositionStrategy(androidx.compose.ui.platform.ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@FloatingAssistantService))"""

text = text.replace("""        closeTargetComposeView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@FloatingAssistantService)
            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)
            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)""", replacement2)

with open('app/src/main/java/com/example/service/FloatingAssistantService.kt', 'w') as f:
    f.write(text)
