package app.pwhs.myip.data.dto.mapbox

import kotlinx.serialization.Serializable

@Serializable
data class GeoCodingDto(
    val features: List<FeatureDto>? = null,
)
