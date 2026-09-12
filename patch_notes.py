import re

with open('.github/workflows/build-apk.yml', 'r') as f:
    content = f.read()

old_release = """          # Create new release with the APK attached
          gh release create "$TAG_NAME" "${{ steps.prepare_asset.outputs.asset_path }}" \\
             --title "$RELEASE_NAME" \\
             --target "${{ github.sha }}" \\
             --notes "### 🎮 Wild Rift Coach - APK de Pruebas (Debug)
          - **Versión de la app:** v${{ steps.app_version.outputs.version_name }}
          - **Código de versión:** ${{ steps.app_version.outputs.version_code }}
          - **Ejecución GitHub Actions:** #${{ github.run_number }}
          - **Commit:** \\`${{ github.sha }}\\`
          
          Descarga el archivo APK directamente en tu teléfono Android desde los archivos adjuntos (Assets) abajo."
"""

new_release = """          cat << NOTE_EOF > release_notes.md
          ### 🎮 Wild Rift Coach - APK de Pruebas (Debug)
          - **Versión de la app:** v${{ steps.app_version.outputs.version_name }}
          - **Código de versión:** ${{ steps.app_version.outputs.version_code }}
          - **Ejecución GitHub Actions:** #${{ github.run_number }}
          - **Commit:** `${{ github.sha }}`
          
          Descarga el archivo APK directamente en tu teléfono Android desde los archivos adjuntos (Assets) abajo.
          NOTE_EOF
          
          # Create new release with the APK attached
          gh release create "$TAG_NAME" "${{ steps.prepare_asset.outputs.asset_path }}" \\
             --title "$RELEASE_NAME" \\
             --target "${{ github.sha }}" \\
             --notes-file release_notes.md
"""

content = content.replace(old_release, new_release)
with open('.github/workflows/build-apk.yml', 'w') as f:
    f.write(content)
