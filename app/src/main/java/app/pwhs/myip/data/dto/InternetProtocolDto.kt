package app.pwhs.myip.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InternetProtocolDto(
    val asn: Long,
    @SerialName("asn_org")
    val asnOrg: String,
    val city: String,
    val country: String,
    val ip: String,
    val region: String
)