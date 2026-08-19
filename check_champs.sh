#!/bin/bash
champs=$(grep -o 'name = "[^"]*"' app/src/main/java/com/example/data/champions/*.kt | cut -d '"' -f 2 | sort | uniq)
missing=""
for c in Ambessa Heimerdinger Lissandra Maokai Zyra Talon Kindred Kalista Viego Syndra Sivir Vladimir Nilah Hecarim Volibear Ornn Swain Urgot Zeri Zoe Lillia Yone Gwen Samira Sion Nautilus Pyke Ekko Shen Karma; do
  if ! echo "$champs" | grep -iq "$c"; then
    missing="$missing $c"
  fi
done
echo "Missing: $missing"
