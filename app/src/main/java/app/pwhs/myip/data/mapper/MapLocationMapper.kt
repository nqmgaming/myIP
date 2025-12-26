package app.pwhs.myip.data.mapper

import app.pwhs.myip.data.dto.mapbox.GeoCodingDto
import app.pwhs.myip.domain.entities.MapLocationEntity

fun GeoCodingDto.toMapLocation() : MapLocationEntity {
    val feature = this.features?.firstOrNull()
    return if (feature != null) {
        MapLocationEntity(
            placeName = feature.placeName ?: "unknown",
            placeType = feature.placeType?.firstOrNull() ?: "unknown"
        )
    } else {
        MapLocationEntity(
            placeName = "unknown",
            placeType = "unknown"
        )
    }
}