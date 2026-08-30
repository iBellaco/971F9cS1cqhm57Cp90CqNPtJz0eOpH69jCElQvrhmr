const fs = require('fs');
let code = fs.readFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', 'utf8');

const oldClick = `.clickable(enabled = canEquip || isEquipped) {
                                if (isEquipped) {
                                    Toast.makeText(context, "Este avatar ya está equipado.", Toast.LENGTH_SHORT).show()
                                } else {
                                    isUpdating = true
                                    SubscriptionManager.changeAvatar(
                                        avatarId = avatar.id,
                                        onSuccess = {
                                            isUpdating = false
                                            Toast.makeText(context, "¡Avatar actualizado con éxito!", Toast.LENGTH_SHORT).show()
                                        },
                                        onError = { error ->
                                            isUpdating = false
                                            Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
                                        }
                                    )
                                }
                            }`;

const newClick = `.clickable {
                                if (isEquipped) {
                                    Toast.makeText(context, "Este avatar ya está equipado.", Toast.LENGTH_SHORT).show()
                                } else if (!canEquip) {
                                    showPremiumRequiredDialog = avatar
                                } else {
                                    isUpdating = true
                                    SubscriptionManager.changeAvatar(
                                        avatarId = avatar.id,
                                        onSuccess = {
                                            isUpdating = false
                                            Toast.makeText(context, "¡Avatar actualizado con éxito!", Toast.LENGTH_SHORT).show()
                                        },
                                        onError = { error ->
                                            isUpdating = false
                                            Toast.makeText(context, "Error: $error", Toast.LENGTH_SHORT).show()
                                        }
                                    )
                                }
                            }`;

code = code.replace(oldClick, newClick);
fs.writeFileSync('app/src/main/java/com/example/ui/components/AvatarSelectionDialog.kt', code);
console.log("Fixed clicks!");
