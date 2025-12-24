package app.pwhs.myip.data.mapper

import app.pwhs.myip.data.dto.IPInfoDto
import app.pwhs.myip.domain.entities.IPInfoEntity

fun IPInfoDto.toEntity() = IPInfoEntity(
    ip = this.ip?.toEntity() ?: throw IllegalArgumentException("IP information is missing"),
    location = this.location?.toEntity()
        ?: throw IllegalArgumentException("Location information is missing"),
    asn = this.asn?.toEntity() ?: throw IllegalArgumentException("ASN information is missing")
)

fun IPInfoEntity.toDto() = IPInfoDto(
    ip = this.ip.toDto(),
    location = this.location.toDto(),
    asn = this.asn.toDto()
)