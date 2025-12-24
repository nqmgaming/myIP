package app.pwhs.myip.presentation

import app.pwhs.myip.domain.entities.IPInfoEntity

data class HomeUIState(
    val ipInfo: IPInfoEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)