package com.practice.gameapp.injectedDependencies

import com.practice.gameapp.data.repositories.GameAPIRepository
import com.practice.gameapp.data.repositories.GameRepository
import com.practice.gameapp.data.repositories.game.api.GameAPIClient
import com.practice.gameapp.data.repositories.game.api.GameAPIMainProvider
import com.practice.gameapp.data.repositories.game.api.GameAPIProvider
import com.practice.gameapp.data.repositories.game.api.GameClient
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single <GameAPIProvider> { GameAPIMainProvider() }
    single <GameClient> { GameAPIClient(get()) }
    single <GameRepository> { GameAPIRepository(get()) }


}