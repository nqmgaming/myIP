package app.pwhs.myip.domain.repository

import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.domain.entities.IPInfoEntity
import app.pwhs.myip.domain.entities.MapLocationEntity
import kotlinx.coroutines.flow.Flow

interface InternetProtocolRepository {
    suspend fun getInternetProtocol(): Flow<Resources<IPInfoEntity>>
    suspend fun searchIPAddress(ip: String): Flow<Resources<IPInfoEntity>>
    suspend fun getMapLocation(
        latitude: Double,
        longitude: Double
    ): Flow<Resources<MapLocationEntity>>
}