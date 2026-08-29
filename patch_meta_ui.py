import re

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'r') as f:
    text = f.read()

# Update the region list
text = text.replace('listOf("NA", "CN").forEach', 'listOf("NA", "CN", "BestBuildWR").forEach')

# Update the region text label
text = text.replace(
    'text = if (region == "NA") tr("América (NA)") else tr("China (CN)"),',
    'text = if (region == "NA") tr("América (NA)") else if (region == "CN") tr("China (CN)") else tr("Global (Web)"),'
)

# Update the "Instantáneo 24/7" text for BestBuildWR if needed. NA handles it.
text = text.replace(
    'text = if (currentRegion == "NA") tr("Global") else tr("Instantáneo 24/7"),',
    'text = if (currentRegion == "NA") tr("Base") else if (currentRegion == "BestBuildWR") tr("Web") else tr("Instantáneo 24/7"),'
)

with open('app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt', 'w') as f:
    f.write(text)
