package ro.gabrieltechventures.scorekeeperpro

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ro.gabrieltechventures.scorekeeperpro.di.appModule

class ScoreKeeperProApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScoreKeeperProApp)
            modules(appModule)
        }
    }
}