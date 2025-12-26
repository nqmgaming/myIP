package app.pwhs.myip.presentation

import app.pwhs.myip.domain.entities.DeviceIPInfoEntity
import app.pwhs.myip.domain.entities.IPInfoEntity
import app.pwhs.myip.domain.entities.MapLocationEntity

data class HomeUIState(
    val ipInfo: IPInfoEntity? = null,
    val deviceIP: DeviceIPInfoEntity? = null,
    val mapLocation: MapLocationEntity? = null,
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val error: String? = null
)