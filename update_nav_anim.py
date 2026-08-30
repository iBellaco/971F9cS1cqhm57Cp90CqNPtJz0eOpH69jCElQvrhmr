with open('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'r') as f:
    text = f.read()

target = """                var previewSelectedTab by remember { mutableIntStateOf(0) }
                val navBgColor = AppThemeManager.getNavBarBackgroundColor()
                val navAccentColor = AppThemeManager.getNavBarAccentColor()
                val navIconColor = AppThemeManager.getNavBarSelectedIconColor()
                val navUnselectedColor = AppThemeManager.getNavBarUnselectedColor()"""

replacement = """                var previewSelectedTab by remember { mutableIntStateOf(0) }
                val navBgColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarBackgroundColor(),
                    animationSpec = tween(350),
                    label = "navBgColorAnim"
                )
                val navAccentColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarAccentColor(),
                    animationSpec = tween(350),
                    label = "navAccentColorAnim"
                )
                val navIconColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarSelectedIconColor(),
                    animationSpec = tween(350),
                    label = "navIconColorAnim"
                )
                val navUnselectedColor by animateColorAsState(
                    targetValue = AppThemeManager.getNavBarUnselectedColor(),
                    animationSpec = tween(350),
                    label = "navUnselectedColorAnim"
                )"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/components/ThemeCustomizationDialog.kt', 'w') as f:
    f.write(text)

print("Updated ThemeCustomizationDialog with smooth animated transitions")
