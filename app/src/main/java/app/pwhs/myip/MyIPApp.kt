package app.pwhs.myip

import android.app.Application
import app.pwhs.myip.core.di.dataModule
import app.pwhs.myip.core.di.networkModule
import app.pwhs.myip.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyIPApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyIPApp)
            modules(
                networkModule,
                dataModule,
                viewModelModule
            )
        }
    }
}