#!/bin/bash
sed -i '/\/\/ Botón Escaneo Manual/,/Botón Limpiar Draft/d' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i '/\/\/ Botón Limpiar Draft/,/Botón Tamaño de Burbuja/d' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i 's/Icons.Default.KeyboardArrowDown/Icons.Default.KeyboardArrowRight/g' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i '/import androidx.compose.material.icons.filled.KeyboardArrowDown/d' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i '/import androidx.compose.material.icons.filled.FlashOn/d' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i '/import androidx.compose.material.icons.filled.DeleteSweep/d' app/src/main/java/com/example/service/FloatingAssistantService.kt
sed -i '/import androidx.compose.material.icons.filled.CheckCircle/a import androidx.compose.material.icons.filled.KeyboardArrowRight' app/src/main/java/com/example/service/FloatingAssistantService.kt
