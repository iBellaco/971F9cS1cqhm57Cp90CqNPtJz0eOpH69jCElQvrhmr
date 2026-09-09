#!/bin/bash
sed -i 's/LazyColumn(/LazyColumn(modifier = Modifier.weight(1f),/g' app/src/main/java/com/example/ui/components/BlueEssenceStoreDialog.kt
sed -i 's/modifier = Modifier/modifier = Modifier/g' app/src/main/java/com/example/ui/components/BlueEssenceStoreDialog.kt
