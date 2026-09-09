#!/bin/bash
sed -i '443c\
                                        } else {\
' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
