#!/bin/bash
sed -i 's/isLegendaryRanked = DraftValidationLayer.isLegendaryRankedDraft(visionText.text)/if (!isLegendaryRankedCache) {\n                isLegendaryRankedCache = DraftValidationLayer.isLegendaryRankedDraft(visionText.text)\n            }\n            isLegendaryRanked = isLegendaryRankedCache/' app/src/main/java/com/example/service/screen/DraftVisionScanner.kt
