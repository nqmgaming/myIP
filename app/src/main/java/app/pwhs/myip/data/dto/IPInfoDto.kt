package app.pwhs.myip.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class IPInfoDto(
    val ip: InternetProtocolDto? = null,
    val asn: AsnDto? = null,
    val location: LocationDto? = null
)