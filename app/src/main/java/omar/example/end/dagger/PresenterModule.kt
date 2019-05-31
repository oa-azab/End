package omar.example.end.dagger

import dagger.Module
import dagger.Provides
import omar.example.end.network.ProductsRemoteRepository
import omar.example.end.ui.main.MainPresenter
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainPresenter(remoteRepository: ProductsRemoteRepository): MainPresenter =
        MainPresenter(remoteRepository)
}