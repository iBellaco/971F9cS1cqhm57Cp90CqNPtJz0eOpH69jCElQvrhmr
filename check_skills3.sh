#!/bin/bash
urls=$(grep -o 'iconUrl = "[^"]*"' app/src/main/java/com/example/data/champions/*.kt | cut -d '"' -f 2 | sort | uniq)
missing=""
for url in $urls; do
  status=$(curl -o /dev/null -s -w "%{http_code}\n" "$url")
  if [ "$status" != "200" ]; then
    missing="$missing\n$url"
  fi
done
echo -e "Missing skill icons: $missing"
