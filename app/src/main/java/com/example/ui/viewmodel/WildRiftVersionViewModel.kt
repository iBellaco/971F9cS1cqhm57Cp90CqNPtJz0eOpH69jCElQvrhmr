package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.repository.WildRiftVersionRepository
import com.example.data.repository.WildRiftVersionRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Estados de la interfaz de usuario para la comprobación de versión de Wild Rift.
 */
sealed interface WildRiftVersionUiState {
    object Idle : WildRiftVersionUiState
    object Loading : WildRiftVersionUiState
    data class Success(val version: String) : WildRiftVersionUiState
    data class Error(val errorMessage: String, val throwable: Throwable? = null) : WildRiftVersionUiState
}

class WildRiftVersionViewModel(
    private val repository: WildRiftVersionRepository = WildRiftVersionRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow<WildRiftVersionUiState>(WildRiftVersionUiState.Idle)
    val uiState: StateFlow<WildRiftVersionUiState> = _uiState.asStateFlow()

    init {
        // Carga inicial en segundo plano al instanciar el ViewModel
        fetchLatestWildRiftVersion()
    }

    /**
     * Dispara la consulta a la API de iTunes Lookup en un hilo de fondo (Dispatchers.IO gestionado por el Repository).
     */
    fun fetchLatestWildRiftVersion() {
        viewModelScope.launch {
            _uiState.value = WildRiftVersionUiState.Loading

            repository.getLatestVersion()
                .onSuccess { version ->
                    _uiState.value = WildRiftVersionUiState.Success(version = version)
                }
                .onFailure { error ->
                    _uiState.value = WildRiftVersionUiState.Error(
                        errorMessage = error.localizedMessage ?: "Error desconocido al obtener la versión",
                        throwable = error
                    )
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WildRiftVersionViewModel(WildRiftVersionRepositoryImpl()) as T
            }
        }
    }
}
