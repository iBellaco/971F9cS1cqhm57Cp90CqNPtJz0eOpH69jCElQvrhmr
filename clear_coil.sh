#!/bin/bash
sed -i '/setContent {/i \
        // Clear Coil cache on update to v1.3.65 to show new avatars\
        coil.imageLoader(this).memoryCache?.clear()\
        coil.imageLoader(this).diskCache?.clear()' app/src/main/java/com/example/MainActivity.kt
