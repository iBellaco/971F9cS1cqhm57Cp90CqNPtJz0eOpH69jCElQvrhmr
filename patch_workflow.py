import re

with open('.github/workflows/build-apk.yml', 'r') as f:
    content = f.read()

old_release_action = """      - name: Create or Update GitHub Release
        id: create_release
        uses: softprops/action-gh-release@v2
        if: success()
        with:
          tag_name: v${{ steps.app_version.outputs.version_name }}-b${{ steps.app_version.outputs.version_code }}
          name: Wild Rift Coach v${{ steps.app_version.outputs.version_name }} (Build #${{ steps.app_version.outputs.version_code }})
          body: |
            ### 🎮 Wild Rift Coach - APK de Pruebas (Debug)
            - **Versión de la app:** v${{ steps.app_version.outputs.version_name }}
            - **Código de versión (versionCode):** ${{ steps.app_version.outputs.version_code }}
            - **Ejecución GitHub Actions:** #${{ github.run_number }}
            - **Commit:** `${{ github.sha }}`
            
            Descarga el archivo APK directamente en tu teléfono Android desde los archivos adjuntos (Assets) abajo.
          files: ${{ steps.prepare_asset.outputs.asset_path }}
          token: ${{ secrets.GITHUB_TOKEN }}
        continue-on-error: true"""

new_release_action = """      - name: Create or Update GitHub Release
        id: create_release
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          TAG_NAME="v${{ steps.app_version.outputs.version_name }}-b${{ steps.app_version.outputs.version_code }}"
          RELEASE_NAME="Wild Rift Coach v${{ steps.app_version.outputs.version_name }} (Build #${{ steps.app_version.outputs.version_code }})"
          
          echo "Creando release: $TAG_NAME"
          
          # Delete if it already exists to overwrite
          gh release delete "$TAG_NAME" --cleanup-tag -y 2>/dev/null || true
          
          # Create new release with the APK attached
          gh release create "$TAG_NAME" "${{ steps.prepare_asset.outputs.asset_path }}" \
            --title "$RELEASE_NAME" \
            --target "${{ github.sha }}" \
            --notes "### 🎮 Wild Rift Coach - APK de Pruebas (Debug)
          - **Versión de la app:** v${{ steps.app_version.outputs.version_name }}
          - **Código de versión:** ${{ steps.app_version.outputs.version_code }}
          - **Ejecución GitHub Actions:** #${{ github.run_number }}
          - **Commit:** \`${{ github.sha }}\`
          
          Descarga el archivo APK directamente en tu teléfono Android desde los archivos adjuntos (Assets) abajo."
        continue-on-error: true"""

content = content.replace(old_release_action, new_release_action)

old_cleanup = """      - name: Cleanup old releases
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          echo "Buscando releases antiguos..."
          # Lista todos los releases. El awk 'NR>1' omite el más reciente (el que acabamos de crear) y toma el Tag ($3) del resto.
          gh release list | awk 'NR>1 {print $3}' | while read tag; do
            if [ ! -z "$tag" ]; then
              echo "Eliminando release y tag antiguo: $tag"
              gh release delete "$tag" --cleanup-tag -y || true
            fi
          done
        continue-on-error: true"""

new_cleanup = """      - name: Cleanup old releases
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          echo "Buscando releases antiguos..."
          gh release list --limit 1000 | awk -F '\t' 'NR>1 {print $3}' | while read tag; do
            if [ ! -z "$tag" ]; then
              echo "Eliminando release y tag antiguo: $tag"
              gh release delete "$tag" --cleanup-tag -y || true
            fi
          done
        continue-on-error: true"""

content = content.replace(old_cleanup, new_cleanup)

with open('.github/workflows/build-apk.yml', 'w') as f:
    f.write(content)
