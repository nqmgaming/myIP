package app.pwhs.myip.domain.entities

data class LocationEntity(
    val city: String,
    val region: String,
    val country: String,
    val countryCode: String,
    val timezone: String,
)