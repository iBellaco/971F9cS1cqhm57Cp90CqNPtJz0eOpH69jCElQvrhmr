#!/bin/bash
sed -i '159c\
                                .clickable { selectedTab = index }\
                                .padding(vertical = 8.dp),\
                            contentAlignment = Alignment.Center\
                        ) {' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
