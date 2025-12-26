package app.pwhs.myip.data

import app.pwhs.myip.core.AppConstants.BASE_URL
import app.pwhs.myip.core.AppConstants.MAP_BOX_API_KEY
import app.pwhs.myip.core.AppConstants.MAP_BOX_BASE_URL
import app.pwhs.myip.data.dto.IPInfoDto
import app.pwhs.myip.data.dto.mapbox.GeoCodingDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {
    suspend fun getInternetProtocol(): IPInfoDto =
        client.get(BASE_URL).body()

    suspend fun searchIpAddress(ip: String): IPInfoDto =
        client.get("$BASE_URL/api/search") {
            url {
                parameters.append("ip", ip)
            }
        }.body()

    suspend fun getMapLocation(
        latitude: Double,
        longitude: Double
    ): GeoCodingDto =
        client.get("$MAP_BOX_BASE_URL/geocoding/v5/mapbox.places/$longitude,$latitude.json") {
            url {
                parameters.append("access_token", MAP_BOX_API_KEY)
                parameters.append("types", "address")
            }
        }.body()


}
