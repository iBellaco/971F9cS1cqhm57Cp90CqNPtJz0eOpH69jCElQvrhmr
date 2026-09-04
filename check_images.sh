#!/bin/bash
grep -h -oE "offline_images/[a-zA-Z0-9_.-]+" app/src/main/java/com/example/data/WildRiftItemsData.kt app/src/main/java/com/example/data/WildRiftSpellsAndRunes.kt | awk -F/ '{print $2}' | sort | uniq > mapped_images.txt
ls -1 app/src/main/assets/offline_images/ > actual_images.txt
echo "Mapped images count: $(wc -l < mapped_images.txt)"
echo "Actual images count: $(wc -l < actual_images.txt)"
comm -23 mapped_images.txt actual_images.txt > missing_in_fs.txt
comm -13 mapped_images.txt actual_images.txt > unmapped_in_fs.txt
echo "Images mapped but missing in FS:"
cat missing_in_fs.txt | wc -l
echo "Images in FS but NOT mapped in code:"
cat unmapped_in_fs.txt | wc -l
