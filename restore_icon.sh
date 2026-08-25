#!/bin/bash
set -e

RES_DIR="app/src/main/res"

# Eliminar los íconos XML genéricos que creé antes
rm -f "$RES_DIR"/drawable-*/ic_launcher*.xml
rm -f "$RES_DIR"/drawable-anydpi-v26/ic_launcher*.xml
rm -f "$RES_DIR"/mipmap-anydpi-v26/ic_launcher*.xml
rm -f "$RES_DIR"/drawable/ic_launcher*.xml

# Crear carpetas mipmap
for density in mdpi hdpi xhdpi xxhdpi xxxhdpi; do
    mkdir -p "$RES_DIR/mipmap-$density"
done

# Descargar el ícono correcto proporcionado por el usuario
curl -sL "https://i.ibb.co/mCnxRpjt/1787359139694.png" -o /tmp/user_icon.png

# Si por alguna razón falla la descarga, usamos el custom_app_icon.png que ya existía
if [ ! -s /tmp/user_icon.png ]; then
    cp "$RES_DIR/drawable/custom_app_icon.png" /tmp/user_icon.png
fi

# Distribuir el PNG real
for density in mdpi hdpi xhdpi xxhdpi xxxhdpi; do
    cp /tmp/user_icon.png "$RES_DIR/mipmap-$density/ic_launcher.png"
    cp /tmp/user_icon.png "$RES_DIR/mipmap-$density/ic_launcher_round.png"
done

# Restaurar el AndroidManifest y el código para usar mipmap
sed -i 's/@drawable\/ic_launcher/@mipmap\/ic_launcher/g' app/src/main/AndroidManifest.xml
find app/src/main/java -name "*.kt" -exec sed -i 's/R.drawable.ic_launcher/R.mipmap.ic_launcher/g' {} +

echo "Iconos restaurados exitosamente."
