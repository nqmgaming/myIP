package app.pwhs.myip.domain.entities

data class IPInfoEntity(
    val ip: InternetProtocolEntity,
    val asn: AsnEntity,
    val location: LocationEntity
)