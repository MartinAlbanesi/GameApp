package com.practice.gameapp.injectedDependencies

import com.google.gson.Gson
import com.practice.gameapp.data.repositories.GameAPIRepository
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.data.repositories.network.game.GameAPI
import com.practice.gameapp.data.repositories.network.game.GameAPIClient
import com.practice.gameapp.data.repositories.network.game.GameClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApi(): GameAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GameAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideGameRepository(gameClient: GameAPIClient): GameRepository {
        return GameAPIRepository(gameClient)
    }

    @Provides
    @Singleton
    fun provideGameClient(gameAPI: GameAPI): GameClient {
        return GameAPIClient(gameAPI)
    }


//    single <GameAPIProvider> { GameAPIMainProvider() }
//    single <GameClient> { GameAPIClient(get()) }
//    single <GameRepository> { GameAPIRepository(get()) }
//    fragment { HomeFragment() }
//
//    //ViewModel
//    viewModel { HomeViewModel(get()) }
//    viewModel { MainViewModel() }
}