#!/bin/bash
open=0
close=0
for i in $(seq 41 590); do
  line=$(sed -n "${i}p" app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt)
  o=$(echo "$line" | grep -o "{" | wc -l)
  c=$(echo "$line" | grep -o "}" | wc -l)
  open=$((open + o))
  close=$((close + c))
  if [ $close -gt $open ]; then
    echo "Extra close at line $i: open=$open close=$close"
    break
  fi
done
