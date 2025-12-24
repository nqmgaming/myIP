package app.pwhs.myip.core.di

import app.pwhs.myip.core.network.provideHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { provideHttpClient() }
}