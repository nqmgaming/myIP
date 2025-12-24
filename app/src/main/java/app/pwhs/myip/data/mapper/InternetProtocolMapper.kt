package app.pwhs.myip.data.mapper

import app.pwhs.myip.data.dto.InternetProtocolDto
import app.pwhs.myip.domain.entities.InternetProtocolEntity

fun InternetProtocolDto.toEntity(): InternetProtocolEntity {
    return InternetProtocolEntity(
        ipAddress = this.ipAddress ?: "Unknown",
    )
}

fun InternetProtocolEntity.toDto(): InternetProtocolDto {
    return InternetProtocolDto(
        ipAddress = this.ipAddress,
    )
}