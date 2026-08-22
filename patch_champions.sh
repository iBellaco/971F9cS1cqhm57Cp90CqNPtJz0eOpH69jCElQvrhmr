#!/bin/bash
# Insert the banner in ChampionsCatalogTab after "Spacer(modifier = Modifier.height(10.dp))" and before "// Search Bar"
# We know the line number is around 440.
awk '
/Spacer\(modifier = Modifier.height\(10.dp\)\)/ && in_champ_tab==0 {
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
    print "                    text = \"wr-meta.com / champions\","
    print "                    color = HextechGold,"
    print "                    fontSize = 11.sp,"
    print "                    fontWeight = FontWeight.Bold"
    print "                )"
    print "            }"
    print "            Text("
    print "                text = \"${filteredChampions.size} ${tr(\\\"Campeones\\\")}\","
    print "                color = HextechCyan,"
    print "                fontSize = 11.sp,"
    print "                fontWeight = FontWeight.SemiBold"
    print "            )"
    print "        }"
    print ""
    in_champ_tab=1
    next
}
{ print $0 }
' app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt > temp1.kt

mv temp1.kt app/src/main/java/com/example/ui/screens/MetaAndDraftScreen.kt
