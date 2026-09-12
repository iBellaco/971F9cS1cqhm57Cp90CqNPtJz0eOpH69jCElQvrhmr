import re

with open('.github/workflows/build-apk.yml', 'r') as f:
    content = f.read()

bad_syntax = """          gh release create "$TAG_NAME" "${{ steps.prepare_asset.outputs.asset_path }}" 
            --title "$RELEASE_NAME" 
            --target "${{ github.sha }}" 
            --notes "### 🎮 Wild Rift Coach - APK de Pruebas (Debug)"""

good_syntax = """          gh release create "$TAG_NAME" "${{ steps.prepare_asset.outputs.asset_path }}" \\
            --title "$RELEASE_NAME" \\
            --target "${{ github.sha }}" \\
            --notes "### 🎮 Wild Rift Coach - APK de Pruebas (Debug)"""

content = content.replace(bad_syntax, good_syntax)

with open('.github/workflows/build-apk.yml', 'w') as f:
    f.write(content)
