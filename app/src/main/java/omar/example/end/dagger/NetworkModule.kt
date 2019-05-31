package omar.example.end.dagger

import dagger.Module
import dagger.Provides
import omar.example.end.network.ProductsRemoteRepository
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRemoteRepository() = ProductsRemoteRepository()
}