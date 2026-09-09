#!/bin/bash
mkdir -p app/src/main/assets/champions
while read -r url; do
    # Extract filename from URL (e.g., kai-sa.png?dl=1 -> kai-sa.png)
    filename=$(echo "$url" | awk -F'/' '{print $NF}' | cut -d'?' -f1)
    
    # Replace '-' with '_' for consistent naming (kai-sa.png -> kai_sa.png)
    # Wait, some champions might not need dash replaced? 
    # Let's replace '-' with '_' and lowercase it just in case.
    clean_filename=$(echo "$filename" | tr '-' '_' | tr '[:upper:]' '[:lower:]')
    
    echo "Downloading $clean_filename from $url"
    curl -sL "$url" -o "app/src/main/assets/champions/$clean_filename"
done < fast_links.txt
