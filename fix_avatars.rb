content = File.read("app/src/main/java/com/example/data/AvatarCatalog.kt")

# Match blocks that define AvatarItem
new_content = content.gsub(/AvatarItem\(\s*id\s*=\s*"([^"]+)",([^)]+?)imageUrl\s*=\s*"https:\/\/ddragon.leagueoflegends.com[^"]+"(.*?)\)/m) do |match|
  id = $1
  rest1 = $2
  rest2 = $3
  "AvatarItem(\n            id = \"#{id}\",#{rest1}imageUrl = \"file:///android_asset/champions/#{id}.png\"#{rest2})"
end

File.write("app/src/main/java/com/example/data/AvatarCatalog.kt", new_content)
