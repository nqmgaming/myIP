package app.pwhs.myip.data.mapper

import app.pwhs.myip.data.dto.AsnDto
import app.pwhs.myip.domain.entities.AsnEntity

fun AsnDto.toEntity() = AsnEntity(
    number = this.number ?: 0,
    organization = this.organization ?: "Unknown",
)

fun AsnEntity.toDto() = AsnDto(
    number = this.number,
    organization = this.organization,
)