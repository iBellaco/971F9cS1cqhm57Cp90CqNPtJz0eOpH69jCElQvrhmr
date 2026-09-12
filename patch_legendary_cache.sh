#!/bin/bash
sed -i 's/private val allySlotFilters/private var isLegendaryRankedCache = false\n    private val allySlotFilters/' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt
sed -i 's/allySlotRolesCache.clear()/isLegendaryRankedCache = false\n        allySlotRolesCache.clear()/' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt
