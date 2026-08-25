import re

with open("app/src/main/AndroidManifest.xml", "r", encoding="utf-8") as f:
    content = f.read()

permissions_to_add = """    <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" android:maxSdkVersion="29" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" android:maxSdkVersion="32" />
    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />"""

content = content.replace('    <uses-permission android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS" />', permissions_to_add)

with open("app/src/main/AndroidManifest.xml", "w", encoding="utf-8") as f:
    f.write(content)
