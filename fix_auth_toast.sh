#!/bin/bash
sed -i 's/android.widget.Toast.makeText(context, tr("Fuera de servicio temporalmente"), android.widget.Toast.LENGTH_SHORT).show()/android.widget.Toast.makeText(context, "Fuera de servicio temporalmente", android.widget.Toast.LENGTH_SHORT).show()/g' app/src/main/java/com/example/ui/auth/AuthScreen.kt
