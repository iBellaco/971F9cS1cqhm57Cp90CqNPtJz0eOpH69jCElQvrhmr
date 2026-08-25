import re

with open('app/src/main/java/com/example/MainActivity.kt', 'r', encoding='utf-8') as f:
    content = f.read()

# Replace Surface with BlurredMeshBackground
surface_code = r'''Surface\(
                    modifier = Modifier\.fillMaxSize\(\),
                    color = HextechDarkBg
                \) \{'''

new_surface_code = r'''com.example.ui.components.BlurredMeshBackground(
                    modifier = Modifier.fillMaxSize()
                ) {'''

content = re.sub(surface_code, new_surface_code, content)

# Make Scaffold background transparent
scaffold_code = r'''Scaffold\('''
new_scaffold_code = r'''Scaffold(
        containerColor = androidx.compose.ui.graphics.Color.Transparent,'''
content = re.sub(scaffold_code, new_scaffold_code, content)

with open('app/src/main/java/com/example/MainActivity.kt', 'w', encoding='utf-8') as f:
    f.write(content)
