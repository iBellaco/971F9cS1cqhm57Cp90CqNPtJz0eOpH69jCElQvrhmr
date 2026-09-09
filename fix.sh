#!/bin/bash
sed -i 's/val isAdmin = com.example.util.SubscriptionManager.isAdminClaim//g' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
sed -i 's/if (isAdmin || com.example.util.SubscriptionManager.userRole.value == "admin")/if (com.example.util.SubscriptionManager.userRole.value == "admin")/g' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
sed -i 's/Toast.makeText(context, tr("Fuera de servicio temporalmente"), Toast.LENGTH_SHORT).show()/Toast.makeText(context, "Fuera de servicio temporalmente", Toast.LENGTH_SHORT).show()/g' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
