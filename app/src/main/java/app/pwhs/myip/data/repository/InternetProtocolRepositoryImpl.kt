package app.pwhs.myip.data.repository

import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.data.ApiService
import app.pwhs.myip.domain.entities.InternetProtocolEntity
import app.pwhs.myip.domain.repository.InternetProtocolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InternetProtocolRepositoryImpl(
    private val apiService: ApiService
) : InternetProtocolRepository {
    override suspend fun getInternetProtocol(): Flow<Resources<InternetProtocolEntity>> = flow {
        emit(Resources.Loading())
        try {
            val response = apiService.getInternetProtocol()
            val entity = InternetProtocolEntity(
                ip = response.ip,
                country = response.country,
                region = response.region,
                city = response.city,
                asn = response.asn,
                asnOrg = response.asnOrg
            )
            emit(Resources.Success(entity))
        } catch (e: Exception) {
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}