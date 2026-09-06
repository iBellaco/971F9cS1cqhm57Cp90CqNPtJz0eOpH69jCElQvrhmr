import sys

file_path = "app/src/main/java/com/example/util/DeviceAndSessionManager.kt"
with open(file_path, "r") as f:
    content = f.read()

old_logic = """        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)
        val isAdmin = AuthManager.isCurrentUserAdmin()

        userRef.get().addOnSuccessListener { snapshot ->
            val registeredDevices = (snapshot.get("registeredDevices") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()
            val mutableDevices = registeredDevices.toMutableList()

            if (!mutableDevices.contains(deviceId)) {
                if (mutableDevices.size >= 2 && !isAdmin) {
                    onError("Límite de dispositivos alcanzado (Máx 2 dispositivos por cuenta).")
                    return@addOnSuccessListener
                }
                if (isAdmin && mutableDevices.size >= 10) mutableDevices.removeAt(0)
                mutableDevices.add(deviceId)
            }

            val updatePayload = hashMapOf<String, Any>(
                "sessionToken" to sessionToken,
                "lastDeviceId" to deviceId,
                "last_active" to loginTimestamp,
                "is_online" to true,
                "registeredDevices" to mutableDevices
            )

            userRef.set(updatePayload, SetOptions.merge())
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error saving session: ${e.message}")
                }
        }.addOnFailureListener { e ->
            Log.e(TAG, "Error fetching user to register device: ${e.message}")
        }
    }"""

new_logic = """        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("users").document(user.uid)

        user.getIdToken(false).addOnSuccessListener { tokenResult ->
            val isAdmin = tokenResult.claims["admin"] == true || AuthManager.isCurrentUserAdmin()

            userRef.get().addOnSuccessListener { snapshot ->
                val registeredDevices = (snapshot.get("registeredDevices") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()
                val mutableDevices = registeredDevices.toMutableList()

                if (!mutableDevices.contains(deviceId)) {
                    if (mutableDevices.size >= 2 && !isAdmin) {
                        onError("Límite de dispositivos alcanzado (Máx 2 dispositivos por cuenta).")
                        return@addOnSuccessListener
                    }
                    if (isAdmin && mutableDevices.size >= 10) mutableDevices.removeAt(0)
                    mutableDevices.add(deviceId)
                }

                val updatePayload = hashMapOf<String, Any>(
                    "sessionToken" to sessionToken,
                    "lastDeviceId" to deviceId,
                    "last_active" to loginTimestamp,
                    "is_online" to true,
                    "registeredDevices" to mutableDevices
                )

                userRef.set(updatePayload, SetOptions.merge())
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error saving session: ${e.message}")
                    }
            }.addOnFailureListener { e ->
                Log.e(TAG, "Error fetching user to register device: ${e.message}")
            }
        }.addOnFailureListener {
            Log.e(TAG, "Error fetching token claims for device check")
            // Fallback
            val isAdmin = AuthManager.isCurrentUserAdmin()
            userRef.get().addOnSuccessListener { snapshot ->
                val registeredDevices = (snapshot.get("registeredDevices") as? List<*>)?.mapNotNull { it?.toString() } ?: emptyList()
                val mutableDevices = registeredDevices.toMutableList()

                if (!mutableDevices.contains(deviceId)) {
                    if (mutableDevices.size >= 2 && !isAdmin) {
                        onError("Límite de dispositivos alcanzado (Máx 2 dispositivos por cuenta).")
                        return@addOnSuccessListener
                    }
                    if (isAdmin && mutableDevices.size >= 10) mutableDevices.removeAt(0)
                    mutableDevices.add(deviceId)
                }

                val updatePayload = hashMapOf<String, Any>(
                    "sessionToken" to sessionToken,
                    "lastDeviceId" to deviceId,
                    "last_active" to loginTimestamp,
                    "is_online" to true,
                    "registeredDevices" to mutableDevices
                )

                userRef.set(updatePayload, SetOptions.merge())
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error saving session: ${e.message}")
                    }
            }
        }
    }"""
content = content.replace(old_logic, new_logic)

with open(file_path, "w") as f:
    f.write(content)
