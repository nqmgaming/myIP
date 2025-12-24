package app.pwhs.myip.data

import app.pwhs.myip.core.AppConstants.BASE_URL
import app.pwhs.myip.data.dto.InternetProtocolDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val client: HttpClient) {
    suspend fun getInternetProtocol(): InternetProtocolDto =
        client.get(BASE_URL).body()
}
