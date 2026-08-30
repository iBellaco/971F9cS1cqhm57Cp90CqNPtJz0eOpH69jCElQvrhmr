const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/util/SubscriptionManager.kt', 'utf8');

if (!code.includes('import com.example.data.AvatarCatalog')) {
    code = code.replace('import com.google.firebase.firestore.SetOptions', 'import com.google.firebase.firestore.SetOptions\nimport com.example.data.AvatarCatalog');
}

const targetFunc = `    fun canEquipAvatar(avatarId: String): Boolean {
        if (avatarId == "default_poro") return true
        if (_isPremium.value || _userRole.value == "admin") return true
        return _unlockedAvatars.value.contains(avatarId)
    }`;

const newFunc = `    fun canEquipAvatar(avatarId: String): Boolean {
        if (_isPremium.value || _userRole.value == "admin") return true
        if (_unlockedAvatars.value.contains(avatarId)) return true
        val avatar = AvatarCatalog.avatars.find { it.id == avatarId }
        if (avatar != null && (avatar.isDefault || avatar.rarity.equals("común", true) || avatar.rarity.equals("comun", true))) return true
        return false
    }`;

code = code.replace(targetFunc, newFunc);
fs.writeFileSync('app/src/main/java/com/example/util/SubscriptionManager.kt', code);
