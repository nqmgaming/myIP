package app.pwhs.myip.data.repository

import app.pwhs.myip.core.data.Resources
import app.pwhs.myip.data.ApiService
import app.pwhs.myip.data.mapper.toEntity
import app.pwhs.myip.domain.entities.IPInfoEntity
import app.pwhs.myip.domain.repository.InternetProtocolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InternetProtocolRepositoryImpl(
    private val apiService: ApiService
) : InternetProtocolRepository {
    override suspend fun getInternetProtocol(): Flow<Resources<IPInfoEntity>> = flow {
        emit(Resources.Loading())
        try {
            val response = apiService.getInternetProtocol()
            emit(Resources.Success(response.toEntity()))
        } catch (e: Exception) {
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}