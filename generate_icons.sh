#!/bin/bash
# Base dimensions
# mdpi: 48, hdpi: 72, xhdpi: 96, xxhdpi: 144, xxxhdpi: 192

# Generate background gradient (using ImageMagick)
for SIZE in 48 72 96 144 192; do
  case $SIZE in
    48) DIR="mipmap-mdpi";;
    72) DIR="mipmap-hdpi";;
    96) DIR="mipmap-xhdpi";;
    144) DIR="mipmap-xxhdpi";;
    192) DIR="mipmap-xxxhdpi";;
  esac
  
  mkdir -p app/src/main/res/$DIR
  
  # The logo should be slightly smaller than the full icon (approx 70%)
  LOGO_SIZE=$(($SIZE * 70 / 100))
  
  # Create a dark blue background (from the gradient colors)
  convert -size ${SIZE}x${SIZE} canvas:"#060d1a" bg_$SIZE.png
  
  # Resize the logo
  convert app/src/main/res/drawable/app_icon_user.png -resize ${LOGO_SIZE}x${LOGO_SIZE} logo_$SIZE.png
  
  # Composite logo on background
  convert bg_$SIZE.png logo_$SIZE.png -gravity center -composite app/src/main/res/$DIR/ic_launcher.png
  
  # Create circular mask for round icon
  convert -size ${SIZE}x${SIZE} xc:none -fill white -draw "circle $(($SIZE/2)),$(($SIZE/2)) $(($SIZE/2)),0" mask_$SIZE.png
  
  # Apply mask to make it round
  convert app/src/main/res/$DIR/ic_launcher.png mask_$SIZE.png -alpha set -compose DstIn -composite app/src/main/res/$DIR/ic_launcher_round.png
  
  # Clean up temp files
  rm bg_$SIZE.png logo_$SIZE.png mask_$SIZE.png
done
