sed -i 's/android.net.Uri.parse(parsedUrl)/java.io.File(parsedUrl.removePrefix("file:\/\/"))/g' app/src/main/java/com/example/ui/components/ChampionAvatar.kt
