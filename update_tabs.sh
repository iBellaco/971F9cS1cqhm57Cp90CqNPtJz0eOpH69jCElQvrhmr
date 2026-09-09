#!/bin/bash
sed -i '172,174c\
                                .clickable { \
                                    if (index == 2) {\
                                        val isAdmin = com.example.util.SubscriptionManager.isAdminClaim\
                                        if (isAdmin || com.example.util.SubscriptionManager.userRole.value == "admin") {\
                                            selectedTab = index\
                                        } else {\
                                            Toast.makeText(context, tr("Fuera de servicio temporalmente"), Toast.LENGTH_SHORT).show()\
                                        }\
                                    } else {\
                                        selectedTab = index\
                                    }\
                                }\
                                .padding(vertical = 8.dp),' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
