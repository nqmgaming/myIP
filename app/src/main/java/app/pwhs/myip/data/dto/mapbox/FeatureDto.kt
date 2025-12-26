package app.pwhs.myip.data.dto.mapbox

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureDto(
    @SerialName("place_type")
    val placeType: List<String>? = null,
    @SerialName("place_name")
    val placeName: String? = null,
)
