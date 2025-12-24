package app.pwhs.myip.data.mapper

import app.pwhs.myip.data.dto.LocationDto
import app.pwhs.myip.domain.entities.LocationEntity

fun LocationDto.toEntity() = LocationEntity(
    country = this.country ?: "Unknown",
    region = this.region ?: "Unknown",
    city = this.city ?: "Unknown",
    timezone = this.timezone ?: "UTC",
    countryCode = this.countryCode ?: "UN"
)

fun LocationEntity.toDto() = LocationDto(
    country = this.country,
    region = this.region,
    city = this.city,
    timezone = this.timezone,
    countryCode = this.countryCode
)