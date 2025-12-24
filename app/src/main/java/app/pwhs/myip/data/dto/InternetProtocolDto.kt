package app.pwhs.myip.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InternetProtocolDto(
    @SerialName("ip")
    val ipAddress: String? = null,
)