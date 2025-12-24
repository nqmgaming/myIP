package app.pwhs.myip.presentation

import app.pwhs.myip.domain.entities.InternetProtocolEntity

data class HomeUIState(
    val internetProtocol: InternetProtocolEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)