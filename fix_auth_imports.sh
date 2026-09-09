#!/bin/bash
sed -i '/import androidx.compose.material.icons.Icons/a \
import androidx.compose.material.icons.filled.LocalActivity\
import androidx.compose.material.icons.filled.Message' app/src/main/java/com/example/ui/auth/AuthScreen.kt
