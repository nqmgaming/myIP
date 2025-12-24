package app.pwhs.myip.core.di

import app.pwhs.myip.data.ApiService
import app.pwhs.myip.data.repository.InternetProtocolRepositoryImpl
import app.pwhs.myip.domain.repository.InternetProtocolRepository
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

val dataModule = module {
    singleOf(::ApiService)
    singleOf(::InternetProtocolRepositoryImpl) bind InternetProtocolRepository::class
}
