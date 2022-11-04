package com.practice.gameapp.data.repositories

import com.practice.gameapp.data.repositories.game.api.GameAPIClient
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.models.toGame
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameAPIRepository @Inject constructor(
    //private val gameClient: GameClient
    //private val gameAPI: GameAPI
    private val gameAPIClient: GameAPIClient
) : GameRepository {

    override suspend fun getGames(): List<GameModel> {
        return gameAPIClient.fetchGame().map { it.toGame() }
    }

    override suspend fun getRandomGame(): GameModel {
        return gameAPIClient.fetchGame().random().toGame()
    }
}


/*
 fun prueba(): List<GameModel> {
     return gameClient.fetchGame().map {it.toGame()}
 }*/
