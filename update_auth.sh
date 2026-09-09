#!/bin/bash
sed -i 's/onClick = { showCommunityCreatorsDialog = true }/onClick = { if (userRole == "admin") showCommunityCreatorsDialog = true else android.widget.Toast.makeText(context, tr("Fuera de servicio temporalmente"), android.widget.Toast.LENGTH_SHORT).show() }/g' app/src/main/java/com/example/ui/auth/AuthScreen.kt
