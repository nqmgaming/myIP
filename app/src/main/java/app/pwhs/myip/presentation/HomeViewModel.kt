package app.pwhs.myip.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.domain.repository.InternetProtocolRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val internetProtocolRepository: InternetProtocolRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getInternetProtocol()
    }

    fun onAction(action: HomeUIAction) {
        when (action) {
            is HomeUIAction.OnRefresh -> {
                getInternetProtocol()
            }
        }
    }

    private fun getInternetProtocol() {
        viewModelScope.launch {
            internetProtocolRepository.getInternetProtocol().collect { resources ->
                when (resources) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                error = null
                            )
                        }

                    }

                    is Resources.Success -> {
                        _uiState.update {
                            it.copy(
                                internetProtocol = resources.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }

                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = resources.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
            }
        }
    }
}