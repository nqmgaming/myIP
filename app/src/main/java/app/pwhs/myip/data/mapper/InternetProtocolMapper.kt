package app.pwhs.myip.data.mapper

import app.pwhs.myip.data.dto.InternetProtocolDto
import app.pwhs.myip.domain.entities.InternetProtocolEntity

fun InternetProtocolDto.toEntity(): InternetProtocolEntity {
    return InternetProtocolEntity(
        ip = this.ip,
        country = this.country,
        region = this.region,
        city = this.city,
        asn = this.asn,
        asnOrg = this.asnOrg
    )
}

fun InternetProtocolEntity.toDto(): InternetProtocolDto {
    return InternetProtocolDto(
        ip = this.ip,
        country = this.country,
        region = this.region,
        city = this.city,
        asn = this.asn,
        asnOrg = this.asnOrg
    )
}