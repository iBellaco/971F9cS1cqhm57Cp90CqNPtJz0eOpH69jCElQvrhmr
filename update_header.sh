#!/bin/bash
sed -i '78,118c\
                // Header\
                Row(\
                    modifier = Modifier.fillMaxWidth(),\
                    horizontalArrangement = Arrangement.SpaceBetween,\
                    verticalAlignment = Alignment.CenterVertically\
                ) {\
                    Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {\
                        Row(verticalAlignment = Alignment.CenterVertically) {\
                            Text(\
                                text = "🌟 " + tr("Comunidad & Creadores"),\
                                color = HextechGold,\
                                fontSize = 18.sp,\
                                fontWeight = FontWeight.Bold\
                            )\
                        }\
                        Spacer(modifier = Modifier.height(2.dp))\
                        Text(\
                            text = tr("Suscríbete con Esencia Azul a tus creadores favoritos"),\
                            color = TextSecondary,\
                            fontSize = 11.5.sp\
                        )\
                    }\
                    IconButton(onClick = onDismiss, modifier = Modifier.size(32.dp)) {\
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = TextMuted)\
                    }\
                }\
                Spacer(modifier = Modifier.height(10.dp))\
                \
                // Banner Esencia Azul\
                val currentBlueEssence by com.example.util.SubscriptionManager.blueEssence.collectAsState()\
                Row(\
                    modifier = Modifier\
                        .fillMaxWidth()\
                        .clip(RoundedCornerShape(8.dp))\
                        .background(HextechSurface)\
                        .border(1.dp, HextechCyan.copy(alpha = 0.5f), RoundedCornerShape(8.dp))\
                        .padding(12.dp),\
                    horizontalArrangement = Arrangement.SpaceBetween,\
                    verticalAlignment = Alignment.CenterVertically\
                ) {\
                    Row(verticalAlignment = Alignment.CenterVertically) {\
                        Icon(Icons.Default.LocalActivity, contentDescription = null, tint = HextechCyan, modifier = Modifier.size(20.dp))\
                        Spacer(modifier = Modifier.width(8.dp))\
                        Column {\
                            Text(tr("Tu Esencia Azul"), color = TextSecondary, fontSize = 11.sp)\
                            Text(\
                                text = "$currentBlueEssence EA",\
                                color = HextechCyan,\
                                fontSize = 15.sp,\
                                fontWeight = FontWeight.Bold\
                            )\
                        }\
                    }\
                    Button(\
                        onClick = onOpenBlueEssenceStore,\
                        colors = ButtonDefaults.buttonColors(containerColor = HextechCyan),\
                        shape = RoundedCornerShape(8.dp),\
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),\
                        modifier = Modifier.height(36.dp)\
                    ) {\
                        Icon(Icons.Default.Add, contentDescription = null, tint = HextechDarkBg, modifier = Modifier.size(16.dp))\
                        Spacer(modifier = Modifier.width(4.dp))\
                        Text(tr("Comprar"), color = HextechDarkBg, fontSize = 12.sp, fontWeight = FontWeight.Bold)\
                    }\
                }\
' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
