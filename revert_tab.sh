#!/bin/bash
sed -i 's/if (com.example.util.SubscriptionManager.userRole.value == "admin") {//g' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
sed -i 's/} else {//g' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
sed -i 's/Toast.makeText(context, "Fuera de servicio temporalmente", Toast.LENGTH_SHORT).show()//g' app/src/main/java/com/example/ui/components/CommunityCreatorsDialog.kt
