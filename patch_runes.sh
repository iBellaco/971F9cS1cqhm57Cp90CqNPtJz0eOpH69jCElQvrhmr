#!/bin/bash
awk '
/Spacer\(modifier = Modifier.height\(10.dp\)\)/ && in_runes_tab==0 {
    print $0
    print ""
    print "        // WR-Meta Database Status Banner"
    print "        Row("
    print "            modifier = Modifier"
    print "                .fillMaxWidth()"
    print "                .background(Color(0xFF0F1522), RoundedCornerShape(8.dp))"
    print "                .border(0.5.dp, HextechGold.copy(alpha = 0.4f), RoundedCornerShape(8.dp))"
    print "                .padding(horizontal = 10.dp, vertical = 6.dp),"
    print "            verticalAlignment = Alignment.CenterVertically,"
    print "            horizontalArrangement = Arrangement.SpaceBetween"
    print "        ) {"
    print "            Row(verticalAlignment = Alignment.CenterVertically) {"
    print "                Box("
    print "                    modifier = Modifier"
    print "                        .size(8.dp)"
    print "                        .background(Color(0xFF10B981), CircleShape)"
    print "                )"
    print "                Spacer(modifier = Modifier.width(6.dp))"
    print "                Text("
    print "                    text = \"wr-meta.com / runes\","
    print "                    color = HextechGold,"
    print "                    fontSize = 11.sp,"
    print "                    fontWeight = FontWeight.Bold"
    print "                )"
    print "            }"
    print "            Text("
    print "                text = \"${filteredRunes.size} ${tr(\\\"Runas\\\")} • ${tr(WildRiftRepository.CURRENT_PATCH_VERSION)}\","
    print "                color = HextechCyan,"
    print "                fontSize = 11.sp,"
    print "                fontWeight = FontWeight.SemiBold"
    print "            )"
    print "        }"
    print ""
    in_runes_tab=1
    next
}
/item \{/ && in_runes_tab==1 {
    # Skip lines until } is matched (basic counting)
    skip=1
    braces=1
    next
}
skip==1 {
    if (/{/) braces++
    if (/}/) braces--
    if (braces==0) {
        skip=0
        in_runes_tab=2
    }
    next
}
{ print $0 }
' app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt > temp2.kt

# The awk script above is a bit brittle for skipping the item block since it might catch other items. Let us just use sed to do a precise replacement.
