import re

with open('.github/workflows/build-apk.yml', 'r') as f:
    content = f.read()

# Find everything from "      - name: Create or Update GitHub Release" to the end
pattern = r"      - name: Create or Update GitHub Release.*"
replacement = """      - name: Create or Update GitHub Release
        id: create_release
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          TAG_NAME="v${{ steps.app_version.outputs.version_name }}-b${{ steps.app_version.outputs.version_code }}"
          RELEASE_NAME="Wild Rift Coach v${{ steps.app_version.outputs.version_name }} (Build #${{ steps.app_version.outputs.version_code }})"
          
          echo "Creando release: $TAG_NAME"
          
          # Delete if it already exists to overwrite
          gh release delete "$TAG_NAME" --cleanup-tag -y 2>/dev/null || true
          
          cat << NOTE_EOF > release_notes.md
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
content = re.sub(pattern, replacement, content, flags=re.DOTALL)

with open('.github/workflows/build-apk.yml', 'w') as f:
    f.write(content)
