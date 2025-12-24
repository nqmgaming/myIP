package app.pwhs.myip.domain.entities


data class InternetProtocolEntity(
    val asn: Long,
    val asnOrg: String,
    val city: String,
    val country: String,
    val ip: String,
    val region: String
)
