package app.pwhs.myip.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val city: String? = null,
    val region: String? = null,
    val country: String? = null,
    @SerialName("country_code")
    val countryCode: String? = null,
    @SerialName("country_flag")
    val countryFlag: String? = null,
    val timezone: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
)