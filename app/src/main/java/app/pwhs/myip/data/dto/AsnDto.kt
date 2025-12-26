package app.pwhs.myip.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AsnDto(
    val number: Int? = null,
    @SerialName("org")
    val organization: String? = null,
)