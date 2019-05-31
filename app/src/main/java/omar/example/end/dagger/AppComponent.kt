package omar.example.end.dagger

import dagger.Component
import omar.example.end.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        PresenterModule::class,
        NetworkModule::class]
)
interface AppComponent {

    fun inject(target: MainActivity)
}