package com.practice.gameapp.injectedDependencies

import com.google.gson.Gson
import com.practice.gameapp.data.repositories.GameAPIRepository
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.data.repositories.network.game.GameAPI
import com.practice.gameapp.data.repositories.network.game.GameAPIClient
import com.practice.gameapp.data.repositories.network.game.GameClient
import com.practice.gameapp.ui.viewmodels.HomeViewModel
import com.practice.gameapp.ui.viewmodels.QuizViewModel
import com.practice.gameapp.ui.viewmodels.VersusViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val remoteRepositoryModule = module {
    //API
    fun provideApi(): GameAPI {
        return Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(GameAPI::class.java)
    }

    single { provideApi() }
    single<GameClient> { GameAPIClient(get()) }
    single<GameRepository> { GameAPIRepository(get()) }

    //ViewModels
    single { HomeViewModel(get()) }
    single { QuizViewModel(get()) }
    single { VersusViewModel() }
}