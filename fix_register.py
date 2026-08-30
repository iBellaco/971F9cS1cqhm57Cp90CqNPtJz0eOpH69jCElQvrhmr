import re
with open('app/src/main/java/com/example/ui/auth/AuthViewModel.kt', 'r') as f:
    text = f.read()

target = """                auth.createUserWithEmailAndPassword(_email.value, _password.value).await()
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }"""

replacement = """                val result = auth.createUserWithEmailAndPassword(_email.value, _password.value).await()
                val profileUpdates = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(_username.value)
                    .build()
                result.user?.updateProfile(profileUpdates)?.await()
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }"""

text = text.replace(target, replacement)

with open('app/src/main/java/com/example/ui/auth/AuthViewModel.kt', 'w') as f:
    f.write(text)
print("AuthViewModel updated")
