package app.pwhs.myip.domain.repository

import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.domain.entities.InternetProtocolEntity
import kotlinx.coroutines.flow.Flow

interface InternetProtocolRepository {
    suspend fun getInternetProtocol(): Flow<Resources<InternetProtocolEntity>>
}