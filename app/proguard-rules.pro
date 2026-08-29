# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Keep data models used for serialization (Moshi, Supabase/Kotlinx Serialization, Firebase, Room)
-keep class com.example.model.** { *; }
-keep class com.example.data.remote.model.** { *; }
-keep class com.example.data.supabase.model.** { *; }
-keep class com.example.data.local.entity.** { *; }

# Firebase (General safety for Reflection-based mapping)
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Supabase (Ktor and Serialization)
-keep class io.ktor.** { *; }
-keep class io.github.jan.supabase.** { *; }
-keepattributes *Annotation*, InnerClasses, EnclosingMethod, Signature

# Room (Keep Dao methods)
-keep class com.example.data.local.dao.** { *; }
-keepclassmembers class * {
    @androidx.room.Query *;
    @androidx.room.Insert *;
    @androidx.room.Update *;
    @androidx.room.Delete *;
    @androidx.room.Transaction *;
}

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Moshi
-keep class * extends com.squareup.moshi.JsonAdapter {
    public <init>(...);
}
-keepclassmembers class * {
    @com.squareup.moshi.Json *;
}

# Ktor Android missing classes
-dontwarn java.lang.management.**
-dontwarn io.ktor.util.debug.**
