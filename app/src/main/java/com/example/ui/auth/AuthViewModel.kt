package com.example.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.util.AuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

enum class PasswordStrength(val label: String) {
    NONE(""),
    WEAK("Débil"),
    MEDIUM("Media"),
    STRONG("Fuerte")
}

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val authScreen: AuthScreenType = AuthScreenType.LOGIN
)

enum class AuthScreenType {
    LOGIN, REGISTER, FORGOT_PASSWORD, EMAIL_VERIFICATION
}

class AuthViewModel : ViewModel() {

    private val auth = AuthManager.getAuth()

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _passwordStrength = MutableStateFlow(PasswordStrength.NONE)
    val passwordStrength: StateFlow<PasswordStrength> = _passwordStrength.asStateFlow()

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
        clearError()
    }

    fun updateUsername(newUsername: String) {
        _username.value = newUsername
        clearError()
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
        _passwordStrength.value = calculatePasswordStrength(newPassword)
        clearError()
    }

    fun updateConfirmPassword(newConfirm: String) {
        _confirmPassword.value = newConfirm
        clearError()
    }

    fun navigateTo(screen: AuthScreenType) {
        _uiState.update { it.copy(authScreen = screen, error = null, isSuccess = false) }
        if (screen == AuthScreenType.LOGIN || screen == AuthScreenType.REGISTER) {
             // Keep email, but maybe clear passwords if we want
        }
    }

    private fun clearError() {
        if (_uiState.value.error != null) {
            _uiState.update { it.copy(error = null) }
        }
    }

    fun login() {
        if (_email.value.isBlank() || _password.value.isBlank()) {
            _uiState.update { it.copy(error = "Por favor, completa todos los campos.") }
            return
        }
        
        if (auth == null) {
            _uiState.update { it.copy(error = "Firebase no configurado. Falta google-services.json.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                auth.signInWithEmailAndPassword(_email.value, _password.value).await()
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "Error de autenticación. Verifica tus credenciales."
                _uiState.update { it.copy(isLoading = false, error = errorMsg) }
            }
        }
    }

    fun register() {
        if (_email.value.isBlank() || _password.value.isBlank() || _username.value.isBlank()) {
            _uiState.update { it.copy(error = "Por favor, completa todos los campos.") }
            return
        }
        if (_password.value != _confirmPassword.value) {
            _uiState.update { it.copy(error = "Las contraseñas no coinciden.") }
            return
        }
        if (_passwordStrength.value == PasswordStrength.WEAK) {
            _uiState.update { it.copy(error = "La contraseña es demasiado débil.") }
            return
        }

        if (auth == null) {
            _uiState.update { it.copy(error = "Firebase no configurado. Falta google-services.json.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                auth.createUserWithEmailAndPassword(_email.value, _password.value).await()
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "Error al registrar la cuenta."
                _uiState.update { it.copy(isLoading = false, error = errorMsg) }
            }
        }
    }
    
    fun resetPassword() {
        if (_email.value.isBlank()) {
            _uiState.update { it.copy(error = "Por favor, ingresa tu correo electrónico.") }
            return
        }
        
        if (auth == null) {
            _uiState.update { it.copy(error = "Firebase no configurado.") }
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                auth.sendPasswordResetEmail(_email.value).await()
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            } catch (e: Exception) {
                val errorMsg = e.localizedMessage ?: "Error al enviar el enlace de recuperación."
                _uiState.update { it.copy(isLoading = false, error = errorMsg) }
            }
        }
    }

    private fun calculatePasswordStrength(password: String): PasswordStrength {
        if (password.isEmpty()) return PasswordStrength.NONE
        if (password.length < 6) return PasswordStrength.WEAK
        
        var hasUpper = false
        var hasLower = false
        var hasDigit = false
        var hasSpecial = false
        
        password.forEach {
            if (it.isUpperCase()) hasUpper = true
            else if (it.isLowerCase()) hasLower = true
            else if (it.isDigit()) hasDigit = true
            else hasSpecial = true
        }
        
        val points = listOf(hasUpper, hasLower, hasDigit, hasSpecial).count { it }
        
        return when {
            password.length >= 8 && points >= 3 -> PasswordStrength.STRONG
            points >= 2 -> PasswordStrength.MEDIUM
            else -> PasswordStrength.WEAK
        }
    }
}
