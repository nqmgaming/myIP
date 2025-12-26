package app.pwhs.myip.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.core.util.LocalIPUtils
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
            is HomeUIAction.OnSearchIp -> {
                searchIPAddress(action.query)
            }
        }
    }

    private fun getInternetProtocol() {
        val localIP = LocalIPUtils.getLocalIPs()
        _uiState.update {
            it.copy(
                deviceIP = localIP
            )
        }
        viewModelScope.launch {
            internetProtocolRepository.getInternetProtocol().collect { resources ->
                when (resources) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                            )
                        }

                    }

                    is Resources.Success -> {
                        _uiState.update {
                            it.copy(
                                ipInfo = resources.data,
                                isLoading = false,
                                error = null
                            )
                        }
                        if (resources.data?.location == null) return@collect
                        val latitude = resources.data.location.latitude
                        val longitude = resources.data.location.longitude
                        getMapLocation(latitude, longitude)
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

    private fun getMapLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            internetProtocolRepository.getMapLocation(latitude, longitude).collect { resources ->
                when (resources) {
                    is Resources.Loading -> {
                        // Optionally handle loading state for map location
                    }

                    is Resources.Success -> {
                        _uiState.update {
                            it.copy(
                                mapLocation = resources.data
                            )
                        }
                    }

                    is Resources.Error -> {
                        // Optionally handle error state for map location
                    }
                }
            }
        }
    }

    private fun searchIPAddress(query: String) {
        viewModelScope.launch {
            internetProtocolRepository.searchIPAddress(query).collect { resources ->
                when (resources) {
                    is Resources.Loading -> {
                        _uiState.update {
                            it.copy(
                                isSearching = true,
                                error = null,
                            )
                        }

                    }

                    is Resources.Success -> {
                        _uiState.update {
                            it.copy(
                                ipInfo = resources.data,
                                isSearching = false,
                                error = null
                            )
                        }
                        if (resources.data?.location == null) return@collect
                        val latitude = resources.data.location.latitude
                        val longitude = resources.data.location.longitude
                        getMapLocation(latitude, longitude)
                    }

                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                isSearching = false,
                                error = resources.message ?: "An unexpected error occurred"
                            )
                        }
                    }
                }
            }
        }
    }
}