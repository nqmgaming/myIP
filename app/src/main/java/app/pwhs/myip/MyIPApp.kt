package app.pwhs.myip

import android.app.Application
import app.pwhs.myip.core.AppConstants
import app.pwhs.myip.core.di.dataModule
import app.pwhs.myip.core.di.networkModule
import app.pwhs.myip.core.di.viewModelModule
import com.mapbox.common.MapboxOptions
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyIPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MapboxOptions.accessToken = AppConstants.MAP_BOX_API_KEY
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