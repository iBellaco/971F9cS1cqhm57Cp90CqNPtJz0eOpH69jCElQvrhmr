# ===================================================================
# OFUSCACIÓN AVANZADA Y REGLAS DE SEGURIDAD R8 / PROGUARD
# ===================================================================

# Optimización y reducción de metadatos de depuración
-optimizationpasses 5
-allowaccessmodification
-repackageclasses 'com.example.wrdftx.o'
-renamesourcefileattribute SourceFile
-keepattributes *Annotation*, InnerClasses, EnclosingMethod, Signature, Exceptions

# Eliminar logs de depuración en compilaciones ofuscadas de release
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
}

# Mantener Componentes Clave del Sistema Android (Declarados en AndroidManifest)
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Keep data models used for serialization (Kotlinx Serialization, Firebase, Room, Json)
-keep class com.example.model.** { *; }
-keep class com.example.data.remote.model.** { *; }
-keep class com.example.data.supabase.model.** { *; }
-keep class com.example.data.local.entity.** { *; }

# Mantener serializadores generados por kotlinx.serialization
-keepclassmembers class * {
    @kotlinx.serialization.SerialName <fields>;
}
-keepclassmembers class * implements kotlinx.serialization.KSerializer {
    public static *** INSTANCE;
}
-keepclassmembers class * {
    *** Companion;
}
-keepclasseswithmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
}

# Firebase & Google Play Services (Mapeos y Reflection)
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# Room (Mantener DAOs y Entidades)
-keep class com.example.data.local.dao.** { *; }
-keepclassmembers class * {
    @androidx.room.Query *;
    @androidx.room.Insert *;
    @androidx.room.Update *;
    @androidx.room.Delete *;
    @androidx.room.Transaction *;
}

# Jetpack Compose (Reglas de estabilidad para recomposiciones)
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Coroutines & Kotlin Reflection
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-dontwarn kotlinx.coroutines.**

# Supabase & Ktor Networking
-keep class io.github.jan.supabase.** { *; }
-dontwarn io.github.jan.supabase.**
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**

# Coil Image Loading
-keep class coil3.** { *; }
-dontwarn coil3.**

# AndroidX Navigation & Lifecycle
-keep class androidx.navigation.** { *; }
-keep class androidx.lifecycle.** { *; }

# Módulos de Seguridad y Anti-Tampering (Evitar que sean removidos por dead-code elimination)
-keep class com.example.util.AppSecurityManager { *; }
-keep class com.example.util.DeviceAndSessionManager { *; }
-keep class com.example.util.SubscriptionManager { *; }
-keep class com.example.util.CrashLogger { *; }
-keep class com.example.util.SystemPermissionHelper { *; }


