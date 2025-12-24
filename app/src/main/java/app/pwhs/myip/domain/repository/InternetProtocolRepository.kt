package app.pwhs.myip.domain.repository

import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.domain.entities.IPInfoEntity
import kotlinx.coroutines.flow.Flow

interface InternetProtocolRepository {
    suspend fun getInternetProtocol(): Flow<Resources<IPInfoEntity>>
}