#!/bin/bash
sed -i '442c\
                                            }\
                                        } else {\
' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
