#!/bin/bash
set -e

RES_DIR="app/src/main/res"

# 1. Update AndroidManifest.xml
sed -i 's/@mipmap\/ic_launcher/@drawable\/ic_launcher/g' app/src/main/AndroidManifest.xml
sed -i 's/@mipmap\/ic_launcher_round/@drawable\/ic_launcher_round/g' app/src/main/AndroidManifest.xml

# 2. Update references in code
find app/src/main/java -name "*.kt" -exec sed -i 's/R.mipmap.ic_launcher/R.drawable.ic_launcher/g' {} +

# 3. Move mipmap-anydpi-v26 to drawable-anydpi-v26
if [ -d "$RES_DIR/mipmap-anydpi-v26" ]; then
    mv "$RES_DIR/mipmap-anydpi-v26" "$RES_DIR/drawable-anydpi-v26"
fi

# 4. Create the fallback XML content
cat << 'XML_CONTENT' > /tmp/fallback_ic_launcher.xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_launcher_background" />
    <item android:drawable="@drawable/ic_launcher_foreground" />
</layer-list>
XML_CONTENT

# 5. Populate all requested drawable folders
for density in nodpi mdpi hdpi xhdpi xxhdpi xxxhdpi; do
    DIR="$RES_DIR/drawable-$density"
    mkdir -p "$DIR"
    cp /tmp/fallback_ic_launcher.xml "$DIR/ic_launcher.xml"
    cp /tmp/fallback_ic_launcher.xml "$DIR/ic_launcher_round.xml"
done

echo "Icon folders replaced and updated successfully."
