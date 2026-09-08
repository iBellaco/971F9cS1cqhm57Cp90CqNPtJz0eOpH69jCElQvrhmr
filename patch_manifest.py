import sys

with open("app/src/main/AndroidManifest.xml", "r") as f:
    content = f.read()

target = """        <meta-data
            android:name="com.google.mlkit.vision.DEPENDENCIES"
            android:value="ocr" />"""

replacement = """        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="ca-app-pub-5124881073806951~7616874321"/>
        <meta-data
            android:name="com.google.mlkit.vision.DEPENDENCIES"
            android:value="ocr" />"""

if target in content:
    content = content.replace(target, replacement)
    with open("app/src/main/AndroidManifest.xml", "w") as f:
        f.write(content)
    print("Updated AndroidManifest.xml")
else:
    print("Target not found in AndroidManifest.xml")

