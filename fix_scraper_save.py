import re

with open('app/src/main/java/com/example/util/WildRiftOfficialScraper.kt', 'r') as f:
    text = f.read()

# Change Download to Pictures
text = text.replace('Environment.DIRECTORY_DOWNLOADS', 'Environment.DIRECTORY_PICTURES')
text = text.replace('MediaStore.Downloads', 'MediaStore.Images.Media')
text = text.replace('Download/WildRift_Imagenes', 'Pictures/WildRift_Imagenes')

with open('app/src/main/java/com/example/util/WildRiftOfficialScraper.kt', 'w') as f:
    f.write(text)
