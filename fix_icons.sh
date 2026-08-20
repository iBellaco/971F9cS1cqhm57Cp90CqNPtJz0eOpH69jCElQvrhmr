#!/bin/bash
SRC="app/src/main/res/drawable/app_icon_custom_foreground.png"
if [ ! -f "$SRC" ]; then
  echo "No source image found!"
  exit 1
fi

declare -A SIZES=(
  ["mdpi"]=48
  ["hdpi"]=72
  ["xhdpi"]=96
  ["xxhdpi"]=144
  ["xxxhdpi"]=192
)

for D in "${!SIZES[@]}"; do
  SIZE=${SIZES[$D]}
  DIR="app/src/main/res/mipmap-${D}"
  mkdir -p "$DIR"
  # Regular icon
  convert "$SRC" -resize ${SIZE}x${SIZE} "$DIR/ic_launcher.png"
  # Round icon (circle crop)
  convert "$SRC" -resize ${SIZE}x${SIZE} \
    \( +clone -alpha extract -draw "fill black polygon 0,0 0,$SIZE $SIZE,$SIZE $SIZE,0" -fill white -draw "circle $((SIZE/2)),$((SIZE/2)) $((SIZE/2)),0" \) \
    -alpha off -compose CopyOpacity -composite "$DIR/ic_launcher_round.png"
done
