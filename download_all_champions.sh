#!/bin/bash
mkdir -p app/src/main/assets/champions
while read -r url; do
    filename=$(echo "$url" | awk -F'/' '{print $NF}' | cut -d'?' -f1)
    # the downloaded files will automatically overwrite existing ones
    clean_filename=$(echo "$filename" | tr '-' '_' | tr '[:upper:]' '[:lower:]')
    
    # Let's ensure things like "dr-mundo" -> "dr_mundo", "nunu-and-willump" -> "nunu_and_willump", "aurelion-sol" -> "aurelion_sol"
    # Actually wait! The app expects exact names like "dr_mundo.png", "kai_sa.png" based on my ID format.
    # The image files from postimg already have names like "aatrox.png", "ahri.png".
    # I should just rename '-' to '_' and lower it as before.
    
    curl -sL "$url" -o "app/src/main/assets/champions/$clean_filename"
done < fast_links_all.txt
