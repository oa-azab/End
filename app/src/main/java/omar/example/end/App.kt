package omar.example.end

import android.app.Application
import omar.example.end.dagger.AppComponent
import omar.example.end.dagger.AppModule
import omar.example.end.dagger.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = initDagger(this)
    }


    private fun initDagger(app: App): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}