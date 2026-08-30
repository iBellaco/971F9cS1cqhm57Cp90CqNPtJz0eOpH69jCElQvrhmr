// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
  alias(libs.plugins.kotlin.serialization) apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.compose) apply false
  alias(libs.plugins.google.devtools.ksp) apply false
  alias(libs.plugins.roborazzi) apply false
  alias(libs.plugins.secrets) apply false
  alias(libs.plugins.google.services) apply false
}


val githubKeystore = file("${rootDir}/github.keystore")
val base64File = file("${rootDir}/debug.keystore.base64")
if (base64File.exists() && !githubKeystore.exists()) {
    try {
        val decoded = java.util.Base64.getDecoder().decode(base64File.readText().replace(Regex("\\s"), ""))
        githubKeystore.writeBytes(decoded)
        println("Restaurado github.keystore desde archivo base64 para mantener consistencia de firmas en GitHub.")
    } catch (e: Exception) {
        println("Error al restaurar github.keystore: ${e.message}")
    }
}
