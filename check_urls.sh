#!/bin/bash
urls=$(grep -o 'avatarUrl = "[^"]*"' app/src/main/java/com/example/data/champions/*.kt | cut -d '"' -f 2)
for url in $urls; do
  status=$(curl -o /dev/null -s -w "%{http_code}\n" "$url")
  if [ "$status" != "200" ]; then
    echo "Broken URL ($status): $url"
  fi
done
