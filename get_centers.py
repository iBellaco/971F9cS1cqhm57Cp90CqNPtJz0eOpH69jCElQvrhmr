import sys
from PIL import Image

# Use the screenshot that the user uploaded
# Oh wait, the image is not mounted in the filesystem by default unless the system provides it.
# Let's check if there are images in the directory.
