package com.practice.gameapp.data.repositories

import com.practice.gameapp.data.repositories.game.api.GameClient
import com.practice.gameapp.domain.models.GameModel
import com.practice.gameapp.domain.models.toGame

class GameAPIRepository(private val gameClient: GameClient) : GameRepository {

    override suspend fun getGames(): List<GameModel> {
        return gameClient.fetchGame().map { it.toGame() }
    }

    override suspend fun getRandomGame(): GameModel {
        return gameClient.fetchGame().random().toGame()
    }
}


/*
 fun prueba(): List<GameModel> {
     return gameClient.fetchGame().map {it.toGame()}
 }*/
