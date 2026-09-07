import sys
with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "r") as f:
    lines = f.readlines()

new_lines = []
for line in lines:
    if "try {" in line and "windowManager?.addView(closeTargetComposeView" in "".join(lines[lines.index(line):lines.index(line)+3]):
        new_lines.extend([
            "        val debugParams = WindowManager.LayoutParams(\n",
            "            WindowManager.LayoutParams.MATCH_PARENT,\n",
            "            WindowManager.LayoutParams.MATCH_PARENT,\n",
            "            layoutType,\n",
            "            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,\n",
            "            PixelFormat.TRANSLUCENT\n",
            "        ).apply {\n",
            "            gravity = Gravity.TOP or Gravity.START\n",
            "        }\n",
            "\n",
            "        debugBoxesComposeView = ComposeView(this).apply {\n",
            "            setViewTreeLifecycleOwner(this@FloatingAssistantService)\n",
            "            setViewTreeViewModelStoreOwner(this@FloatingAssistantService)\n",
            "            setViewTreeSavedStateRegistryOwner(this@FloatingAssistantService)\n",
            "            setContent {\n",
            "                val showDebug by showVisionDebugger.collectAsState()\n",
            "                if (showDebug) {\n",
            "                    VisionDebugOverlay()\n",
            "                }\n",
            "            }\n",
            "        }\n",
            line
        ])
    else:
        new_lines.append(line)

with open("app/src/main/java/com/example/service/FloatingAssistantService.kt", "w") as f:
    f.writelines(new_lines)

